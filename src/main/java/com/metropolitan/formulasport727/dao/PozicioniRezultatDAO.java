/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kvalifikacije;
import com.metropolitan.formulasport727.entities.PozicioniRezultat;
import com.metropolitan.formulasport727.entities.Trening;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Pozicioni Rezultat
 * @author Miroslav Stipanović 727
 */
public interface PozicioniRezultatDAO {
    /**
     * metoda vraća sve pozicione rezultate iz baze
     * @return 
     */
    public List<PozicioniRezultat> getListaSvihPozicionihRezultata();
    /**
     * metoda vraća sve pozicione rezultate treninga navedenog u parametru
     * @param trening
     * @return 
     */
    public List<PozicioniRezultat> getListaSvihPozicionihRezultataTreninga(Trening trening);
    /**
     * metoda vraća sve pozicione rezultate kvalifikacija navedenih u parametru
     * @param kvalifikacije
     * @return 
     */
    public List<PozicioniRezultat> getListaSvihPozicionihRezultataKvalifikacija(Kvalifikacije kvalifikacije);
    /**
     * metoda vraća pozicioni rezultat čiji je atribut id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public PozicioniRezultat getPozicioniRezultatById(Long id);
    /**
     * metoda dodaje nov pozicioni rezultat u bazu ili menja već postojeći
     * @param pozicioniRezultat 
     */
    public void dodajIliIzmeniPozicioniRezultat(PozicioniRezultat pozicioniRezultat);
    /**
     * metoda briše pozicioni rezultat čiji je atribut id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiPozicioniRezultat(Long id);
}
