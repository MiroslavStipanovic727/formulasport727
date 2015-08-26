/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages.diskusionegrupe;

import com.metropolitan.formulasport727.dao.DiskusijaDAO;
import com.metropolitan.formulasport727.dao.KomentarDiskusijeDAO;
import com.metropolitan.formulasport727.data.Uloga;
import com.metropolitan.formulasport727.entities.Diskusija;
import com.metropolitan.formulasport727.entities.KomentarDiskusije;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.pages.DiskusioneGrupe;
import com.metropolitan.formulasport727.services.ProtectedPage;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje stranice 
 * na kojoj se prikazuje izabrana diskusija korisnika
 * @author Miroslav Stipanović 727
 */
@ProtectedPage
@RolesAllowed(value={"Administrator","Klijent", "Urednik", "Moderator"})
public class IzabranaDiskusija {
    
    // <editor-fold defaultstate="collapsed" desc="Ajax atributi">
    @Inject
    private Request zahtev;
    @Inject
    private AjaxResponseRenderer psvRR;
    @InjectComponent
    private Zone zonaModDiskOpcija;
    @InjectComponent
    private Zone zonaFormeKomentar;
    @InjectComponent
    private Zone zonaPrikazKomentara;
    //</editor-fold>
    
    @Persist
    private Diskusija diskusija;
    @Inject
    private DiskusijaDAO diDAO;
    
    @Property
    @SessionState
    private Korisnik ulogovaniKorisnik;
    
    @Property
    @Persist
    private List<KomentarDiskusije> komentari;
    @Property
    private KomentarDiskusije komentar;
    @Inject
    private KomentarDiskusijeDAO kdDAO;
    
    @Property
    @Persist
    private String naslovKomentara;
    @Property
    @Persist
    private String tekstKomentara;
    
    @InjectPage
    private DiskusioneGrupe dGrupe;
    @InjectPage
    private NovaDiskusija nDiskusija;
    
    @Persist
    private KomentarDiskusije editKomentar;
    
    private final String format = "dd/MM/yyyy HH:mm";
    
    void onActivate(){
        komentari = kdDAO.getListaSvihKomentaraDiskusijeUDiskusiji(diskusija);
    }
    
    @CommitAfter
    Object onSuccessFromNovKomentar(){
        if(getIzmenaKomentara()){
            editKomentar.setNaslovKd(naslovKomentara);
            editKomentar.setTekstKd(tekstKomentara);
            kdDAO.dodajIliIzmeniKomentarDiskusije(editKomentar);
            setEditKomentar(new KomentarDiskusije());
            komentari = kdDAO.getListaSvihKomentaraDiskusijeUDiskusiji(diskusija);
        } else {
            KomentarDiskusije novKomentar = new KomentarDiskusije();
            novKomentar.setNaslovKd(naslovKomentara);
            novKomentar.setTekstKd(tekstKomentara);
            novKomentar.setDisId(diskusija);
            novKomentar.setKorId(ulogovaniKorisnik);
            novKomentar.setVremePostavljanja(Calendar.getInstance().getTime());
            kdDAO.dodajIliIzmeniKomentarDiskusije(novKomentar);
            komentari = kdDAO.getListaSvihKomentaraDiskusijeUDiskusiji(diskusija);
        }
        naslovKomentara = "";
        tekstKomentara = "";
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazKomentara).addRender(zonaFormeKomentar);
        }
        return zahtev.isXHR() ? zonaPrikazKomentara.getBody() : null;
    }

    Object onNazadNaDg(){
        dGrupe.setSelektKategorije(diskusija.getDisId().getKatId());
        dGrupe.setIzabranaDG(diskusija.getDisId());
        return dGrupe;
    }
    
    Object onIzmenaDiskusije(){
        nDiskusija.setDiskusionaGrupa(diskusija.getDisId());
        nDiskusija.setEditDiskusija(diskusija);
        return nDiskusija;
    }
    
    @CommitAfter
    Object onOdobrenjeDiskusije(){
        diskusija.setOdobrena(true);
        diDAO.dodajIliIzmeniDiskusiju(diskusija);
        return zahtev.isXHR() ? zonaModDiskOpcija.getBody() : null;
    }
    
    @CommitAfter
    Object onZabranaDiskusije(){
        dGrupe.setSelektKategorije(diskusija.getDisId().getKatId());
        dGrupe.setIzabranaDG(diskusija.getDisId());
        diDAO.obrisiDiskusiju(diskusija.getId());
        return dGrupe;
    }
    
    Object onIzmeniKomentar(KomentarDiskusije izmena){
        editKomentar = izmena;
        tekstKomentara = editKomentar.getTekstKd();
        if(editKomentar.getNaslovKd()!=null&&!editKomentar.getNaslovKd().equals(""))
            naslovKomentara = editKomentar.getNaslovKd();
        return zahtev.isXHR() ? zonaFormeKomentar.getBody() : null;
    }
    
    @CommitAfter
    Object onBrisanjeKomentara(Long id){
        kdDAO.obrisiKomentarDiskusije(id);
        komentari = kdDAO.getListaSvihKomentaraDiskusijeUDiskusiji(diskusija);
        return zahtev.isXHR() ? zonaPrikazKomentara.getBody() : null;
    }
    
    public Format getFormat()
    {
        return new SimpleDateFormat(format);
    }

    public Diskusija getDiskusija() {
        return diskusija;
    }

    public void setDiskusija(Diskusija diskusija) {
        this.diskusija = diskusija;
    }

    public KomentarDiskusije getEditKomentar() {
        return editKomentar;
    }

    public void setEditKomentar(KomentarDiskusije editKomentar) {
        this.editKomentar = editKomentar;
    }
    
    public boolean getImaNaslovKomentara(){
        return komentar!=null&&komentar.getNaslovKd()!=null;
    }
    
    public boolean getModerator(){
        return ulogovaniKorisnik!=null&&ulogovaniKorisnik.getUloga().equals(Uloga.Moderator);
    }
    
    public boolean getNeodobrenaDiskusija(){
        return !diskusija.isOdobrena();
    }
    
    public boolean getIzmenaKomentara(){
        return editKomentar!=null&&editKomentar.getId()!=null;
    }
    
}
