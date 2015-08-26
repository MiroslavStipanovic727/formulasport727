/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages;

// <editor-fold defaultstate="collapsed" desc="import">
import com.metropolitan.formulasport727.dao.KategorijaDAO;
import com.metropolitan.formulasport727.dao.VestDAO;
import com.metropolitan.formulasport727.data.PretragaVesti;
import com.metropolitan.formulasport727.data.Uloga;
import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.entities.Vest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PersistentLocale;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
//</editor-fold>

/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje početne
 * strane sajta, odnosno sekcije vesti sajta
 * @author Miroslav Stipanović 727
 */
public class Index {
    // <editor-fold defaultstate="collapsed" desc="Atributi">
    // <editor-fold defaultstate="collapsed" desc="Ajax atributi">
    @Inject
    private Request zahtev;
    @Inject
    private AjaxResponseRenderer psvRR;
    @InjectComponent
    private Zone zonaPoslednjihVesti;
    @InjectComponent
    private Zone zonaPretrazenihVesti;
    //</editor-fold>
    @Property
    private PretragaVesti pretraga;
  
    @Inject
    private KategorijaDAO katDAO;
    @Inject
    private VestDAO vestDAO;
    
    @Property
    private Kategorija kid;
    @Property
    private Kategorija kid2;
    
    @Property
    @Persist
    private List<Kategorija> kategorije;
    @Property
    @Persist
    private List<Vest> pretrazeneVesti;
    @Persist
    private Date izabraniDatum1;
    @Persist
    private Date izabraniDatum2;
    @Persist
    private Kategorija izabranaKategorija;
    @Property
    private Vest pVest;
    @Property
//    @Persist
    private List<Vest> poslednjeVesti;
    
    @Persist
    private boolean pretragaAktivirana;
    @Persist
    private boolean pretragaAktiviranaIzVesti;
    
    @InjectPage
    private IzabranaVest izvest;
    @InjectPage
    private UnosIzmenaVesti unosIzmenaVesti;
    
    @SessionState
    private Korisnik ulogovaniKorisnik;
    
    private final String format = "dd/MM/yyyy HH:mm";
    
    @Inject
    private PersistentLocale persistentLocale;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Value Encoderi">
    public ValueEncoder getEncoder(){
        return new ValueEncoder<Kategorija>(){

            @Override
            public String toClient(Kategorija v) {
                return String.valueOf(v.getId());
            }

            @Override
            public Kategorija toValue(String string) {
                Kategorija kategorija=katDAO.getKategorijaById(Long.parseLong(string));
                return kategorija;
            }
        };
    }
    //</editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="On Metode">
    void onActivate(){
        kategorije = katDAO.getListaSvihKategorija();
        if(getIzabranaKategorija()!=null&&getIzabranaKategorija().getId()!=null){
            pretrazi(getIzabraniDatum1(),getIzabraniDatum2(),getIzabranaKategorija());}
        poslednjeVesti = vestDAO.getListaPoslednjihObjavljenihVesti().subList(0, 11);
        if (!persistentLocale.isSet()) {
            for(Vest v : poslednjeVesti){
                v.setSlikaVesti(v.getSlikaVesti().substring(3));
            }
            persistentLocale.set(new Locale("sr"));
        }
    }
    
    public Object onActionFromPovratak(){
        pretrazeneVesti = null;
        poslednjeVesti = vestDAO.getListaPoslednjihObjavljenihVesti().subList(0, 11);
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPoslednjihVesti).addRender(zonaPretrazenihVesti);
        }
        return zahtev.isXHR() ? zonaPoslednjihVesti.getBody() : null;
    }
    
    Object onSuccessFromPretraga1(){
        pretrazi(pretraga.getDatum1(), pretraga.getDatum2(), kid);
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPretrazenihVesti).addRender(zonaPoslednjihVesti);
        }
        return zahtev.isXHR() ? zonaPretrazenihVesti.getBody() : null;
    }
    
    Object onSuccessFromPretraga2(){
        pretrazi(pretraga.getDatum1(), pretraga.getDatum2(), kid2);
        return zahtev.isXHR() ? zonaPretrazenihVesti.getBody() : null;
    }
    
    public Object onIzaberiPoslednjuVest(int index){
        izvest.setIzabranaVest(poslednjeVesti.get(index));
        return izvest;
    }
    
    public Object onIzaberiPretrazenuVest(Vest prVest){
        izvest.setIzabranaVest(prVest);
        return izvest;
    }
    
    public Object onIzmeniPretrazenuVest(Vest prVest){
        unosIzmenaVesti.setIzabranaVest(prVest);
        return unosIzmenaVesti;
    }
    
    public Object onNovaVest(){
        unosIzmenaVesti.setIzabranaVest(new Vest());
        return unosIzmenaVesti;
    }
    
    @CommitAfter
    public Object onActionFromObrisiPretrazenuVest(Vest prVest){
        pretrazeneVesti.remove(prVest);
        vestDAO.obrisiVest(prVest.getId());
        return zahtev.isXHR() ? zonaPretrazenihVesti.getBody() : null;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get/Set i util Metode">
    public boolean getPretragaAktivirana(){
        return pretrazeneVesti != null;
    }
    
    public void setPretragaAktiviranaIzVesti(boolean pretragaAktiviranaIzVesti){
        this.pretragaAktiviranaIzVesti = pretragaAktiviranaIzVesti;
    }

    public Date getIzabraniDatum1() {
        return izabraniDatum1;
    }

    public void setIzabraniDatum1(Date izabraniDatum1) {
        this.izabraniDatum1 = izabraniDatum1;
    }

    public Date getIzabraniDatum2() {
        return izabraniDatum2;
    }

    public void setIzabraniDatum2(Date izabraniDatum2) {
        this.izabraniDatum2 = izabraniDatum2;
    }

    public Kategorija getIzabranaKategorija() {
        return izabranaKategorija;
    }

    public void setIzabranaKategorija(Kategorija izabranaKategorija) {
        this.izabranaKategorija = izabranaKategorija;
    }
    
    public boolean getPretragaPrazna(){
        return pretrazeneVesti.isEmpty();
    }
  
    public Vest poslednjaVest(Integer brVesti){
        return poslednjeVesti.get(brVesti);
    }

    public Format getFormat()
    {
        return new SimpleDateFormat(format);
    }

    private void pretrazi(Date datum1, Date datum2, Kategorija kategorija) {
        if(datum1.before(datum2)){
            pretrazeneVesti = vestDAO.getListaPretrazenihVesti
        (datum1, datum2, kategorija);
        } else {
            pretrazeneVesti = vestDAO.getListaPretrazenihVesti
        (datum2, datum1, kategorija);
        }

    }
    
    public boolean getPrikazEditOpcije(){
        if(ulogovaniKorisnik!=null&&ulogovaniKorisnik.getUloga()!=null)
            return ulogovaniKorisnik.getUloga().equals(Uloga.Urednik);
        else return false;
    }
    
    public boolean getPrikazDeleteOpcije(){
        if(ulogovaniKorisnik!=null&&ulogovaniKorisnik.getUloga()!=null)
            return ulogovaniKorisnik.getUloga().equals(Uloga.Urednik)
                    ||ulogovaniKorisnik.getUloga().equals(Uloga.Administrator);
        else return false;
    }
    
    public boolean getUlogovan(){
        return ulogovaniKorisnik!=null&&ulogovaniKorisnik.getEmail()!=null;
    }
    
    public boolean getUrednik(){
        return ulogovaniKorisnik!=null&&ulogovaniKorisnik.getUloga().equals(Uloga.Urednik);
    }
    //</editor-fold>

}