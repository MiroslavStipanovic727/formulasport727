/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages;

// <editor-fold defaultstate="collapsed" desc="Import">
import com.metropolitan.formulasport727.pages.statistika.IzabranaVelikaNagrada;
import com.metropolitan.formulasport727.dao.KategorijaDAO;
import com.metropolitan.formulasport727.dao.KlasifikacijaDAO;
import com.metropolitan.formulasport727.dao.SezonaDAO;
import com.metropolitan.formulasport727.dao.TimDAO;
import com.metropolitan.formulasport727.dao.VelikaNagradaDAO;
import com.metropolitan.formulasport727.dao.VozacDAO;
import com.metropolitan.formulasport727.data.TipoviPodataka;
import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.Klasifikacija;
import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Tim;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import com.metropolitan.formulasport727.entities.Vozac;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
//</editor-fold>

/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje sekcije 
 * sajta o statistikama šampionata
 * @author Miroslav Stipanović 727
 */
public class StatistikaSampionata {
    // <editor-fold defaultstate="collapsed" desc="Atributi">
    // <editor-fold defaultstate="collapsed" desc="Ajax atributi">
    @Inject
    private Request zahtev;
    @Inject
    private AjaxResponseRenderer psvRR;
    @InjectComponent
    private Zone zonaFormeIzborPodataka;
    @InjectComponent
    private Zone zonaPrikazaPodataka;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Izbor Kategorije atributi">
    @Property
    @Persist
    private List<Kategorija> kategorije;
    @Property
    private Kategorija izborKategorija;
    @Property
    private Kategorija vrednostKategorije;
    @Inject
    private KategorijaDAO kaDAO;
    @Persist
    private boolean izborKat;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Izbor sezone atributi">
    @Property
    @Persist
    private List<Sezona> sezone;
    @Property
    private Sezona izborSezone;
    @Property
    private Sezona vrednostSezone;
    @Persist
    private Sezona izabranaVrednostSezone;
    @Inject
    private SezonaDAO seDAO;
    @Persist
    private boolean izborSez;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Izbor tipa atributi">
    @Property
    private TipoviPodataka tipovi;
    @Persist
    private boolean izborTip;
//    @Property
//    @Persist
//    private String vrednostTipa;
    //</editor-fold>
    
    @Property
    @Persist
    private String izabrano;

    // <editor-fold defaultstate="collapsed" desc="Prikaz vozača atributi">
    @Property
    @Persist
    private List<Vozac> vozaci;
    @Property
    private Vozac vozac;
    @Inject
    private VozacDAO voDAO;
    @Persist
    private boolean prikazVozaca;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Prikaz tima atributi">
    @Property
    @Persist
    private List<Tim> timovi;
    @Property
    private Tim tim;
    @Inject
    private TimDAO tiDAO;
    @Persist
    private boolean prikazTimova;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Prikaz velike nagrade atributi">
    @Property
    @Persist
    private List<VelikaNagrada> velikeNagrade;
    @Property
    private VelikaNagrada velikaNagrada;
    @Inject
    private VelikaNagradaDAO vnDAO;
    @Persist
    private boolean prikazVelikihNagrada;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Prikaz tabela atributi">
    @Property
    @Persist
    private List<Klasifikacija> tabelaVozaca;
    @Property
    private Klasifikacija redVozac;
    @Property
    @Persist
    private List<Klasifikacija> tabelaTimova;
    @Property
    private Klasifikacija redTim;
    @Inject
    private KlasifikacijaDAO klDAO;
    @Persist
    private boolean prikazTabela;
    //</editor-fold>
    
    @InjectPage
    private IzabranaVelikaNagrada izVN;
    
    private final String format = "dd/MM/yyyy";

    public Format getFormat()
    {
        return new SimpleDateFormat(format);
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="On Metode">
    void onActivate(){
        if(kategorije==null){
            kategorije = kaDAO.getListaSvihKategorija();
        }
        if(sezone == null){
            sezone = new ArrayList<>();
        }
        if(vozaci==null){
            vozaci = new ArrayList<>();
        }
        if(timovi==null){
            timovi = new ArrayList<>();
        }
        if(velikeNagrade==null){
            velikeNagrade = new ArrayList<>();
        }
        if(tabelaVozaca == null){
            tabelaVozaca = new ArrayList<>();
        }
        if(tabelaTimova == null){
            tabelaTimova = new ArrayList<>();
        }
    }
    
    Object onSuccessFromIzborPodataka(){
        if(izborKat==false&&izborSez==false){
           izborKat=true; 
           sezone = seDAO.getListaSvihSezonaKategorije(vrednostKategorije);
           izborSez=true;
           izabrano = vrednostKategorije.toString() + " - ";
        } else if(izborKat==true&&izborSez==true){
            izborSez=false;
            izborTip=true;
            izabrano += vrednostSezone.toString() + " - ";
            izabranaVrednostSezone = vrednostSezone;
        } else if(izborSez==false&&izborTip==true){
            izborTip=false;
            izborKat=false;
            izabrano += tipovi.toString() + " - ";
            čišćenjePodataka();
            punjenjePodataka();
        }
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaPodataka).addRender(zonaFormeIzborPodataka);
        }
        return zahtev.isXHR() ? zonaPrikazaPodataka.getBody() : null;
    }
    
    Object onActionFromReset(){
        izborKat = false;
        izborSez = false;
        izborTip = false;
        prikazVozaca = false;
        prikazTimova = false;
        prikazVelikihNagrada = false;
        prikazTabela = false;
        izabrano = "";
        izabranaVrednostSezone = null;
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaPodataka).addRender(zonaFormeIzborPodataka);
        }
        return zahtev.isXHR() ? zonaPrikazaPodataka.getBody() : null;
    }
    
    public Object onIzaberiVelikuNagradu(VelikaNagrada vn){
        izVN.setIzabranaVelikaNagrada(vn);
        return izVN;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get Metode">
    public boolean getKategorijaIzabrana(){
        return izborKat;
    }
    
    public boolean getPrikazSezone(){
        return izborSez;
    }
    
    public boolean getPrikazTipa(){
        return izborTip;
    }
    
    public boolean getPrikazVozaca(){
        return prikazVozaca;
    }
    
    public boolean getPrikazTimova(){
        return prikazTimova;
    }
    
    public boolean getVozacTima(){
        return tim.equals(vozac.getTimId());
    }
    
    public boolean getPrikazVelikihNagrada(){
        return prikazVelikihNagrada;
    }
    
    public boolean getPrikazTabela(){
        return prikazTabela;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Value Encoderi">
    public ValueEncoder getEncoder(){
        return new ValueEncoder<Kategorija>(){

            @Override
            public String toClient(Kategorija v) {
                return String.valueOf(v.getId());
            }

            @Override
            public Kategorija toValue(String string) {
                Kategorija kategorija=kaDAO.getKategorijaById(Long.parseLong(string));
                return kategorija;
            }
            
        };
    }
    
    public ValueEncoder getEncoderSezone(){
        return new ValueEncoder<Sezona>(){

            @Override
            public String toClient(Sezona v) {
                return String.valueOf(v.getId());
            }

            @Override
            public Sezona toValue(String string) {
                Sezona sezona=seDAO.getSezonaById(Long.parseLong(string));
                return sezona;
            }
            
        };
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Util Metode">
    private void čišćenjePodataka(){
        if(!vozaci.isEmpty()){
            prikazVozaca = false;
            vozaci.clear();
        }        
        if(!timovi.isEmpty()){
            prikazTimova = false;
            timovi.clear();
        }   
        if(!velikeNagrade.isEmpty()){
            prikazVelikihNagrada = false;
            velikeNagrade.clear();
        }
        if(!tabelaVozaca.isEmpty()){
            prikazTabela = false;
            tabelaVozaca.clear();
        }
        if(!tabelaTimova.isEmpty()){
            prikazTabela = false;
            tabelaTimova.clear();
        }
    }

    private void punjenjePodataka() {

        if(tipovi.equals(tipovi.Vozači)){
            prikazVozaca = true;
            vozaci = voDAO.getListaSvihVozacaSezone(izabranaVrednostSezone);
            
        } 
        else if(tipovi.equals(tipovi.Timovi)){
            prikazTimova = true;
            vozaci = voDAO.getListaSvihVozacaSezone(izabranaVrednostSezone);
            timovi = tiDAO.getListaSvihTimovaSezone(izabranaVrednostSezone);
        }
        else if(tipovi.equals(tipovi.VelikeNagrade)){
            prikazVelikihNagrada = true;
            velikeNagrade = vnDAO.getListaSvihVelikihNagradaSezone(izabranaVrednostSezone);
        } 
        else if(tipovi.equals(tipovi.Tabele)){
            prikazTabela = true;
            tabelaVozaca = klDAO.getListaSvihKlasifikacijaVozacaSezone(izabranaVrednostSezone);
            tabelaTimova = klDAO.getListaSvihKlasifikacijaTimovaSezone(izabranaVrednostSezone);
        }
    }
    //</editor-fold>
}
