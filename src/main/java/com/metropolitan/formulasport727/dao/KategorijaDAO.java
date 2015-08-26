/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kategorija;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Kategorija
 * @author Miroslav Stipanović 727
 */
public interface KategorijaDAO {
    
    /**
     * metoda vraća sve kategorije iz baze
     * @return 
     */
    public List<Kategorija> getListaSvihKategorija();
    /**
     * metoda vraća konkretnu kategoriju iz baze na osnovu njenog atributa id
     * @param id
     * @return 
     */
    public Kategorija getKategorijaById(Long id);
    /**
     * metoda dodaje kategoriju iz parametra u bazu ili menja već postojeću
     * @param kategorija
     */
    public void dodajIliIzmeniKategoriju(Kategorija kategorija);
    /**
     * metoda briše kategoriju na osnovu njenog atributa id
     * @param id 
     */
    public void obrisiKategoriju(Long id);
}
