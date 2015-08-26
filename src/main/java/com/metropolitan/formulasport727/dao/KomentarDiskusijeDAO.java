/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.KomentarDiskusije;
import com.metropolitan.formulasport727.entities.Diskusija;
import com.metropolitan.formulasport727.entities.Korisnik;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Komentar Diskusije
 * @author Miroslav Stipanović 727
 */
public interface KomentarDiskusijeDAO {
    /**
     * metoda vraća sve komentare diskusija iz baze
     * @return 
     */
    public List<KomentarDiskusije> getListaSvihKomentaraDiskusije();
    /**
     * metoda vraća sve komentare diskusije navedene u parametru
     * @param diskusija
     * @return 
     */
    public List<KomentarDiskusije> getListaSvihKomentaraDiskusijeUDiskusiji(Diskusija diskusija);
    /**
     * metoda vraća sve komentare korisnika navedenog u parametru
     * @param korisnik
     * @return 
     */
    public List<KomentarDiskusije> getListaSvihKomentaraDiskusijeKorisnika(Korisnik korisnik);
    /**
     * metoda vraća sve komentare korisnika navedenog u parametru koji su postavljeni
     * u diskusiji navedenoj u parametru
     * @param diskusija
     * @param korisnik
     * @return 
     */
    public List<KomentarDiskusije> getListaSvihKomentaraDiskusijeKorisnikaUDiskusiji(Diskusija diskusija, Korisnik korisnik);
    /**
     * metoda vraća komentar diskusije na osnovu njegovog atributa id
     * @param id
     * @return 
     */
    public KomentarDiskusije getKomentarDiskusijeById(Long id);
    /**
     * metoda dodaje nov komentar u bazu ili menja već postojeći komentar u bazi. 
     * Komentar je naveden u parametru 
     * @param komentarDiskusije 
     */
    public void dodajIliIzmeniKomentarDiskusije(KomentarDiskusije komentarDiskusije);
    /**
     * metoda briše komentar diskusije iz baze na osnovu njegovog atributa id navedenog u parametru
     * @param id 
     */
    public void obrisiKomentarDiskusije(Long id);
}
