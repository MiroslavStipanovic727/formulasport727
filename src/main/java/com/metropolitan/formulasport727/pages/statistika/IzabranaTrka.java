/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages.statistika;

// <editor-fold defaultstate="collapsed" desc="import">
import com.metropolitan.formulasport727.dao.TrkackiRezultatDAO;
import com.metropolitan.formulasport727.entities.TrkackiRezultat;
import com.metropolitan.formulasport727.entities.Trka;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
//</editor-fold>

/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje stranice 
 * za prikaz podataka trke koju je klijent izabrao
 * @author Miroslav Stipanović 727
 */
public class IzabranaTrka {
    
    // <editor-fold defaultstate="collapsed" desc="Atributi">
    @Persist
    private VelikaNagrada izabranaVelikaNagrada;
    @Persist
    private Trka izabranaTrka;
    
    @Inject
    private TrkackiRezultatDAO trDAO;
    @Property
    private List<TrkackiRezultat> tabelaRezultata;
    @Property
    private TrkackiRezultat redRezultata;
    
    @InjectPage
    private IzabranaVelikaNagrada izVN;    
    
    private final String format = "dd/MM/yyyy HH:mm";

    public Format getFormat()
    {
        return new SimpleDateFormat(format);
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="On metode">
    void onActivate(){
        tabelaRezultata = trDAO.getListaSvihTrkackihRezultataTrke(izabranaTrka);
    }
    
    Object onPovratakNaVN(){
        izVN.setIzabranaVelikaNagrada(izabranaVelikaNagrada);
        return izVN;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get/Set">
    public VelikaNagrada getIzabranaVelikaNagrada() {
        return izabranaVelikaNagrada;
    }

    public void setIzabranaVelikaNagrada(VelikaNagrada izabranaVelikaNagrada) {
        this.izabranaVelikaNagrada = izabranaVelikaNagrada;
    }

    public Trka getIzabranaTrka() {
        return izabranaTrka;
    }

    public void setIzabranaTrka(Trka izabranaTrka) {
        this.izabranaTrka = izabranaTrka;
    }
    //</editor-fold>
}
