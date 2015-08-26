/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Trka;
import com.metropolitan.formulasport727.entities.TrkackiRezultat;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Trkački Rezultat
 * @author Miroslav Stipanović 727
 */
public interface TrkackiRezultatDAO {
    /**
     * metoda vraća listu svih trkačkih rezultata iz baze
     * @return 
     */
    public List<TrkackiRezultat> getListaSvihTrkackihRezultata();
    /**
     * metoda vraća listu svih trkački rezultata određene trke navedene u parametru
     * @param trka
     * @return 
     */
    public List<TrkackiRezultat> getListaSvihTrkackihRezultataTrke(Trka trka);
    /**
     * metoda vraća trkački rezultat čiji je atribut id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public TrkackiRezultat getTrkackiRezultatById(Long id);
    /**
     * metoda dodaje nov trkački rezultat u bazu ili menja već postojeći
     * @param trkackiRezultat 
     */
    public void dodajIliIzmeniTrkackiRezultat(TrkackiRezultat trkackiRezultat);
    /**
     * metoda briše trkački rezultat čiji je atribut id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiTrkackiRezultat(Long id);
}
