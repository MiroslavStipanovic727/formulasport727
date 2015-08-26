/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages;

import com.metropolitan.formulasport727.dao.KorisnikDAO;
import com.metropolitan.formulasport727.dao.KorpaDAO;
import com.metropolitan.formulasport727.data.Uloga;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.entities.Korpa;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje stranice
 * za prijavu korisnika na sajt
 * @author Miroslav Stipanović 727
 */
public class Login {
    
    @SessionState
    private Korisnik ulogovaniKorisnik;
    @Inject
    private KorisnikDAO korisnikDAO;
    @Component
    private Form login;
    @Property
    private String emailIliKIme;
    @Property
    private String sifra;
    @Inject
    private KorpaDAO korpaDAO;
    @Property
    @SessionState
    private Korpa korpa;
    
    Object onActivate() {
        if (ulogovaniKorisnik!=null && ulogovaniKorisnik.getEmail() != null) {
            return Index.class;
        }
        return null;
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
    
    @CommitAfter
    Object onSuccessFromLogin(){
        String loginSifra = getMD5Hash(sifra);
        Korisnik k = korisnikDAO.proveriKorisnika(emailIliKIme, loginSifra);
        if(k!=null){
            ulogovaniKorisnik = k;
            System.out.println("Uspešno logovanje na sistem korisnika "+k.getKorisnickoIme());
            Korpa staraKorpa = korpaDAO.getKorpaKorisnika(ulogovaniKorisnik);
            if(staraKorpa!=null)
                korpaDAO.obrisiKorpu(staraKorpa.getId());
            korpa = new Korpa();
            korpa.setKorId(ulogovaniKorisnik);
            korpaDAO.dodajIliIzmeniKorpu(korpa);
            korpa = korpaDAO.getKorpaKorisnika(ulogovaniKorisnik);
            if(ulogovaniKorisnik.getUloga() == Uloga.Moderator) {
                return DiskusioneGrupe.class;
            } else if(ulogovaniKorisnik.getUloga() == Uloga.Administrator) {
                return AdminPanel.class;
            } else 
                return Index.class;
        }
        else {
            login.recordError("Uneti korisnik ne postoji ili je pogrešna šifra");
            System.out.println("Neuspešno logovanje");
            return null;
        }
    }
    
}
