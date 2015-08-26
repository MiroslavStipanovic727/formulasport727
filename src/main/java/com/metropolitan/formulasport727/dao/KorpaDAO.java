/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Korpa;
import com.metropolitan.formulasport727.entities.Korisnik;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Korpa
 * @author Miroslav Stipanović 727
 */
public interface KorpaDAO {
    /**
     * metoda vraća sve korpe iz baze
     * @return 
     */
    public List<Korpa> getListaSvihKorpi();
    /**
     * metoda vraća sve korpe određenog korisnika navedenog u parametru
     * @param korisnik
     * @return 
     */
    public List<Korpa> getListaSvihKorpiKorisnika(Korisnik korisnik);
    /**
     * metoda vraća korpu iz baze čiji je atribut id jednak parametru 
     * @param id
     * @return 
     */
    public Korpa getKorpaById(Long id);
    /**
     * metoda vraća korpu iz baze određenog korisnika navedenog u parametru
     * @param korisnik
     * @return 
     */
    public Korpa getKorpaKorisnika(Korisnik korisnik);
    /**
     * metoda dodaje novu korpu u bazu ili menja već postojeću
     * @param korpa 
     */
    public void dodajIliIzmeniKorpu(Korpa korpa);
    /**
     * metoda briše korpu iz baze čije je atribut id jednak parametru
     * @param id 
     */
    public void obrisiKorpu(Long id);
}
