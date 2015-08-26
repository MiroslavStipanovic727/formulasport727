/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.Vest;
import java.util.Date;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Vest
 * @author Miroslav Stipanović 727
 */
public interface VestDAO {
    /**
     * metoda vraća sve vesti iz baze
     * @return 
     */
    public List<Vest> getListaSvihVesti();
    /**
     * metoda vraća vest iz baze čiji je atribut id jednak navedenom u parametru
     * @param id
     * @return 
     */
    public Vest getVestById(Long id);
    /**
     * metoda dodaje novu vest u bazu ili menja već postojeću
     * @param vest 
     */
    public void dodajIliIzmeniVest(Vest vest);
    /**
     * metoda briše vest iz baze čiji je atribut id jednak navedenom u parametru
     * @param id 
     */
    public void obrisiVest(Long id);
    /**
     * metoda vraća sve vesti određene kategorije navedene u parametru koje se nalaze
     * između početnog i završnog datuma, navedenih u parametru. Ukoliko je završni
     * datum naveden u parametru u budućnosti, metoda vraća sve vesti između početnog
     * datuma i trenutnog vremena
     * @param pocetni
     * @param zavrsni
     * @param kategorija
     * @return 
     */
    public List<Vest> getListaPretrazenihVesti(Date pocetni, Date zavrsni, Kategorija kategorija);
    /**
     * metoda vraća sve vesti čiji je atribut vreme objave manji od trenunog vremena,
     * odnosno sve vesti objavljene u prošlosti
     * @return 
     */
    public List<Vest> getListaPoslednjihObjavljenihVesti();
}
