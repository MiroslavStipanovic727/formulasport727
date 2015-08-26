/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages;

// <editor-fold defaultstate="collapsed" desc="import">
import com.metropolitan.formulasport727.dao.DiskusijaDAO;
import com.metropolitan.formulasport727.dao.DiskusionaGrupaDAO;
import com.metropolitan.formulasport727.dao.KategorijaDAO;
import com.metropolitan.formulasport727.data.Uloga;
import com.metropolitan.formulasport727.entities.Diskusija;
import com.metropolitan.formulasport727.entities.DiskusionaGrupa;
import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.pages.diskusionegrupe.IzabranaDiskusija;
import com.metropolitan.formulasport727.pages.diskusionegrupe.NovaDiskusija;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
//</editor-fold>

/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje 
 * sekcije Diskusionih Grupa sajta
 * @author Miroslav Stipanović 727
 */
public class DiskusioneGrupe {
    
    // <editor-fold defaultstate="collapsed" desc="Atributi">
    // <editor-fold defaultstate="collapsed" desc="Ajax atributi">
    @Inject
    private Request zahtev;
    @Inject
    private AjaxResponseRenderer psvRR;
    @InjectComponent
    private Zone zonaFormeIzboraKategorije;
    @InjectComponent
    private Zone zonaPrikazaDG;
    @InjectComponent
    private Zone zonaPrikazaDiskusija;
    @InjectComponent
    private Zone zonaFormePretraga;
    @InjectComponent
    private Zone zonaPrikazaPretrage;
    //</editor-fold>
    
    @Property
    @Persist
    private List<Kategorija> kategorije;
    @Persist
    private Kategorija selektKategorije;
    @Inject
    private KategorijaDAO kaDAO;
    @Persist
    private boolean imaKategorije;
    
    @Property
    @Persist
    private List<DiskusionaGrupa> diskusioneGrupeKategorije;
    @Property
    private DiskusionaGrupa dGrupa;
    @Inject
    private DiskusionaGrupaDAO dgDAO; 
    @Persist
    private DiskusionaGrupa izabranaDG;
    
    @Inject
    private DiskusijaDAO diDAO;
    @Property
    @Persist
    private List<Diskusija> diskusijeGrupe;
    @Property
    private Diskusija diskusijaGrupe;
    @Property
    @Persist
    private List<Diskusija> neodobreneDiskusijeGrupe;
    @Property
    private Diskusija neodobrenaDiskusijaGrupe;
    @Property
    @Persist
    private List<Diskusija> pretrazeneDiskusije;
    @Property
    private Diskusija pretrazenaDiskusija;
    @Property
    private String kljucnereci;
    @Property
    @Persist
    private String pretrazeneReci;
    
    @InjectPage
    private NovaDiskusija novaDiskusija;
    @InjectPage
    private IzabranaDiskusija izDiskusija;
    
    private final String format = "dd/MM/yyyy HH:mm";

    public Format getFormat()
    {
        return new SimpleDateFormat(format);
    }
    
    @Property
    @SessionState
    private Korisnik ulogovaniKorisnik;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Value Encoderi">
    public ValueEncoder getEncoderKategorije(){
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
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="On Metode">
    void onActivate(){
        if(kategorije==null){
            kategorije = kaDAO.getListaSvihKategorija();
        }
        if(getImaIzabraneDG()){
            updateDiskusijeGrupe(getIzabranaDG());
        }
    }
    
    Object onSuccessFromIzborKategorije(){
        if(getSelektKategorije()!=null){
            diskusioneGrupeKategorije = dgDAO.getListaSvihDiskusionihGrupaKategorije(getSelektKategorije());
            imaKategorije = true;
        }
        else {
            diskusioneGrupeKategorije = new ArrayList<>();
            imaKategorije = false;
        }
        setIzabranaDG(new DiskusionaGrupa());
        diskusijeGrupe = new ArrayList<>();
        neodobreneDiskusijeGrupe = new ArrayList<>();
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaDG).addRender(zonaFormeIzboraKategorije);
        }
        return zahtev.isXHR() ? zonaPrikazaDG.getBody() : null;
//        return this;
    }
    
    Object onSuccessFromPretraga(){
        if(kljucnereci==null||kljucnereci.equals("")){
            pretrazeneDiskusije = new ArrayList<>();
            pretrazeneReci = "";
        }
            
        pretrazeneDiskusije = diDAO.getListaDiskusijaPoKljucnimRecima(kljucnereci);
        pretrazeneReci = kljucnereci;
        kljucnereci = "";
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaPretrage).addRender(zonaFormePretraga);
        }
        return zahtev.isXHR() ? zonaPrikazaPretrage.getBody() : null;
//        return this;
    }
    
    Object onIzaberiDG(DiskusionaGrupa izabranaDG){
        this.setIzabranaDG(izabranaDG);
        updateDiskusijeGrupe(izabranaDG);
        return zahtev.isXHR() ? zonaPrikazaDiskusija.getBody() : null;
//        return this;
    }
    
    Object onIzaberiDiskusiju(Diskusija izabranaDiskusija){
        izDiskusija.setDiskusija(izabranaDiskusija);
        return izDiskusija;
    }
    
    Object onNovaDiskusija(DiskusionaGrupa izabranaDG){
        novaDiskusija.setDiskusionaGrupa(izabranaDG);
        if(!ulogovaniKorisnik.getUloga().equals(Uloga.Moderator)){
            novaDiskusija.setEditDiskusija(new Diskusija());
        }
        return novaDiskusija;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get i Util Metode">
    public boolean getImaKategorije(){
        return imaKategorije;
    }
    
    public boolean getImaIzabraneDG(){
        return getIzabranaDG() != null && getIzabranaDG().getNazivDg()!=null;
    }
    
    public boolean getIzvrsenaPretraga(){
        return pretrazeneReci!=null&&!pretrazeneReci.equals("");
//        return kljucnereci!=null&&!kljucnereci.equals("");
    }
    
    public boolean getImaOdobrenihDiskusija(){
        return diDAO.getBrojOdobrenihDiskusijaDiskusioneGrupe(getIzabranaDG())>0;
    }
    
    public boolean getImaNeodobrenihDiskusija(){
        return diDAO.getBrojNeodobrenihDiskusijaDiskusioneGrupe(getIzabranaDG())>0;
    }
    
    public boolean getModerator(){
        return ulogovaniKorisnik!=null&&ulogovaniKorisnik.getUloga().equals(Uloga.Moderator);
    }
    
    public boolean getUlogovan(){
        return ulogovaniKorisnik!=null&&ulogovaniKorisnik.getEmail()!=null;
    }

    public Kategorija getSelektKategorije() {
        return selektKategorije;
    }

    public void setSelektKategorije(Kategorija selektKategorije) {
        this.selektKategorije = selektKategorije;
    }

    public DiskusionaGrupa getIzabranaDG() {
        return izabranaDG;
    }

    public void setIzabranaDG(DiskusionaGrupa izabranaDG) {
        this.izabranaDG = izabranaDG;
    }
    
    public int brojDiskusijaDG(DiskusionaGrupa dGrupa){
        return diDAO.getBrojOdobrenihDiskusijaDiskusioneGrupe(dGrupa);
    }
    
    private void updateDiskusijeGrupe(DiskusionaGrupa izabranaDG) {
        diskusijeGrupe = diDAO.getListaSvihOdobrenihDiskusijaDiskusioneGrupe(izabranaDG);
        neodobreneDiskusijeGrupe = diDAO.getListaSvihNeodobrenihDiskusijaDiskusioneGrupe(izabranaDG);
    }
    //</editor-fold>

    
}
