/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages.statistika;

// <editor-fold defaultstate="collapsed" desc="import">
import com.metropolitan.formulasport727.dao.KvalifikacijeDAO;
import com.metropolitan.formulasport727.dao.TreningDAO;
import com.metropolitan.formulasport727.dao.TrkaDAO;
import com.metropolitan.formulasport727.entities.Kvalifikacije;
import com.metropolitan.formulasport727.entities.Trening;
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
 * za prikaz podataka velike nagrade koju je klijent izabrao
 * @author Miroslav Stipanović 727
 */
public class IzabranaVelikaNagrada {
    
    // <editor-fold defaultstate="collapsed" desc="Atributi">
    @Persist
    private VelikaNagrada izabranaVelikaNagrada;
    
    @Property
    @Persist
    private List<Trening> treninziVelikeNagrade;
    @Property
    private Trening trening;
    @Inject
    private TreningDAO treDAO;
    
    @Property
    @Persist
    private List<Kvalifikacije> kvalifikacijeVelikeNagrade;
    @Property
    private Kvalifikacije kvalifikacije;
    @Inject
    private KvalifikacijeDAO kvaDAO;
    
    @Property
    @Persist
    private List<Trka> trkeVelikeNagrade;
    @Property
    private Trka trka;
    @Inject
    private TrkaDAO trkDAO;
    
    private final String format = "dd/MM/yyyy";

    public Format getFormat()
    {
        return new SimpleDateFormat(format);
    }
    
    private final String format2 = "dd/MM/yyyy HH:mm";

    public Format getFormat2()
    {
        return new SimpleDateFormat(format2);
    }
    
    @InjectPage
    private IzabraniTrening izTrening;   
    @InjectPage
    private IzabraneKvalifikacije izKvalifikacije;  
    @InjectPage
    private IzabranaTrka izTrka;  
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="On Metode">
    void onActivate(){
        treninziVelikeNagrade = treDAO.getListaSvihTreningaVelikeNagrade(izabranaVelikaNagrada);
        kvalifikacijeVelikeNagrade = kvaDAO.getListaSvihKvalifikacijaVelikeNagrade(izabranaVelikaNagrada);
        trkeVelikeNagrade = trkDAO.getListaSvihTrkaVelikeNagrade(izabranaVelikaNagrada);
    }
    
    public Object onIzaberiTrening(Trening trening){
        izTrening.setIzabranaVelikaNagrada(izabranaVelikaNagrada);
        izTrening.setIzabraniTrening(trening);
        return izTrening;
//        return this;
    }
    
    public Object onIzaberiKvalifikacije(Kvalifikacije kvalifikacije){
        izKvalifikacije.setIzabranaVelikaNagrada(izabranaVelikaNagrada);
        izKvalifikacije.setIzabraneKvalifikacije(kvalifikacije);
        return izKvalifikacije;
//        return this;
    }
    
    public Object onIzaberiTrku(Trka trka){
        izTrka.setIzabranaVelikaNagrada(izabranaVelikaNagrada);
        izTrka.setIzabranaTrka(trka);
        return izTrka;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get/Set">
    public VelikaNagrada getIzabranaVelikaNagrada() {
        return izabranaVelikaNagrada;
    }

    public void setIzabranaVelikaNagrada(VelikaNagrada izabranaVelikaNagrada) {
        this.izabranaVelikaNagrada = izabranaVelikaNagrada;
    }
    
    public boolean getImaDrzavu(){
        return izabranaVelikaNagrada.getDrzId()!=null;
    }
    
    public boolean getImaTreninge(){
        return !treDAO.getListaSvihTreningaVelikeNagrade(izabranaVelikaNagrada).isEmpty();
    }
    
    public boolean getImaKvalifikacije(){
        return !kvaDAO.getListaSvihKvalifikacijaVelikeNagrade(izabranaVelikaNagrada).isEmpty();
    }
    
    public boolean getImaTrke(){
        return !trkDAO.getListaSvihTrkaVelikeNagrade(izabranaVelikaNagrada).isEmpty();
    }
    //</editor-fold>
}
