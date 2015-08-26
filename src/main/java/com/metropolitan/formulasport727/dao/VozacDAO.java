/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Vozac;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Vozac
 * @author Miroslav Stipanović 727
 */
public interface VozacDAO {
    /**
     * metoda vraća sve vozače iz baze
     * @return 
     */
    public List<Vozac> getListaSvihVozaca();
    /**
     * metoda vraća sve vozače određene sezone navedene u parametru
     * @param sezona
     * @return 
     */
    public List<Vozac> getListaSvihVozacaSezone(Sezona sezona);
    /**
     * metoda vraća vozača iz baze čiji je atribut id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public Vozac getVozacById(Long id);
    /**
     * metoda vraća vozača iz baze koji je poslednji dodat u bazu
     * @return 
     */
    public Vozac getLastVozac();
    /**
     * metoda dodaje novog vozača u bazu ili menja već postojećeg
     * @param vozac 
     */
    public void dodajIliIzmeniVozaca(Vozac vozac);
    /**
     * metoda briše vozača iz baze čiji je atribut id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiVozaca(Long id);
}
