/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.Sezona;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Sezona
 * @author Miroslav Stipanović 727
 */
public interface SezonaDAO {
    /**
     * metoda vraća sve sezone iz baze
     * @return 
     */
    public List<Sezona> getListaSvihSezona();
    /**
     * metoda vraća sve sezone kategorije navedene u parametru
     * @param kategorija
     * @return 
     */
    public List<Sezona> getListaSvihSezonaKategorije(Kategorija kategorija);
    /**
     * metoda vraća sezonu čiji je atribut id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public Sezona getSezonaById(Long id);
    /**
     * metoda dodaje novu sezonu u bazu ili menja već postojeću
     * @param sezona 
     */
    public void dodajIliIzmeniSezonu(Sezona sezona);
    /**
     * metoda briše sezonu čiji je atribut id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiSezonu(Long id);
}
