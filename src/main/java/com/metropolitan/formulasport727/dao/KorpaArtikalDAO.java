/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Artikal;
import com.metropolitan.formulasport727.entities.KorpaArtikal;
import com.metropolitan.formulasport727.entities.Korpa;
import java.util.List;

/**
 *Interfejs za pristup tabeli baze podataka Korpa Artikal
 * @author Miroslav Stipanović 727
 */
public interface KorpaArtikalDAO {
    /**
     * metoda vraća sve Korpa Artikle iz baze
     * @return 
     */
    public List<KorpaArtikal> getListaSvihKorpaArtikala();
    /**
     * metoda vraća sve Korpa Artikle određene korpe navedene u parametru
     * @param korpa
     * @return 
     */
    public List<KorpaArtikal> getListaSvihKorpaArtikalaKorpe(Korpa korpa);
    /**
     * metoda vraća sve Korpa Artikle određene korpe navedene u parametru, grupisane
     * po artiklu tako da se u slučaju pojave jednog artikla više puta vraća samo jedan
     * red artikla
     * @param korpa
     * @return 
     */
    public List<KorpaArtikal> getListaSvihJedinstvenihKorpaArtikalaKorpe(Korpa korpa);
    /**
     * metoda vraća sve Korpa Artikle određene korpe navedene u parametru koji 
     * sadrže artikal naveden u parametru
     * @param korpa
     * @param artikal
     * @return 
     */
    public List<KorpaArtikal> getListaSvihKorpaArtikalaKorpeSaArtiklom(Korpa korpa, Artikal artikal);
    /**
     * metoda vraća Korpa Artikal iz baze čiji je atribut id naveden u parametru
     * @param id
     * @return 
     */
    public KorpaArtikal getKorpaArtikalById(Long id);
    /**
     * metoda dodaje novi Korpa Artikal u bazu ili menja već postojeći
     * @param korpaArtikal 
     */
    public void dodajIliIzmeniKorpuArtikal(KorpaArtikal korpaArtikal);
    /**
     * metoda briše Korpa Artikal iz baze čiji je atribut id naveden u parametru
     * @param id 
     */
    public void obrisiKorpuArtikal(Long id);
    /**
     * metoda vraća broj redova Korpa Artikala određenog artikla navedenog u parametru 
     * u korpi navedenoj u parametru
     * @param korpa
     * @param artikal
     * @return 
     */
    public int brojPojavljivanjaArtiklaUKorpi(Korpa korpa, Artikal artikal);
}
