/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.DiskusionaGrupa;
import com.metropolitan.formulasport727.entities.Diskusija;
import com.metropolitan.formulasport727.entities.Korisnik;
import java.util.List;

/**
 * Interfejs za pristup tabeli baze podataka Diskusija
 * @author Miroslav Stipanović 727
 */
public interface DiskusijaDAO {
    /**
     * metoda vraća sve diskusije iz baze
     * @return 
     */
    public List<Diskusija> getListaSvihDiskusija();
    /**
     * metoda vraća sve diskusije iz baze sa atributom odobrena u stanju true
     * @return 
     */
    public List<Diskusija> getListaSvihOdobrenihDiskusija();
    /**
     * metoda vraća sve diskusije iz baze sa atributom odobrena u stanju false
     * @return 
     */
    public List<Diskusija> getListaSvihNeodobrenihDiskusija();
    /**
     * metoda vraća sve diskusije konkretne diskusione grupe navedene u parametrima
     * @param diskusionaGrupa
     * @return 
     */
    public List<Diskusija> getListaSvihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa);
    /**
     * metoda vraća sve diskusije konkretne diskusione grupe navedene u parametrima
     * sa atributom odobrena u stanju true
     * @param diskusionaGrupa
     * @return 
     */
    public List<Diskusija> getListaSvihOdobrenihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa);
    /**
     * metoda vraća sve diskusije konkretne diskusione grupe navedene u parametrima
     * sa atributom odobrena u stanju false
     * @param diskusionaGrupa
     * @return 
     */
    public List<Diskusija> getListaSvihNeodobrenihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa);
    /**
     * metoda vraća sve diskusije otvorene od strane korisnika navedenog u parametrima
     * @param korisnik
     * @return 
     */
    public List<Diskusija> getListaSvihDiskusijaKorisnika(Korisnik korisnik);
    /**
     * metoda vraća sve diskusije otvorene od strane korisnika navedenog u parametrima
     * sa atributom odobrena u stanju true 
     * @param korisnik
     * @return 
     */
    public List<Diskusija> getListaSvihOdobrenihDiskusijaKorisnika(Korisnik korisnik);
    /**
     * metoda vraća sve diskusije konkretne diskusione grupe navedene u parametrima
     * otvorene od strane korisnika navedenog u parametrima
     * @param diskusionaGrupa
     * @param korisnik
     * @return 
     */
    public List<Diskusija> getListaSvihDiskusijaKorisnikaUDiskusionojGrupi(DiskusionaGrupa diskusionaGrupa, Korisnik korisnik);
    /**
     * metoda vraća sve diskusije konkretne diskusione grupe navedene u parametrima
     * otvorene od strane korisnika navedenog u parametrima
     * sa atributom odobrena u stanju true 
     * @param diskusionaGrupa
     * @param korisnik
     * @return 
     */
    public List<Diskusija> getListaSvihOdobrenihDiskusijaKorisnikaUDiskusionojGrupi(DiskusionaGrupa diskusionaGrupa, Korisnik korisnik);
    /**
     * metoda vraća sve diskusije koje u naslovu ili opisu sadrže jednu ili više
     * ključnih reči navedenih u parametrima
     * @param kljucneReci
     * @return 
     */
    public List<Diskusija> getListaDiskusijaPoKljucnimRecima(String kljucneReci);
    /**
     * metoda vraća konkretnu diskusiju iz baze na osnovu njenog atributa id
     * @param id
     * @return 
     */
    public Diskusija getDiskusijaById(Long id);
    /**
     * metoda dodaje diskusiju iz parametra u bazu ili menja već postojeću
     * @param diskusija 
     */
    public void dodajIliIzmeniDiskusiju(Diskusija diskusija);
    /**
     * metoda briše diskusiju na osnovu njenog atributa id
     * @param id 
     */
    public void obrisiDiskusiju(Long id);
    /**
     * metoda vraća broj diskusija konkretne diskusione grupe navedene u parametru
     * @param diskusionaGrupa
     * @return 
     */
    public int getBrojDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa);
    /**
     * metoda vraća broj diskusija konkretne diskusione grupe navedene u parametru
     * sa atributom odobrena u stanju true 
     * @param diskusionaGrupa
     * @return 
     */
    public int getBrojOdobrenihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa);
    /**
     * metoda vraća broj diskusija konkretne diskusione grupe navedene u parametru
     * sa atributom odobrena u stanju false 
     * @param diskusionaGrupa
     * @return 
     */
    public int getBrojNeodobrenihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa);
}
