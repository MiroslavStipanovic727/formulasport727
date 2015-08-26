/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Korisnik;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Korisnik
 * @author Miroslav Stipanović 727
 */
public interface KorisnikDAO {
    
    /**
     * metoda vraća sve korisnike iz baze
     * @return 
     */
    public List<Korisnik> getListaSvihKorisnika();
    /**
     * metoda vraća korisnika čiji je atribut id jednak parametru id
     * @param id
     * @return 
     */
    public Korisnik getKorisnikById(Long id);
    /**
     * metoda vraća korisnika čiji je atribut email jednak parametru email
     * @param email
     * @return 
     */
    public Korisnik getKorisnikByEmail(String email);
    /**
     * metoda dodaje novog korisnika u bazu ili menja već postojećeg u bazi. 
     * @param korisnik 
     */
    public void dodajIliIzmeniKorisnika(Korisnik korisnik);
    /**
     * metoda dodaje novog korisnika u bazu 
     * @param korisnik 
     */
    public void dodajKorisnika(Korisnik korisnik);
    /**
     * metoda briše korisnika iz baze čiji je atribut email jednak parametru
     * @param email 
     */
    public void obrisiKorisnika(String email);
    /**
     * metoda koja služi za proveru da li u bazi postoji korisnik sa emailom ili 
     * korisničkim imenom navedenim u parametru, koji ima šifru navedenu u parametru.
     * Vraća pronađenog korisnika ako postoji, ili null ako ne postoji
     * @param emailIliKorisnickoIme
     * @param sifra
     * @return 
     */
    public Korisnik proveriKorisnika(String emailIliKorisnickoIme, String sifra);
    /**
     * metoda koja dodaje novog korisnika u bazu a potom vraća tako dobijenog korisnika iz baze
     * @param korisnik
     * @return 
     */
    public Korisnik registrujKorisnika(Korisnik korisnik);
    /**
     * metoda koja proverava da li email naveden u parametru postoji u bazi
     * @param email
     * @return 
     */
    public boolean proveraDaLiPostojiEmail(String email);
    /**
     * metoda koja proverava da li korisničko ime navedeno u parametru postoji u bazi
     * @param korisnickoIme
     * @return 
     */
    public boolean proveraDaLiPostojiKorisnickoIme(String korisnickoIme);
}
