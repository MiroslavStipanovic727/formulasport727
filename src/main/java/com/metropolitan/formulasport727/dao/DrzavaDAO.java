/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Drzava;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Država
 * @author Miroslav Stipanović 727
 */
public interface DrzavaDAO {
    /**
     * metoda vraća sve države iz baze
     * @return 
     */
    public List<Drzava> getListaSvihDrzava();
    /**
     * metoda vraća konkretnu državu iz baze na osnovu njenog atributa id
     * @param id
     * @return 
     */
    public Drzava getDrzavaById(Long id);
    /**
     * metoda dodaje državu iz parametra u bazu ili menja već postojeću
     * @param drzava 
     */
    public void dodajIliIzmeniDrzavu(Drzava drzava);
    /**
     * metoda briše državu na osnovu njenog atributa id
     * @param id 
     */
    public void obrisiDrzavu(Long id);
}
