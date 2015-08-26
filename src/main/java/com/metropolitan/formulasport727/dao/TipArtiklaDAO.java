/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.TipArtikla;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Tip Artikla
 * @author Miroslav Stipanović 727
 */
public interface TipArtiklaDAO {
    /**
     * metoda vraća sve tipove artikla iz baze
     * @return 
     */
    public List<TipArtikla> getListaSvihTipovaArtikla();
    /**
     * metoda vraća tip artikla iz baze čiji je atribut id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public TipArtikla getTipArtiklaById(Long id);
    /**
     * metoda dodaje nov artikal u bazu ili menja već postojeći
     * @param tipArtikla 
     */
    public void dodajIliIzmeniTipArtikla(TipArtikla tipArtikla);
    /**
     * metoda briše tip artikla iz baze čiji je atribut id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiTipArtikla(Long id);
}
