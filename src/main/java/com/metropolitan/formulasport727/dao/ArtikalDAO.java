/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Artikal;
import com.metropolitan.formulasport727.entities.TipArtikla;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Artikal
 * @author Miroslav Stipanović 727
 */
public interface ArtikalDAO {
    /**
     * metoda vraća sve artikle iz baze
     * @return 
     */
    public List<Artikal> getListaSvihArtikala();
    /**
     * metoda vraća sve artikle konkretnog tipa artikla definisanog u paremetrima
     * @param tipArtikla
     * @return 
     */
    public List<Artikal> getListaSvihArtikalaTipaArtikla(TipArtikla tipArtikla);
    /**
     * metoda vraća konkretan artikal iz baze na osnovu njegovog atributa id
     * @param id
     * @return 
     */
    public Artikal getArtikalById(Long id);
    /**
     * metoda dodaje artikal iz parametra u bazu ili menja već postojeći
     * @param artikal 
     */
    public void dodajIliIzmeniArtikal(Artikal artikal);
    /**
     * metoda briše artikal na osnovu njegovog atributa id
     * @param id 
     */
    public void obrisiArtikal(Long id);
}
