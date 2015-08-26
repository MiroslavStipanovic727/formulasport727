/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages;

import com.metropolitan.formulasport727.dao.KategorijaDAO;
import com.metropolitan.formulasport727.dao.KomentarVestiDAO;
import com.metropolitan.formulasport727.dao.KorisnikDAO;
import com.metropolitan.formulasport727.data.PretragaVesti;
import com.metropolitan.formulasport727.data.Uloga;
import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.KomentarVesti;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.entities.Vest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.tapestry5.ValueEncoder;
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
 * na kojoj se prikazuju izabrane vesti korisnika
 * @author Miroslav Stipanović 727
 */
public class IzabranaVest {
    
    // <editor-fold defaultstate="collapsed" desc="Ajax atributi">
    @Inject
    private Request zahtev;
    @Inject
    private AjaxResponseRenderer psvRR;
    @InjectComponent
    private Zone zonaFormeKomentara;
    @InjectComponent
    private Zone zonaPrikazaKomentara;
    //</editor-fold>
    
    @Property
    private PretragaVesti pretraga;
    @Inject
    private KategorijaDAO kaDAO;
    @Property
    private Kategorija kategorija;
    @Persist
    private Vest izabranaVest;
    
    @Property
    @SessionState
    private Korisnik ulogovaniKorisnik;
    
    @Property
    private KomentarVesti komentar;
    @Property
//    @Persist
    private KomentarVesti unosKomentar;
    
    @Inject
    private KomentarVestiDAO kvDAO;
    @Inject
    private KorisnikDAO koDAO;
    
    @Property
    private String naslovKomentara;
    @Property
    private String tekstKomentara;
    
    @InjectPage
    private Index index;
    
    @Property
    @Persist
    private List<Kategorija> kategorije;
    @Property
    private List<KomentarVesti> komentariVesti;
    @Persist
    private KomentarVesti editKomentar;
    
    private final String format = "dd/MM/yyyy HH:mm";

    public Format getFormat()
    {
        return new SimpleDateFormat(format);
    }
    
    public ValueEncoder getEncoder(){
        return new ValueEncoder<Kategorija>(){

            @Override
            public String toClient(Kategorija v) {
                return String.valueOf(v.getId());
            }

            @Override
            public Kategorija toValue(String string) {
                Kategorija kategorija=kaDAO.getKategorijaById(Long.parseLong(string));
                return kategorija;
            }
            
        };
    }
    
    public boolean getImaKomentara(){
        return !komentariVesti.isEmpty();
    }
    
    public boolean getImaNaslov(){
        return komentar.getNaslovKomentara() != null;
    }
    
    public Vest getIzabranaVest(){
        return this.izabranaVest;
    }
    
    public void setIzabranaVest(Vest izabranaVest){
        this.izabranaVest = izabranaVest;
    }

    public KomentarVesti getEditKomentar() {
        return editKomentar;
    }

    public void setEditKomentar(KomentarVesti editKomentar) {
        this.editKomentar = editKomentar;
    }
    
    void onActivate(){
        komentariVesti = kvDAO.getListaSvihKomentaraKonkretneVesti(izabranaVest);
        kategorije = kaDAO.getListaSvihKategorija();
        if(unosKomentar == null){
            unosKomentar = new KomentarVesti();
        }
    }
    
    Object onIzmeniKomentar(KomentarVesti izmena){
        setEditKomentar(izmena);
        tekstKomentara = getEditKomentar().getTekstKomentara();
        if(getEditKomentar().getNaslovKomentara()!=null&&!getEditKomentar().getNaslovKomentara().equals(""))
            naslovKomentara = getEditKomentar().getNaslovKomentara();
        return zahtev.isXHR() ? zonaFormeKomentara.getBody() : null;
    }
    
    @CommitAfter
    Object onBrisanjeKomentara(Long id){
        kvDAO.obrisiKomentarVesti(id);
        komentariVesti = kvDAO.getListaSvihKomentaraKonkretneVesti(izabranaVest);
        return zahtev.isXHR() ? zonaPrikazaKomentara.getBody() : null;
    }
    
    @CommitAfter
    Object onSuccessFromNovKomentar(){
        if(getIzmenaKomentara()){
            editKomentar.setNaslovKomentara(naslovKomentara);
            editKomentar.setTekstKomentara(tekstKomentara);
            kvDAO.dodajIliIzmeniKomentarVesti(editKomentar);
            setEditKomentar(new KomentarVesti());
            komentariVesti = kvDAO.getListaSvihKomentaraKonkretneVesti(izabranaVest);
        } else {
            napraviKomentar();
            kvDAO.dodajIliIzmeniKomentarVesti(unosKomentar);
            komentariVesti = kvDAO.getListaSvihKomentaraKonkretneVesti(izabranaVest);
            unosKomentar = new KomentarVesti();
        }
        naslovKomentara = "";
        tekstKomentara = "";
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaKomentara).addRender(zonaFormeKomentara);
        }
        return zahtev.isXHR() ? zonaPrikazaKomentara.getBody() : null;
    }
    
    Object onSuccessFromPretraga(){
//        index.setPretragaAktiviranaIzVesti(true);
        index.setIzabraniDatum1(pretraga.getDatum1());
        index.setIzabraniDatum2(pretraga.getDatum2());
        index.setIzabranaKategorija(kategorija);
        return index;
        
    }

    private void napraviKomentar() {
        unosKomentar.setKorId(ulogovaniKorisnik);
        if(naslovKomentara!=null&&!naslovKomentara.equals("")){
            unosKomentar.setNaslovKomentara(naslovKomentara);
        }
        unosKomentar.setTekstKomentara(tekstKomentara);
        unosKomentar.setVesId(izabranaVest);
        unosKomentar.setVreme(Calendar.getInstance().getTime());
    }
    
    public boolean getUlogovan(){
        return ulogovaniKorisnik!=null&&ulogovaniKorisnik.getEmail()!=null;
    }
    
    public boolean getModerator(){
        return ulogovaniKorisnik!=null&&ulogovaniKorisnik.getUloga().equals(Uloga.Moderator);
    }
    
    public boolean getIzmenaKomentara(){
        return editKomentar!=null&&editKomentar.getId()!=null;
    }
}
