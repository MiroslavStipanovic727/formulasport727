/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Tim;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Tim
 * @author Miroslav Stipanović 727
 */
public interface TimDAO {
    /**
     * metoda vraća sve timove iz baze
     * @return 
     */
    public List<Tim> getListaSvihTimova();
    /**
     * metoda vraća sve timove sezone navedene u parametru 
     * @param sezona
     * @return 
     */
    public List<Tim> getListaSvihTimovaSezone(Sezona sezona);
    /**
     * metoda vraća tim iz baze čiji je atribut id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public Tim getTimById(Long id);
    /**
     * metoda vraća tim iz baze koji je poslednji dodat u bazu
     * @return 
     */
    public Tim getLastTim();
    /**
     * metoda dodaje nov tim u bazu ili menja već postojeći
     * @param tim 
     */
    public void dodajIliIzmeniTim(Tim tim);
    /**
     * metoda briše tim iz baze čiji je atribut id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiTim(Long id);
}
