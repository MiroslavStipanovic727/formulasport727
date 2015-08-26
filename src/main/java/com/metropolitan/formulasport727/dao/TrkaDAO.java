/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Trka;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Trka
 * @author Miroslav Stipanović 727
 */
public interface TrkaDAO {
    /**
     * metoda vraća sve trke iz baze
     * @return 
     */
    public List<Trka> getListaSvihTrka();
    /**
     * metoda vraća sve trke određene velike nagrade navedene u parametru
     * @param velikaNagrada
     * @return 
     */
    public List<Trka> getListaSvihTrkaVelikeNagrade(VelikaNagrada velikaNagrada);
    /**
     * metoda vraća sve trke određene sezone navedene u parametru
     * @param sezona
     * @return 
     */
    public List<Trka> getListaSvihTrkaSezone(Sezona sezona);
    /**
     * metoda vraća trku iz baze čiji je parametar id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public Trka getTrkaById(Long id);
    /**
     * metoda dodaje novu trku u bazu ili menja već postojeću
     * @param trka 
     */
    public void dodajIliIzmeniTrku(Trka trka);
    /**
     * metoda briše trku iz baze čiji je parametar id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiTrku(Long id);
}
