/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Trening;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Trening
 * @author Miroslav Stipanović 727
 */
public interface TreningDAO {
    /**
     * metoda vraća sve treninge iz baze
     * @return 
     */
    public List<Trening> getListaSvihTreninga();
    /**
     * metoda vraća sve treninge određene velike nagrade navedene u parametru
     * @param velikaNagrada
     * @return 
     */
    public List<Trening> getListaSvihTreningaVelikeNagrade(VelikaNagrada velikaNagrada);
    /**
     * metoda vraća sve treninge određene sezone navedene u parametru
     * @param sezona
     * @return 
     */
    public List<Trening> getListaSvihTreningaSezone(Sezona sezona);
    /**
     * metoda vraća trening čiji je atribut id jednak navedenom u parametru 
     * @param id
     * @return 
     */
    public Trening getTreningById(Long id);
    /**
     * metoda dodaje nov trening u bazu ili menja već postojeći
     * @param trening 
     */
    public void dodajIliIzmeniTrening(Trening trening);
    /**
     * metoda briše trening čiji je atribut id jednak navedenom u parametru 
     * @param id 
     */
    public void obrisiTrening(Long id);
}
