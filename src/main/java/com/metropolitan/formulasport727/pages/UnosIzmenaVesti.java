/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages;

import com.metropolitan.formulasport727.dao.KategorijaDAO;
import com.metropolitan.formulasport727.dao.VestDAO;
import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.entities.Vest;
import com.metropolitan.formulasport727.services.ProtectedPage;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
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
 * za unos novih ili izmenu starih vesti
 * @author Miroslav Stipanović 727
 */
@ProtectedPage
@RolesAllowed(value={"Urednik"})
public class UnosIzmenaVesti {
    
    @Inject
    private Request zahtev;
    @Inject
    private AjaxResponseRenderer psvRR;
    @InjectComponent
    private Zone zonaNaslova;
    @InjectComponent
    private Zone zonaForme;
    
    @Persist
    private Vest izabranaVest;
    
    @Property
    @Persist
    private String naslov;
    @Property
    @Persist
    private String tekst;
    @Property
    @Persist
    private String slika;
    @Property
    @Persist
    private boolean kasnije;
    @Property
    private Date datum;
    @Property
    private int satObjave;
    @Property
    private int minutObjave;
    @Property
    private int sekundObjave;
    
    @Property
    private Kategorija izborKategorije;
    @Property
    @Persist
    private List<Kategorija> kategorije;
    @Inject
    private KategorijaDAO kaDAO;
    
    @Inject
    private VestDAO veDAO;
    
    @SessionState
    private Korisnik ulogovaniKorisnik;
    
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
    
    void onActivate(){
        kategorije = kaDAO.getListaSvihKategorija();
        if(getIzmenaVesti()){
            izborKategorije = izabranaVest.getKatId();
            naslov = izabranaVest.getNaslovVesti();
            slika = izabranaVest.getSlikaVesti();
            tekst = izabranaVest.getTekstVesti();
            Calendar c = Calendar.getInstance();
            c.setTime(izabranaVest.getVremeObjave());
            datum = c.getTime();
            satObjave = c.get(Calendar.HOUR_OF_DAY);
            minutObjave = c.get(Calendar.MINUTE);
            sekundObjave = c.get(Calendar.SECOND);
        }
    }
    
    Object onSuccessFromFormaKasnije(){
        return zahtev.isXHR() ? zonaForme.getBody() : null;
    }
    
    @CommitAfter
    Object onSuccessFromFormaVesti(){
        Vest novaVest = new Vest();
        if(getIzmenaVesti()){
            izabranaVest.setNaslovVesti(naslov);
            izabranaVest.setTekstVesti(tekst);
            izabranaVest.setSlikaVesti(slika);
            izabranaVest.setKatId(izborKategorije);
            if(getObjaviKasnije()){
                Calendar c = Calendar.getInstance();
                c.setTime(datum);
                c.add(Calendar.HOUR_OF_DAY, satObjave);
                c.add(Calendar.MINUTE, minutObjave);
                c.add(Calendar.SECOND, sekundObjave);
                izabranaVest.setVremeObjave(c.getTime());
            } 
            veDAO.dodajIliIzmeniVest(izabranaVest);
            izabranaVest = new Vest();
        } else {
            novaVest.setNaslovVesti(naslov);
            novaVest.setTekstVesti(tekst);
            novaVest.setSlikaVesti(slika);
            novaVest.setKatId(izborKategorije);
            if(getObjaviKasnije()){
                Calendar c = Calendar.getInstance();
                c.setTime(datum);
                c.add(Calendar.HOUR_OF_DAY, satObjave);
                c.add(Calendar.MINUTE, minutObjave);
                c.add(Calendar.SECOND, sekundObjave);
                novaVest.setVremeObjave(c.getTime());
            } else {
                novaVest.setVremeObjave(Calendar.getInstance().getTime());
            }
            novaVest.setKorId(ulogovaniKorisnik);
            veDAO.dodajIliIzmeniVest(novaVest);
        }
        return Index.class;
    }
    
    Object onNovaVest(){
        izabranaVest = new Vest();
        izborKategorije = new Kategorija();
        naslov = "";
        slika = "";
        tekst = "";
        datum = Calendar.getInstance().getTime();
        satObjave = 0;
        minutObjave = 0;
        sekundObjave = 0;
        if(zahtev.isXHR()){
            psvRR.addRender(zonaForme).addRender(zonaNaslova);
        }
        return zahtev.isXHR() ? zonaForme.getBody() : null;
    }

    public Vest getIzabranaVest() {
        return izabranaVest;
    }

    public void setIzabranaVest(Vest izabranaVest) {
        this.izabranaVest = izabranaVest;
    }
    
    public boolean getIzmenaVesti(){
        return izabranaVest!=null&&izabranaVest.getId()!=null;
    }
    
    public boolean getObjaviKasnije(){
        return kasnije;
    }
}
