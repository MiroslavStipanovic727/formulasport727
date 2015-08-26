/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Klasifikacija;
import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Tim;
import com.metropolitan.formulasport727.entities.Vozac;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Klasifikacija
 * @author Miroslav Stipanović 727
 */
public interface KlasifikacijaDAO {
    /**
     * metoda vraća sve klasifikacije iz baze
     * @return 
     */
    public List<Klasifikacija> getListaSvihKlasifikacija();
    /**
     * metoda vraća sve klasifikacije iz baze čiji atribut Vozac postoji (nije null)
     * @return 
     */
    public List<Klasifikacija> getListaSvihKlasifikacijaVozaca();
    /**
     * metoda vraća sve klasifikacije iz baze čiji atribut Tim postoji (nije null)
     * @return 
     */
    public List<Klasifikacija> getListaSvihKlasifikacijaTimova();
    /**
     * metoda vraća sve klasifikacije sezone navedene u parametru
     * @param sezona
     * @return 
     */
    public List<Klasifikacija> getListaSvihKlasifikacijaSezone(Sezona sezona);
    /**
     * metoda vraća sve klasifikacije sezone navedene u parametru čiji atribut Vozac postoji (nije null)
     * @param sezona
     * @return 
     */
    public List<Klasifikacija> getListaSvihKlasifikacijaVozacaSezone(Sezona sezona);
    /**
     * metoda vraća sve klasifikacije sezone navedene u parametru čiji atribut Tim postoji (nije null)
     * @param sezona
     * @return 
     */
    public List<Klasifikacija> getListaSvihKlasifikacijaTimovaSezone(Sezona sezona);
    /**
     * metoda vraća konkretnu klasifikaciju na osnovu njenog atributa id navedenog u parametru
     * @param id
     * @return 
     */
    public Klasifikacija getKlasifikacijaById(Long id);
    /**
     * metoda vraća konkretnu klasifikaciju na osnovu atributa vozač navedenog u parametru
     * @param vozac
     * @return 
     */
    public Klasifikacija getKlasifikacijaByVozac(Vozac vozac);
    /**
     * metoda vraća konkretnu klasifikaciju na osnovu atributa tim navedenog u parametru
     * @param tim
     * @return 
     */
    public Klasifikacija getKlasifikacijaByTim(Tim tim);
    /**
     * metoda vraća konkretnu klasifikaciju koja je poslednja uneta u bazu
     * @return 
     */
    public Klasifikacija getLastKlasifikacija();
    /**
     * metoda dodaje klasifikaciju navedenu u parametru u bazu
     * @param klasifikacija 
     */
    public void dodajIliIzmeniKlasifikaciju(Klasifikacija klasifikacija);
    /**
     * metoda dodaje klasifikaciju navedenu u parametru u bazu ili menja već postojeću 
     * @param klasifikacija 
     */
    public void izmeniKlasifikaciju(Klasifikacija klasifikacija);
    /**
     * metoda briše klasifikaciju navedenu u parametru iz baze
     * @param id 
     */
    public void obrisiKlasifikaciju(Long id);
    
}
