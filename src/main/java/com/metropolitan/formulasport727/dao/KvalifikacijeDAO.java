/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kvalifikacije;
import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Kvalifikacije
 * @author Miroslav Stipanović 727
 */
public interface KvalifikacijeDAO {
    /**
     * metoda vraća sve kvalifikacije iz baze
     * @return 
     */
    public List<Kvalifikacije> getListaSvihKvalifikacija();
    /**
     * metoda vraća sve kvalifikacije određene velike nagrade navedene u parametru
     * @param velikaNagrada
     * @return 
     */
    public List<Kvalifikacije> getListaSvihKvalifikacijaVelikeNagrade(VelikaNagrada velikaNagrada);
    /**
     * metoda vraća sve kvalifikacije određene sezone navedene u parametru
     * @param sezona
     * @return 
     */
    public List<Kvalifikacije> getListaSvihKvalifikacijaSezone(Sezona sezona);
    /**
     * metoda vraća kvalifikacije iz baze čiji je atribut id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public Kvalifikacije getKvalifikacijeById(Long id);
    /**
     * metoda dodaje nove kvalifikacije u bazu ili menja već postojeće
     * @param kvalifikacije 
     */
    public void dodajIliIzmeniKvalifikacije(Kvalifikacije kvalifikacije);
    /**
     * metoda briše kvalifikacije iz baze čiji je atribut id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiKvalifikacije(Long id);
}
