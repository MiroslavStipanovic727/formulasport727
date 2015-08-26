/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Velika Nagrada
 * @author Miroslav Stipanović 727
 */
public interface VelikaNagradaDAO {
    /**
     * metoda vraća sve velike nagrade iz baze
     * @return 
     */
    public List<VelikaNagrada> getListaSvihVelikihNagrada();
    /**
     * metoda vraća sve velike nagrade određene sezone navedene u parametru
     * @param sezona
     * @return 
     */
    public List<VelikaNagrada> getListaSvihVelikihNagradaSezone(Sezona sezona);
    /**
     * metoda vraća veliku nagradu čiji je id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public VelikaNagrada getVelikaNagradaById(Long id);
    /**
     * metoda dodaje novu veliku nagradu u bazu ili menja već postojeću
     * @param velikaNagrada 
     */
    public void dodajIliIzmeniVelikuNagradu(VelikaNagrada velikaNagrada);
    /**
     * metoda briše veliku nagradu čiji je id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiVelikuNagradu(Long id);
}
