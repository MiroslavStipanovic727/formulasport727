/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.DiskusionaGrupa;
import com.metropolitan.formulasport727.entities.Kategorija;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Diskusiona Grupa
 * @author Miroslav Stipanović 727
 */
public interface DiskusionaGrupaDAO {
    /**
     * metoda vraća sve diskusione grupe iz baze
     * @return 
     */
    public List<DiskusionaGrupa> getListaSvihDiskusionihGrupa();
    /**
     * metoda vraća sve diskusione grupe kategorije navedene u parametru
     * @param kategorija
     * @return 
     */
    public List<DiskusionaGrupa> getListaSvihDiskusionihGrupaKategorije(Kategorija kategorija);
    /**
     * metoda vraća konkretnu diskusionu grupu iz baze na osnovu njenog atributa id
     * @param id
     * @return 
     */
    public DiskusionaGrupa getDiskusionaGrupaById(Long id);
    /**
     * metoda dodaje diskusionu grupu iz parametra u bazu ili menja već postojeću
     * @param diskusionaGrupa 
     */
    public void dodajIliIzmeniDiskusionuGrupu(DiskusionaGrupa diskusionaGrupa);
    /**
     * metoda briše diskusionu grupu na osnovu njenog atributa id
     * @param id 
     */
    public void obrisiDiskusionuGrupu(Long id);
}
