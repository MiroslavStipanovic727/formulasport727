/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages;

import com.metropolitan.formulasport727.dao.KategorijaDAO;
import com.metropolitan.formulasport727.dao.KorisnikDAO;
import com.metropolitan.formulasport727.dao.KorpaDAO;
import com.metropolitan.formulasport727.data.Uloga;
import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.entities.Korpa;
import java.util.List;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.kaptcha.components.KaptchaImage;

/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje stranice
 * za Registraciju korisnika na sajt
 * @author Miroslav Stipanović 727
 */
public class Registracija {
    
    @Property
    private Korisnik korisnik;
    @Property
    private Kategorija omiljenaKategorija;
    @SessionState
    private Korisnik ulogovaniKorisnik;
    @Inject
    private KorisnikDAO koDAO;
    @Component
    private BeanEditForm registracijaKorisnika;
    @Inject
    private KategorijaDAO kaDAO;
    @Property
    private List<Kategorija> kategorije;
    @Inject
    private KorpaDAO korpaDAO;
    @Property
    @SessionState
    private Korpa korpa;
    
    @Component
    @Property
    private KaptchaImage kaptchaImage;
    @Property
    private String cap;
    
    public ValueEncoder getEncoder(){
        return new ValueEncoder<Kategorija>(){

            @Override
            public String toClient(Kategorija k) {
                return String.valueOf(k.getId());
            }

            @Override
            public Kategorija toValue(String string) {
                Kategorija kategorija=kaDAO.getKategorijaById(Long.parseLong(string));
                return kategorija;
            }
            
        };
    }
    
    public String getMD5Hash(String yourString) {
        try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(yourString.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
    
    void onActivate(){
        kategorije = kaDAO.getListaSvihKategorija();
    }
    
    @CommitAfter
    Object onSuccess(){
        if (koDAO.proveraDaLiPostojiEmail(korisnik.getEmail())) {
            registracijaKorisnika.recordError("Uneti email već postoji");
            return null;
        } else if(koDAO.proveraDaLiPostojiKorisnickoIme(korisnik.getKorisnickoIme())) {
            registracijaKorisnika.recordError("Uneto korisničko ime već postoji");
            return null;
        } else {
            String preHashSifra = korisnik.getSifra();
            korisnik.setSifra(getMD5Hash(preHashSifra));
            korisnik.setKatId(omiljenaKategorija);
            korisnik.setUloga(Uloga.Klijent);
            ulogovaniKorisnik = koDAO.registrujKorisnika(korisnik);
            korpa = new Korpa();
            korpa.setKorId(ulogovaniKorisnik);
            korpaDAO.dodajIliIzmeniKorpu(korpa);
            korpa = korpaDAO.getKorpaKorisnika(ulogovaniKorisnik);
            return Index.class;
        }
    }
    
}
