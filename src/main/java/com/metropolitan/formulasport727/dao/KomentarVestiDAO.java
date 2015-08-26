/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.KomentarVesti;
import com.metropolitan.formulasport727.entities.Vest;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Komentar Vesti
 * @author Miroslav Stipanović 727
 */
public interface KomentarVestiDAO {
    
    /**
     * metoda vraća sve komentare vesti iz baze
     * @return 
     */
    public List<KomentarVesti> getListaSvihKomentaraVesti();
    /**
     * metoda vraća sve komentare vesti konkretne vesti navedene u parametru
     * @param vest
     * @return 
     */
    public List<KomentarVesti> getListaSvihKomentaraKonkretneVesti(Vest vest);
    /**
     * metoda vraća komentar vesti na osnovu njegovog atributa id
     * @param id
     * @return 
     */
    public KomentarVesti getKomentarVestiById(Long id);
    /**
     * metoda dodaje nov komentar u bazu ili menja već postojeći komentar u bazi. 
     * Komentar je naveden u parametru 
     * @param komentarVesti 
     */
    public void dodajIliIzmeniKomentarVesti(KomentarVesti komentarVesti);
    /**
     * metoda briše komentar vesti iz baze na osnovu njegovog atributa id navedenog u parametru
     * @param id 
     */
    public void obrisiKomentarVesti(Long id);
}
