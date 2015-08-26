/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages;

import com.metropolitan.formulasport727.dao.*;
import com.metropolitan.formulasport727.data.TipoviBaza;
import com.metropolitan.formulasport727.data.TipoviPodatakaAdminpanel;
import com.metropolitan.formulasport727.entities.*;
import com.metropolitan.formulasport727.services.ProtectedPage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;


/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje 
 * admin panela sajta
 * @author Miroslav Stipanović 727
 */
@ProtectedPage
@RolesAllowed(value={"Administrator"})
public class AdminPanel {
    
    @Inject
    private Request zahtev;
    @Inject
    private AjaxResponseRenderer psvRR;
    @Property
    @Persist
    private TipoviBaza tipovi;
    @Persist
    private boolean prikazArtikala;
    @Persist
    private boolean prikazKlijenata;
    @Persist
    private boolean prikazDiskusionihGrupa;
    @Persist
    private boolean prikazSampionata;
    @Persist
    private boolean prikazOstalog;
    @InjectComponent
    private Zone zonaFormeIzborPodataka;
    @InjectComponent
    private Zone zonaPrikazaIzabranihPodataka;
    // <editor-fold defaultstate="collapsed" desc="Atributi Klijent Baza">
    @Property
    @Persist
    private Korisnik korisnik;
    @Property
    private Korisnik redkorisnik;
    @Inject
    private KorisnikDAO koriDAO;
    @Property
    private List<Korisnik> korisnici;
    @Component
    private BeanEditForm unoskorisnika;
    @Component
    private BeanEditForm izmenakorisnika;
    @Property
    @Persist
    private boolean izmenaSifre;
    @Property
    @Persist
    private Kategorija omiljenaKategorija;
    @Property
    private Kategorija omiljenaKategorijaUnos;
    @Property
    @Persist
    private List<Kategorija> kategorije;
    @Inject
    private KategorijaDAO kaDAO;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaKorisnika;
    @InjectComponent
    private Zone zonaFormeIzmenaKorisnika;
    @InjectComponent
    private Zone zonaFormeUnosKorisnika;
    @InjectComponent
    private Zone zonaGridaKorisnik;
    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributi Artikal Baza">
    @Property
    @Persist
    private Artikal artikal;
    @Property
    private Artikal redartikal;
    @Inject
    private ArtikalDAO arDAO;
    @Property
    @Persist
    private List<Artikal> artikli;
    @Property
    @Persist
    private TipArtikla kategorijaArtikla;
    @Property
    @Persist
    private List<TipArtikla> tipoviArtikla;
    @Inject
    private TipArtiklaDAO taDAO;
    @Property
    private String novtip;
    @Property
    @Persist
    private TipArtikla kategorijaArtiklaTabela;
    @Property
    @Persist
    private List<TipArtikla> kategorijeArtiklaTabela;
    @InjectComponent
    private Zone zonaFormeUnosArtikla;
    @InjectComponent
    private Zone zonaFormeTipArtikla;
    @InjectComponent
    private Zone zonaPrikazaArtikala;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributi Šampionati baza unos">
    @Property
    @Persist
    private List<Kategorija> kategorijeUnos;
    @Property
    private Kategorija izborKategorijaUnos;
    @Property
    @Persist
    private Kategorija vrednostKategorijeUnos;
    @Persist
    private Kategorija staraVrednostKategorijeUnos;
    @Property
    @Persist
    private List<Sezona> sezoneUnos;
    @Property
    private Sezona izborSezoneUnos;
    @Property
    @Persist
    private Sezona vrednostSezoneUnos;
    @Inject
    private SezonaDAO seDAO;
    @Property
    @Persist
    private TipoviPodatakaAdminpanel tipoviPodatakaUnos;
    @Persist
    private boolean prikazUnosForme;
    @Property
    private String novaKategorijaUnos;
    @Property
    private String novaSezonaUnos;
    @InjectComponent
    private Zone zonaFormeIzborPodatakaUnos;
    @InjectComponent
    private Zone zonaPrikazaUnosFormeSampionata;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributi Šampionati baza prikaz">
    @Property
    @Persist
    private List<Kategorija> kategorijePrikaz;
    @Property
    private Kategorija izborKategorijaPrikaz;
    @Property
    @Persist
    private Kategorija vrednostKategorijePrikaz;
    @Persist
    private Kategorija staraVrednostKategorijePrikaz;
    @Property
    @Persist
    private List<Sezona> sezonePrikaz;
    @Property
    private Sezona izborSezonePrikaz;
    @Property
    @Persist
    private Sezona vrednostSezonePrikaz;
    @Property
    @Persist
    private TipoviPodatakaAdminpanel tipoviPodatakaPrikaz;
    @Persist
    private boolean prikazTabelePodataka;
    @InjectComponent
    private Zone zonaFormeIzborPodatakaPrikaz;
    @InjectComponent
    private Zone zonaPrikazaTabelePodataka;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributi Vozač">
    @Property
    @Persist
    private Vozac vozac;
    @Property
    private Vozac redvozac;
    @Inject
    private VozacDAO voDAO;
    @Property
    private List<Vozac> vozaciPrikaz;
    @Inject
    private KlasifikacijaDAO klDAO;
    @Property
    @Persist
    private String pozicijaUnos;
    @Property
    @Persist
    private String bodoviUnos;
    @Inject
    private TimDAO tiDAO;
    @Property
    @Persist
    private Tim timVozacaUnos;
    @Property
    private List<Tim> timoviSezoneUnosVozaca;
    @Inject
    private DrzavaDAO drDAO;
    @Property
    @Persist
    private Drzava drzavaVozaca;
    @Property
    private List<Drzava> drzave;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaVozaca;
    @InjectComponent
    private Zone zonaVozaciPrikaz;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributi Tim">
    @Property
    @Persist
    private Tim tim;
    @Property
    private Tim redtim;
    @Property
    private List<Tim> timoviPrikaz;
    @Property
    @Persist
    private String pozicijaTimaUnos;
    @Property
    @Persist
    private String bodoviTimaUnos;
    @Property
    @Persist
    private Drzava drzavaTima;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaTima;
    @InjectComponent
    private Zone zonaTimoviPrikaz;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributi Velika Nagrada">
    @Property
    @Persist
    private VelikaNagrada velikaNagrada;
    @Property
    private VelikaNagrada redvn;
    @Inject
    private VelikaNagradaDAO vnDAO;
    @Property
    private List<VelikaNagrada> velikeNagradePrikaz;
    @Property
    @Persist
    private Vozac vozacVelikeNagradeUnos;
    @Property
    private List<Vozac> vozaciSezone;
    @Property
    @Persist
    private Drzava drzavaVelikeNagrade;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaVelikeNagrade;
    @InjectComponent
    private Zone zonaVelikeNagradePrikaz;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributi Trening">
    @Property
    @Persist
    private Trening trening;
    @Property
    private Trening redtrening;
    @Inject
    private TreningDAO treDAO;
    @Property
    private List<Trening> treninziPrikaz;
    @Property
    @Persist
    private VelikaNagrada velikaNagradaTreninga;
    @Property
    private List<VelikaNagrada> velikeNagradeSezone;
    @Property
    @Persist
    private int satTre;
    @Property
    @Persist
    private int minutTre;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaTreninga;
    @InjectComponent
    private Zone zonaTreninziPrikaz;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributi Kvalifikacije">
    @Property
    @Persist
    private Kvalifikacije kvalifikacije;
    @Property
    private Kvalifikacije redkvalifikacije;
    @Inject
    private KvalifikacijeDAO kvDAO;
    @Property
    private List<Kvalifikacije> kvalifikacijePrikaz;
    @Property
    @Persist
    private VelikaNagrada velikaNagradaKvalifikacija;
    @Property
    @Persist
    private int satKv;
    @Property
    @Persist
    private int minutKv;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaKvalifikacija;
    @InjectComponent
    private Zone zonaKvalifikacijePrikaz;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributi Trka">
    @Property
    @Persist
    private Trka trka;
    @Property
    private Trka redtrka;
    @Inject
    private TrkaDAO trkDAO;
    @Property
    private List<Trka> trkePrikaz;
    @Property
    @Persist
    private VelikaNagrada velikaNagradaTrke;
    @Property
    @Persist
    private int satTrk;
    @Property
    @Persist
    private int minutTrk;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaTrke;
    @InjectComponent
    private Zone zonaTrkePrikaz;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributi Pozicioni Rezultat">
    @Property
    @Persist
    private VelikaNagrada izborVelikeNagradePR;
    @Property
    @Persist
    private VelikaNagrada izborVelikeNagradePRprikaz;
    @Persist
    private VelikaNagrada stariIzborVelikeNagradePRprikaz;
    @Property
    private String treningIliKvalifikacijeRezultat;
    @Property
    @Persist
    private boolean unosPR;
    @Property
    @Persist
    private boolean unosTreRez;
    @Property
    @Persist
    private boolean prikazPR;
    @Property
    @Persist
    private PozicioniRezultat pozicioniRezultat;
    @Property
    private PozicioniRezultat redpr;
    @Inject
    private PozicioniRezultatDAO prDAO;
    @Property
    private List<PozicioniRezultat> pozicioniRezultatPrikaz;
    @Property
    @Persist
    private Vozac vozacTreningRezultata;
    @Property
    @Persist
    private Vozac vozacKvalifikacionogRezultata;
    @Property
    @Persist
    private Trening treningVelikeNagrade;
    @Property
    private List<Trening> treninziVelikeNagradeUnos;
    @Property
    @Persist
    private Kvalifikacije kvalifikacijeVelikeNagrade;
    @Property
    private List<Kvalifikacije> kvalifikacijeVelikeNagradeUnos;
    @Property
    @Persist
    private Trening treningVelikeNagradePRprikaz;
    @Persist 
    private Trening stariTrening;
    @Property
    private List<Trening> treninziVelikeNagradePrikaz;
    @Property
    @Persist
    private Kvalifikacije kvalifikacijaVelikeNagradePRprikaz;
    @Persist
    private Kvalifikacije stareKvalifikacije;
    @Property
    private List<Kvalifikacije> kvalifikacijeVelikeNagradePrikaz;
    @InjectComponent
    private Zone zonaFormeIzborPozicioniRezultatUnos;
    @InjectComponent
    private Zone zonaUnosaPozicionogRezultata;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaPozicionogRezultata;
    @InjectComponent
    private Zone zonaFormeIzborPozicioniRezultatPrikaz;
    @InjectComponent
    private Zone zonaPozicioniRezultatPrikaz;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributi Trkački Rezultat">
    @Property
    @Persist
    private VelikaNagrada izborVelikeNagradeTR;
    @Property
    @Persist
    private VelikaNagrada izborVelikeNagradeTRprikaz;
    @Persist
    private VelikaNagrada stariIzborVelikeNagradeTRprikaz;
    @Property
    @Persist
    private boolean unosTR;
    @Property
    @Persist
    private boolean prikazTR;
    @Property
    @Persist
    private TrkackiRezultat trkackiRezultat;
    @Property
    private TrkackiRezultat redtr;
    @Inject
    private TrkackiRezultatDAO trDAO;
    @Property
    private List<TrkackiRezultat> trkackiRezultatPrikaz;
    @Property
    @Persist
    private Vozac vozacTrkackogRezultata;
    @Property
    @Persist
    private Trka trkaVelikeNagrade;
    @Property
    private List<Trka> trkeVelikeNagradeUnos;
    @Property
    @Persist
    private Trka trkaVelikeNagradeTRprikaz;
    @Persist 
    private Trka staraTrka;
    @Property
    private List<Trka> trkeVelikeNagradePrikaz;
    @InjectComponent
    private Zone zonaFormeIzborTrkackiRezultatUnos;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaTrkackogRezultata;
    @InjectComponent
    private Zone zonaFormeIzborTrkackiRezultatPrikaz;
    @InjectComponent
    private Zone zonaTrkackiRezultatPrikaz;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributi klasifikacija vozač">
    @Property
    @Persist
    private Klasifikacija klasifikacijaVozac;
    @Property
    private Klasifikacija redklv;
    @Property
    private List<Klasifikacija> klasifikacijaVozacaPrikaz;
    @Component
    private BeanEditForm izmenaKlasifikacijeVozaca;
    @InjectComponent
    private Zone zonaFormeIzmenaKlasifikacijeVozaca;
    @InjectComponent
    private Zone zonaKlasifikacijaVozacaPrikaz;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributi klasifikacija tim">
    @Property
    @Persist
    private Klasifikacija klasifikacijaTim;
    @Property
    private Klasifikacija redklt;
    @Property
    private List<Klasifikacija> klasifikacijaTimaPrikaz;
    @Component
    private BeanEditForm izmenaKlasifikacijeTima;
    @InjectComponent
    private Zone zonaFormeIzmenaKlasifikacijeTima;
    @InjectComponent
    private Zone zonaKlasifikacijaTimaPrikaz;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributi Diskusione Grupe Baza">
    @Property
    @Persist
    private DiskusionaGrupa dgrupa;
    @Property
    private DiskusionaGrupa reddg;
    @Inject
    private DiskusionaGrupaDAO dgDAO;
    @Property
    private List<DiskusionaGrupa> dgrupe;
    @Property
    @Persist
    private Kategorija kategorijaDg;
    @Persist 
    private boolean prikazTabelaDg;
    @Property
    @Persist
    private Kategorija izborKategorijeDg;
    @Persist
    private boolean prikazTabelaDiskusije;
    @Persist
    private DiskusionaGrupa izabranaDg;
    @Property
    private List<Diskusija> diskusije;
    @Property
    private Diskusija reddiskusija;
    @Inject
    private DiskusijaDAO diDAO;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaDiskusioneGrupe;
    @InjectComponent
    private Zone zonaFormeIzborKategorijeDiskusioneGrupe;
    @InjectComponent
    private Zone zonaDgrupePrikaz;
    @InjectComponent
    private Zone zonaPrikazTabelaDiskusije;
    @InjectComponent
    private Zone zonaPrikazTabelaDiskusije2;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributi Države">
    @Property
    @Persist
    private Drzava drzava;
    @Property
    private Drzava reddrzava;
    @InjectComponent
    private Zone zonaFormeUnosIzmenaDrzave;
    @InjectComponent
    private Zone zonaGridaDrzava;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="ValueEncoderi">
    public ValueEncoder getEncoderKategorije(){
        return new ValueEncoder<Kategorija>(){

            @Override
            public String toClient(Kategorija k) {
                return String.valueOf(k.getId());
            }

            @Override
            public Kategorija toValue(String string) {
                Kategorija kategorija=kaDAO.getKategorijaById(Long.parseLong(string));
                return kategorija;
            }
            
        };
    }
    
    public ValueEncoder getEncoderTipaArtikla(){
        return new ValueEncoder<TipArtikla>(){

            @Override
            public String toClient(TipArtikla ta) {
                return String.valueOf(ta.getId());
            }

            @Override
            public TipArtikla toValue(String string) {
                TipArtikla tipArtikla=taDAO.getTipArtiklaById(Long.parseLong(string));
                return tipArtikla;
            }
            
        }; 
    }
    
    public ValueEncoder getEncoderSezone(){
        return new ValueEncoder<Sezona>(){

            @Override
            public String toClient(Sezona s) {
                return String.valueOf(s.getId());
            }

            @Override
            public Sezona toValue(String string) {
                Sezona sezona=seDAO.getSezonaById(Long.parseLong(string));
                return sezona;
            }
            
        }; 
    }
    
//    public ValueEncoder getEncoderKlasifikacije(){
//        return new ValueEncoder<Klasifikacija>(){
//
//            @Override
//            public String toClient(Klasifikacija k) {
//                return String.valueOf(k.getId());
//            }
//
//            @Override
//            public Klasifikacija toValue(String string) {
//                Klasifikacija klasifikacija=klDAO.getKlasifikacijaById(Long.parseLong(string));
//                return klasifikacija;
//            }
//            
//        };
//    }
    
    public ValueEncoder getEncoderTima(){
        return new ValueEncoder<Tim>(){

            @Override
            public String toClient(Tim t) {
                return String.valueOf(t.getId());
            }

            @Override
            public Tim toValue(String string) {
                Tim tim=tiDAO.getTimById(Long.parseLong(string));
                return tim;
            }
            
        };
    }
    
    public ValueEncoder getEncoderDrzave(){
        return new ValueEncoder<Drzava>(){

            @Override
            public String toClient(Drzava d) {
                return String.valueOf(d.getId());
            }

            @Override
            public Drzava toValue(String string) {
                Drzava drzava=drDAO.getDrzavaById(Long.parseLong(string));
                return drzava;
            }
            
        };
    }
    
    public ValueEncoder getEncoderVozaca(){
        return new ValueEncoder<Vozac>(){

            @Override
            public String toClient(Vozac v) {
                return String.valueOf(v.getId());
            }

            @Override
            public Vozac toValue(String string) {
                Vozac vozac=voDAO.getVozacById(Long.parseLong(string));
                return vozac;
            }
            
        };
    }
    
    public ValueEncoder getEncoderVelikeNagrade(){
        return new ValueEncoder<VelikaNagrada>(){

            @Override
            public String toClient(VelikaNagrada vn) {
                return String.valueOf(vn.getId());
            }

            @Override
            public VelikaNagrada toValue(String string) {
                VelikaNagrada velikaNagrada=vnDAO.getVelikaNagradaById(Long.parseLong(string));
                return velikaNagrada;
            }
            
        };
    }
    
    public ValueEncoder getEncoderTreninga(){
        return new ValueEncoder<Trening>(){

            @Override
            public String toClient(Trening t) {
                return String.valueOf(t.getId());
            }

            @Override
            public Trening toValue(String string) {
                Trening trening=treDAO.getTreningById(Long.parseLong(string));
                return trening;
            }
            
        };
    }
    
    public ValueEncoder getEncoderKvalifikacija(){
        return new ValueEncoder<Kvalifikacije>(){

            @Override
            public String toClient(Kvalifikacije kv) {
                return String.valueOf(kv.getId());
            }

            @Override
            public Kvalifikacije toValue(String string) {
                Kvalifikacije kvalifikacije=kvDAO.getKvalifikacijeById(Long.parseLong(string));
                return kvalifikacije;
            }
            
        };
    }
    
    public ValueEncoder getEncoderTrka(){
        return new ValueEncoder<Trka>(){

            @Override
            public String toClient(Trka t) {
                return String.valueOf(t.getId());
            }

            @Override
            public Trka toValue(String string) {
                Trka trka=trkDAO.getTrkaById(Long.parseLong(string));
                return trka;
            }
            
        };
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="On Metode">
    void onActivate(){
        korisnici = koriDAO.getListaSvihKorisnika();
        kategorije = kaDAO.getListaSvihKategorija();
        tipoviArtikla = taDAO.getListaSvihTipovaArtikla();
        drzave = drDAO.getListaSvihDrzava();
        
        kategorijeArtiklaTabela = taDAO.getListaSvihTipovaArtikla();
        if(kategorijaArtiklaTabela!=null){
            artikli = arDAO.getListaSvihArtikalaTipaArtikla(kategorijaArtiklaTabela);
        } else {
            artikli = arDAO.getListaSvihArtikala();
        }
        if(artikli.isEmpty()){
            artikli.add(new Artikal());
        }
        kategorijeUnos = kaDAO.getListaSvihKategorija();
        if(vrednostKategorijeUnos==null){
            sezoneUnos = new ArrayList<>();
        } else {
            sezoneUnos = seDAO.getListaSvihSezonaKategorije(vrednostKategorijeUnos);
        }
        if(vrednostSezoneUnos==null){
            timoviSezoneUnosVozaca = new ArrayList<>();
            vozaciSezone = new ArrayList<>();
            velikeNagradeSezone = new ArrayList<>();
        } else {
            timoviSezoneUnosVozaca = tiDAO.getListaSvihTimovaSezone(vrednostSezoneUnos);
            vozaciSezone = voDAO.getListaSvihVozacaSezone(vrednostSezoneUnos);
            velikeNagradeSezone = vnDAO.getListaSvihVelikihNagradaSezone(vrednostSezoneUnos);
        }
        kategorijePrikaz = kaDAO.getListaSvihKategorija();
        if(vrednostKategorijePrikaz==null){
            sezonePrikaz = new ArrayList<>();
        } else {
            sezonePrikaz = seDAO.getListaSvihSezonaKategorije(vrednostKategorijePrikaz);
        }
        if(vrednostSezonePrikaz!=null){
            vozaciPrikaz = voDAO.getListaSvihVozacaSezone(vrednostSezonePrikaz);
            timoviPrikaz = tiDAO.getListaSvihTimovaSezone(vrednostSezonePrikaz);
            velikeNagradePrikaz = vnDAO.getListaSvihVelikihNagradaSezone(vrednostSezonePrikaz);
            treninziPrikaz = treDAO.getListaSvihTreningaSezone(vrednostSezonePrikaz);
            kvalifikacijePrikaz = kvDAO.getListaSvihKvalifikacijaSezone(vrednostSezonePrikaz);
            trkePrikaz = trkDAO.getListaSvihTrkaSezone(vrednostSezonePrikaz);
            klasifikacijaVozacaPrikaz = klDAO.getListaSvihKlasifikacijaVozacaSezone(vrednostSezonePrikaz);
            klasifikacijaTimaPrikaz = klDAO.getListaSvihKlasifikacijaTimovaSezone(vrednostSezonePrikaz);
        }
        if(vozaciPrikaz!=null&&vozaciPrikaz.isEmpty()){
            vozaciPrikaz.add(new Vozac());
        }
        if(timoviPrikaz!=null&&timoviPrikaz.isEmpty()){
            timoviPrikaz.add(new Tim());
        }
        if(velikeNagradePrikaz!=null&&velikeNagradePrikaz.isEmpty()){
            velikeNagradePrikaz.add(new VelikaNagrada());
        }
        if(treninziPrikaz!=null&&treninziPrikaz.isEmpty()){
            treninziPrikaz.add(new Trening());
        }
        if(kvalifikacijePrikaz!=null&&kvalifikacijePrikaz.isEmpty()){
            kvalifikacijePrikaz.add(new Kvalifikacije());
        }
        if(trkePrikaz!=null&&trkePrikaz.isEmpty()){
            trkePrikaz.add(new Trka());
        }
        if(klasifikacijaVozacaPrikaz!=null&&klasifikacijaVozacaPrikaz.isEmpty()){
            klasifikacijaVozacaPrikaz.add(new Klasifikacija());
        }
        if(klasifikacijaTimaPrikaz!=null&&klasifikacijaTimaPrikaz.isEmpty()){
            klasifikacijaTimaPrikaz.add(new Klasifikacija());
        }
        if(izborVelikeNagradePR!=null){
            treninziVelikeNagradeUnos = treDAO.getListaSvihTreningaVelikeNagrade(izborVelikeNagradePR);
            kvalifikacijeVelikeNagradeUnos = kvDAO.getListaSvihKvalifikacijaVelikeNagrade(izborVelikeNagradePR);
        } else {
            treninziVelikeNagradeUnos = new ArrayList<>();
            kvalifikacijeVelikeNagradeUnos = new ArrayList<>();
        }
        if(izborVelikeNagradeTR!=null){
            trkeVelikeNagradeUnos = trkDAO.getListaSvihTrkaVelikeNagrade(izborVelikeNagradeTR);
        } else {
            trkeVelikeNagradeUnos = new ArrayList<>();
        }
        if(izborVelikeNagradePRprikaz!=null){
            treninziVelikeNagradePrikaz = treDAO.getListaSvihTreningaVelikeNagrade(izborVelikeNagradePRprikaz);
            kvalifikacijeVelikeNagradePrikaz = kvDAO.getListaSvihKvalifikacijaVelikeNagrade(izborVelikeNagradePRprikaz);
        } else {
            treninziVelikeNagradePrikaz = new ArrayList<>();
            kvalifikacijeVelikeNagradePrikaz = new ArrayList<>();
        }
        if(izborVelikeNagradeTRprikaz!=null){
            trkeVelikeNagradePrikaz = trkDAO.getListaSvihTrkaVelikeNagrade(izborVelikeNagradeTRprikaz);
        } else {
            trkeVelikeNagradePrikaz = new ArrayList<>();
        }
        if(treningVelikeNagradePRprikaz!=null){
            pozicioniRezultatPrikaz = prDAO.getListaSvihPozicionihRezultataTreninga(treningVelikeNagradePRprikaz);
        } else if (kvalifikacijaVelikeNagradePRprikaz!=null){
            pozicioniRezultatPrikaz = prDAO.getListaSvihPozicionihRezultataKvalifikacija(kvalifikacijaVelikeNagradePRprikaz);
        } else {
            pozicioniRezultatPrikaz = new ArrayList<>();
        }
        if(trkaVelikeNagradeTRprikaz!=null){
            trkackiRezultatPrikaz = trDAO.getListaSvihTrkackihRezultataTrke(trkaVelikeNagradeTRprikaz);
        }
        if(pozicioniRezultatPrikaz!=null&&pozicioniRezultatPrikaz.isEmpty()){
            pozicioniRezultatPrikaz.add(new PozicioniRezultat());
        }
        if(trkackiRezultatPrikaz!=null&&trkackiRezultatPrikaz.isEmpty()){
            trkackiRezultatPrikaz.add(new TrkackiRezultat());
        }
        if(izborKategorijeDg!=null){
            dgrupe = dgDAO.getListaSvihDiskusionihGrupaKategorije(izborKategorijeDg);
        } else {
            dgrupe = new ArrayList<>();
        }
        if(dgrupe!=null&&dgrupe.isEmpty()){
            dgrupe.add(new DiskusionaGrupa());
        }
        if(izabranaDg!=null){
            diskusije = diDAO.getListaSvihDiskusijaDiskusioneGrupe(izabranaDg);
        } else {
            diskusije = new ArrayList<>();
        }
        if(diskusije!=null&&diskusije.isEmpty()){
            diskusije.add(new Diskusija());
        }
    }
    
    Object onSuccessFromIzborPodataka(){
        čišćenjePrikaza();
        if(tipovi==null){
            prikazArtikala = false;
            prikazDiskusionihGrupa = false;
            prikazKlijenata = false;
            prikazSampionata = false;
            prikazOstalog = false;
        }
        else if(tipovi.equals(TipoviBaza.Artikli)){
            prikazArtikala = true;
        } else if(tipovi.equals(TipoviBaza.DiskusioneGrupe)){
            prikazDiskusionihGrupa = true;
        } else if(tipovi.equals(TipoviBaza.Klijenti)){
            prikazKlijenata = true;
        } else if(tipovi.equals(TipoviBaza.Sampionati)){
            prikazSampionata = true;
        } else if(tipovi.equals(TipoviBaza.Ostalo)){
            prikazOstalog = true;
        }
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaIzabranihPodataka).addRender(zonaFormeIzborPodataka);
        }
        return zahtev.isXHR() ? zonaPrikazaIzabranihPodataka.getBody() : null;
    }
    
    private void čišćenjePrikaza(){
        prikazArtikala = false;
        prikazDiskusionihGrupa = false;
        prikazKlijenata = false;
        prikazSampionata = false;
        prikazOstalog = false;
    }
    
    Object onSuccessFromIzborPodatakaUnos(){
        if(novaKategorijaUnos!=null){
            unosNoveKategorije();
        }
        if(novaSezonaUnos!=null&&vrednostKategorijeUnos!=null){
            unosNoveSezone();
        }
        if((vrednostKategorijeUnos!=null&&staraVrednostKategorijeUnos!=null)&&
                !staraVrednostKategorijeUnos.equals(vrednostKategorijeUnos)){
            staraVrednostKategorijeUnos = vrednostKategorijeUnos;
            vrednostSezoneUnos = null;
        }
        prikazUnosForme = vrednostSezoneUnos!=null&&vrednostKategorijeUnos!=null&&tipoviPodatakaUnos!=null;
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaUnosFormeSampionata).addRender(zonaFormeIzborPodatakaUnos);
        }
        return zahtev.isXHR() ? zonaPrikazaUnosFormeSampionata.getBody() : null;
    }
    
    Object onSuccessFromIzborPozicioniRezultatUnos(){
        unosPR = true;
        unosTreRez = treningIliKvalifikacijeRezultat.equals("T");
        if(zahtev.isXHR()){
            psvRR.addRender(zonaUnosaPozicionogRezultata).addRender(zonaFormeIzborPozicioniRezultatUnos);
        }
        return zahtev.isXHR() ? zonaUnosaPozicionogRezultata.getBody() : null;
    }
    
    Object onSuccessFromIzborTrkackiRezultatUnos(){
        unosTR = true;
        if(zahtev.isXHR()){
            psvRR.addRender(zonaFormeUnosIzmenaTrkackogRezultata).addRender(zonaFormeIzborTrkackiRezultatUnos);
        }
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTrkackogRezultata.getBody() : null;
    }
    
    Object onSuccessFromIzborPozicioniRezultatPrikaz(){
        if(izborVelikeNagradePRprikaz!=null&&!izborVelikeNagradePRprikaz.equals(stariIzborVelikeNagradePRprikaz)){
            stariIzborVelikeNagradePRprikaz = izborVelikeNagradePRprikaz;
            treningVelikeNagradePRprikaz = null;
            kvalifikacijaVelikeNagradePRprikaz = null;
        }
        prikazPR = (treningVelikeNagradePRprikaz!=null||kvalifikacijaVelikeNagradePRprikaz!=null)
                &&izborVelikeNagradePRprikaz!=null;
        if(treningVelikeNagradePRprikaz!=null&&!treningVelikeNagradePRprikaz.equals(stariTrening)){
            kvalifikacijaVelikeNagradePRprikaz = null;
        }
        if(kvalifikacijaVelikeNagradePRprikaz!=null&&!kvalifikacijaVelikeNagradePRprikaz.equals(stareKvalifikacije)){
            treningVelikeNagradePRprikaz = null;
        }
        stariTrening = treningVelikeNagradePRprikaz;
        stareKvalifikacije = kvalifikacijaVelikeNagradePRprikaz;
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPozicioniRezultatPrikaz).addRender(zonaFormeIzborPozicioniRezultatPrikaz);
        }
        return zahtev.isXHR() ? zonaPozicioniRezultatPrikaz.getBody() : null;
    }
    
    Object onSuccessFromIzborTrkackiRezultatPrikaz(){
        if(izborVelikeNagradeTRprikaz!=null&&!izborVelikeNagradeTRprikaz.equals(stariIzborVelikeNagradeTRprikaz)){
            stariIzborVelikeNagradeTRprikaz = izborVelikeNagradeTRprikaz;
            trkaVelikeNagradeTRprikaz = null;
        }
        prikazTR = izborVelikeNagradeTRprikaz!=null&&trkaVelikeNagradeTRprikaz!=null;
        if(zahtev.isXHR()){
            psvRR.addRender(zonaTrkackiRezultatPrikaz).addRender(zonaFormeIzborTrkackiRezultatPrikaz);
        }
        return zahtev.isXHR() ? zonaTrkackiRezultatPrikaz.getBody() : null;
    }
    
    Object onSuccessFromIzborPodatakaPrikaz(){
        if((vrednostKategorijePrikaz!=null&&staraVrednostKategorijePrikaz!=null)&&
                !staraVrednostKategorijePrikaz.equals(vrednostKategorijePrikaz)){
            staraVrednostKategorijePrikaz = vrednostKategorijePrikaz;
            vrednostSezonePrikaz = null;
        }
        prikazTabelePodataka = vrednostSezonePrikaz!=null&&vrednostKategorijePrikaz!=null
                &&tipoviPodatakaPrikaz!=null;
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaTabelePodataka).addRender(zonaFormeIzborPodatakaPrikaz);
        }
        return zahtev.isXHR() ? zonaPrikazaTabelePodataka.getBody() : null;
    }
    
    Object onSuccessFromIzborKategorijeDiskusioneGrupe(){
        prikazTabelaDg = izborKategorijeDg!=null;
        if(zahtev.isXHR()){
            psvRR.addRender(zonaDgrupePrikaz).addRender(zonaFormeIzborKategorijeDiskusioneGrupe);
        }
        return zahtev.isXHR() ? zonaDgrupePrikaz.getBody() : null;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Korisnik metode">
    @CommitAfter
    Object onSuccessFromUnosKorisnika(){
        if(koriDAO.proveraDaLiPostojiEmail(korisnik.getEmail())) {
            unoskorisnika.recordError("Uneti email već postoji");
            return null;
        } else if(koriDAO.proveraDaLiPostojiKorisnickoIme(korisnik.getKorisnickoIme())) {
            unoskorisnika.recordError("Uneto korisničko ime već postoji");
            return null;
        } else {
            String preHashSifra = korisnik.getSifra();
            korisnik.setSifra(getMD5Hash(preHashSifra));
            korisnik.setKatId(omiljenaKategorijaUnos);
            koriDAO.dodajIliIzmeniKorisnika(korisnik);
            korisnici = koriDAO.getListaSvihKorisnika();
            korisnik = new Korisnik();
//            if(zahtev.isXHR()){
//                psvRR.addRender(zonaGridaKorisnik).addRender(zonaFormeUnosKorisnika);
//            }
//            return zahtev.isXHR() ? zonaGridaKorisnik.getBody() : null;
            return this;
        }
        
    }
    
    @CommitAfter
    Object onSuccessFromIzmenaKorisnika(){
        Korisnik prePromene = koriDAO.getKorisnikById(korisnik.getId());
        if(korisnik.getEmail().equals(prePromene.getEmail())||!koriDAO.proveraDaLiPostojiEmail(korisnik.getEmail())){
            String preHashSifra = korisnik.getSifra();
            if(izmenaSifre){
                korisnik.setSifra(getMD5Hash(preHashSifra));
            }
            else{
                korisnik.setSifra(prePromene.getSifra());
            }
            korisnik.setKatId(omiljenaKategorija);
            koriDAO.dodajIliIzmeniKorisnika(korisnik);
            korisnici = koriDAO.getListaSvihKorisnika();
            korisnik = new Korisnik();
            omiljenaKategorija = new Kategorija();
//            if(zahtev.isXHR()){
//                psvRR.addRender(zonaGridaKorisnik).addRender(zonaFormeIzmenaKorisnika);
//            }
//            return zahtev.isXHR() ? zonaGridaKorisnik.getBody() : null;
            return this;
        } else {
            izmenakorisnika.recordError("Uneti email već postoji");
            return null;
        }
    }
    
    Object onActionFromResetKorisnika(){
        korisnik = new Korisnik();
        omiljenaKategorija = new Kategorija();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaKorisnika.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditKorisnik(Korisnik kZaMenjanje){
        korisnik = kZaMenjanje;
        omiljenaKategorija = korisnik.getKatId();
        return zahtev.isXHR() ? zonaFormeIzmenaKorisnika.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteKorisnik(String email){
        koriDAO.obrisiKorisnika(email);
        korisnici = koriDAO.getListaSvihKorisnika();
        if(korisnici.isEmpty()){
            korisnici.add(new Korisnik());
        }
        return zahtev.isXHR() ? zonaGridaKorisnik.getBody() : null;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Artikal Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaArtikla(){
        if(novtip!=null){
            TipArtikla noviTip = new TipArtikla();
            noviTip.setNazivTipa(novtip);
            taDAO.dodajIliIzmeniTipArtikla(noviTip);
            kategorijaArtikla = taDAO.getTipArtiklaById(taDAO.getListaSvihTipovaArtikla()
                    .get(taDAO.getListaSvihTipovaArtikla().size()-1).getId());
        }
        artikal.setTipId(kategorijaArtikla);
        arDAO.dodajIliIzmeniArtikal(artikal);
        artikal = new Artikal();
        kategorijaArtikla = new TipArtikla();
        return this;
    }
    
    Object onActionFromResetArtikla(){
        artikal = new Artikal();
        kategorijaArtikla = new TipArtikla();
        return zahtev.isXHR() ? zonaFormeUnosArtikla.getBody() : null;
    }
    
    Object onSuccessFromIzborTipaArtikla(){
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaArtikala).addRender(zonaFormeTipArtikla);
        }
        return zahtev.isXHR() ? zonaPrikazaArtikala.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditArtikal(Artikal aZaMenjanje){
        artikal = aZaMenjanje;
        kategorijaArtikla = artikal.getTipId();
        return zahtev.isXHR() ? zonaFormeUnosArtikla.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteArtikal(Long id){
        arDAO.obrisiArtikal(id);
        if(kategorijaArtiklaTabela!=null){
            artikli = arDAO.getListaSvihArtikalaTipaArtikla(kategorijaArtiklaTabela);
        } else {
            artikli = arDAO.getListaSvihArtikala();
        }
        if(artikli.isEmpty()){
            artikli.add(new Artikal());
        }
        return zahtev.isXHR() ? zonaPrikazaArtikala.getBody() : null;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Vozač Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaVozaca(){
        if(vozac.getId()==null)
            dodavanjeNoveKlasifikacijeVozaca();
        dodavanjeIliIzmenaVozaca();
        if(vozac.getId()==null)
            izmenaNoveKlasifikacijeVozaca();
        bodoviUnos = new String();
        pozicijaUnos = new String();
        drzavaVozaca = new Drzava();
        timVozacaUnos = new Tim();
        vozac = new Vozac();
        return this;
    }
    
    @CommitAfter
    private void dodavanjeNoveKlasifikacijeVozaca(){
        Klasifikacija k = new Klasifikacija();
        k.setSezId(vrednostSezoneUnos);
        k.setBodovi(Double.parseDouble(bodoviUnos));
        k.setPozicijaK(Integer.parseInt(pozicijaUnos));
        klDAO.dodajIliIzmeniKlasifikaciju(k);
    }
    
    @CommitAfter
    private void dodavanjeIliIzmenaVozaca(){
        Klasifikacija k2;
        if(vozac.getId()!=null) {
            izmenaKlasifikacijeVozaca();
        } else{
            k2 = klDAO.getLastKlasifikacija();    
            vozac.setKlaId(k2);
        }
        vozac.setSezId(vrednostSezoneUnos);
        vozac.setDrzId(drzavaVozaca);
        vozac.setTimId(timVozacaUnos);
        if(vozac.getSlika()==null){
            vozac.setSlika("../images/vozaci/bezslike.jpg");
        }
        voDAO.dodajIliIzmeniVozaca(vozac);
    }
    
    @CommitAfter
    private void izmenaKlasifikacijeVozaca(){
        Klasifikacija k = klDAO.getKlasifikacijaByVozac(vozac);
        k.setBodovi(Double.parseDouble(bodoviUnos));
        k.setPozicijaK(Integer.parseInt(pozicijaUnos));
        klDAO.dodajIliIzmeniKlasifikaciju(k);
    }
    
    @CommitAfter
    private void izmenaNoveKlasifikacijeVozaca(){
        Klasifikacija k2 = klDAO.getLastKlasifikacija();
        k2.setVozId(voDAO.getLastVozac());
        klDAO.dodajIliIzmeniKlasifikaciju(k2);
    }
    
    Object onActionFromResetVozaca(){
        bodoviUnos = new String();
        pozicijaUnos = new String();
        drzavaVozaca = new Drzava();
        timVozacaUnos = new Tim();
        vozac = new Vozac();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaVozaca.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditVozac(Vozac vZaMenjanje){
        vozac = vZaMenjanje;
        bodoviUnos = ""+vozac.getKlaId().getBodovi();
        pozicijaUnos = ""+vozac.getKlaId().getPozicijaK();
        drzavaVozaca = vozac.getDrzId();
        timVozacaUnos = vozac.getTimId();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaVozaca.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteVozac(Long id){
        Klasifikacija k = klDAO.getKlasifikacijaByVozac(voDAO.getVozacById(id));
        k.setVozId(null);
        klDAO.dodajIliIzmeniKlasifikaciju(k);
        voDAO.obrisiVozaca(id);
        klDAO.obrisiKlasifikaciju(k.getId());
        vozaciPrikaz = voDAO.getListaSvihVozacaSezone(vrednostSezonePrikaz);
        if(vozaciPrikaz.isEmpty()){
            vozaciPrikaz.add(vozac);
        }
        return zahtev.isXHR() ? zonaVozaciPrikaz : null;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Tim Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaTima(){
        if(tim.getId()==null)
            dodavanjeNoveKlasifikacijeTima();
        dodavanjeIliIzmenaTima();
        if(tim.getId()==null)
            izmenaNoveKlasifikacijeTima();
        bodoviTimaUnos = new String();
        pozicijaTimaUnos = new String();
        drzavaTima = new Drzava();
        tim = new Tim();
        return this;
    }
    
    @CommitAfter
    private void dodavanjeNoveKlasifikacijeTima(){
        Klasifikacija k = new Klasifikacija();
        k.setSezId(vrednostSezoneUnos);
        k.setBodovi(Double.parseDouble(bodoviTimaUnos));
        k.setPozicijaK(Integer.parseInt(pozicijaTimaUnos));
        klDAO.dodajIliIzmeniKlasifikaciju(k);
    }
    
    @CommitAfter
    private void dodavanjeIliIzmenaTima(){
        Klasifikacija k2;
        if(tim.getId()!=null) {
            izmenaKlasifikacijeTima();
        } else{
            k2 = klDAO.getLastKlasifikacija();    
            tim.setKlaId(k2);
        }
        tim.setSezId(vrednostSezoneUnos);
        tim.setDrzId(drzavaTima);
        if(tim.getLogoTima()==null){
            tim.setLogoTima("../images/timovi/bezlogoa.jpg");
        }
        tiDAO.dodajIliIzmeniTim(tim);
    }
    
    @CommitAfter
    private void izmenaKlasifikacijeTima(){
        Klasifikacija k = klDAO.getKlasifikacijaByTim(tim);
        k.setBodovi(Double.parseDouble(bodoviTimaUnos));
        k.setPozicijaK(Integer.parseInt(pozicijaTimaUnos));
        klDAO.dodajIliIzmeniKlasifikaciju(k);
    }
    
    @CommitAfter
    private void izmenaNoveKlasifikacijeTima(){
        Klasifikacija k2 = klDAO.getLastKlasifikacija();
        k2.setTimId(tiDAO.getLastTim());
        klDAO.dodajIliIzmeniKlasifikaciju(k2);
    }
    
    Object onActionFromResetTima(){
        bodoviTimaUnos = new String();
        pozicijaTimaUnos = new String();
        drzavaTima = new Drzava();
        tim = new Tim();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTima.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditTim(Tim tZaMenjanje){
        tim = tZaMenjanje;
        bodoviTimaUnos = ""+tim.getKlaId().getBodovi();
        pozicijaTimaUnos = ""+tim.getKlaId().getPozicijaK();
        drzavaTima = tim.getDrzId();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTima.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteTim(Long id){
        Klasifikacija k = klDAO.getKlasifikacijaByTim(tiDAO.getTimById(id));
        k.setTimId(null);
        klDAO.dodajIliIzmeniKlasifikaciju(k);
        tiDAO.obrisiTim(id);
        klDAO.obrisiKlasifikaciju(k.getId());
        timoviPrikaz = tiDAO.getListaSvihTimovaSezone(vrednostSezonePrikaz);
        if(timoviPrikaz.isEmpty()){
            timoviPrikaz.add(new Tim());
        }
        return zahtev.isXHR() ? zonaTimoviPrikaz.getBody() : null;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Velika Nagrada Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaVelikeNagrade(){
        if(vozacVelikeNagradeUnos!=null&&vozacVelikeNagradeUnos.getId()!=null)
            velikaNagrada.setVozId(vozacVelikeNagradeUnos);
        if(drzavaVelikeNagrade!=null&&drzavaVelikeNagrade.getId()!=null)
            velikaNagrada.setDrzId(drzavaVelikeNagrade);
        velikaNagrada.setSezId(vrednostSezoneUnos);
        vnDAO.dodajIliIzmeniVelikuNagradu(velikaNagrada);
        vozacVelikeNagradeUnos = new Vozac();
        drzavaVelikeNagrade = new Drzava();
        velikaNagrada = new VelikaNagrada();
        return this;
    }
    
    Object onActionFromResetVelikeNagrade(){
        vozacVelikeNagradeUnos = new Vozac();
        drzavaVelikeNagrade = new Drzava();
        velikaNagrada = new VelikaNagrada();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaVelikeNagrade.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditVN(VelikaNagrada vnZaMenjanje){
        velikaNagrada = vnZaMenjanje;
//        if(vozacVelikeNagradeUnos!=null){
//        if(velikaNagrada.getVozId()!=null){
            vozacVelikeNagradeUnos = velikaNagrada.getVozId();
//        } else {
//            
//        }
//        if(drzavaVelikeNagrade != null){
            drzavaVelikeNagrade = velikaNagrada.getDrzId();
//        } 
        return zahtev.isXHR() ? zonaFormeUnosIzmenaVelikeNagrade.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteVN(Long id){
        vnDAO.obrisiVelikuNagradu(id);
        velikeNagradePrikaz = vnDAO.getListaSvihVelikihNagradaSezone(vrednostSezonePrikaz);
        if(velikeNagradePrikaz.isEmpty())
            velikeNagradePrikaz.add(new VelikaNagrada());
        return zahtev.isXHR() ? zonaVelikeNagradePrikaz.getBody() : null;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Trening Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaTreninga(){
        trening.setVelId(velikaNagradaTreninga);
        Calendar c = Calendar.getInstance();
        c.setTime(trening.getVremeTreninga());
        c.add(Calendar.HOUR_OF_DAY, satTre);
        c.add(Calendar.MINUTE, minutTre);
        trening.setVremeTreninga(c.getTime());
        treDAO.dodajIliIzmeniTrening(trening);
        velikaNagradaTreninga = null;
        trening = new Trening();
        satTre = 0;
        minutTre = 0;
        return this;
    }
    
    Object onActionFromResetTreninga(){
        velikaNagradaTreninga = null;
        trening = new Trening();
        satTre = 0;
        minutTre = 0;
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTreninga.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditTrening(Trening treZaMenjanje){
        trening = treZaMenjanje;
        Calendar c = Calendar.getInstance();
        c.setTime(trening.getVremeTreninga());
        satTre = c.get(Calendar.HOUR_OF_DAY);
        minutTre = c.get(Calendar.MINUTE);
        velikaNagradaTreninga = trening.getVelId();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTreninga.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteTrening(Long id){
        treDAO.obrisiTrening(id);
        treninziPrikaz = treDAO.getListaSvihTreningaSezone(vrednostSezonePrikaz);
        if(treninziPrikaz.isEmpty())
            treninziPrikaz.add(new Trening());
        return zahtev.isXHR() ? zonaTreninziPrikaz.getBody() : null;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Kvalifikacije Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaKvalifikacija(){
        kvalifikacije.setVelId(velikaNagradaKvalifikacija);
        Calendar c = Calendar.getInstance();
        c.setTime(kvalifikacije.getVremeKvalifikacija());
        c.add(Calendar.HOUR_OF_DAY, satKv);
        c.add(Calendar.MINUTE, minutKv);
        kvalifikacije.setVremeKvalifikacija(c.getTime());
        kvDAO.dodajIliIzmeniKvalifikacije(kvalifikacije);
        velikaNagradaKvalifikacija = null;
        kvalifikacije = new Kvalifikacije();
        satKv = 0;
        minutKv = 0;
        return this;
    }
    
    Object onActionFromResetKvalifikacija(){
        velikaNagradaKvalifikacija = null;
        kvalifikacije = new Kvalifikacije();
        satKv = 0;
        minutKv = 0;
        return zahtev.isXHR() ? zonaFormeUnosIzmenaKvalifikacija.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditKvalifikacija(Kvalifikacije kvZaMenjanje){
        kvalifikacije = kvZaMenjanje;
        Calendar c = Calendar.getInstance();
        c.setTime(kvalifikacije.getVremeKvalifikacija());
        satKv = c.get(Calendar.HOUR_OF_DAY);
        minutKv = c.get(Calendar.MINUTE);
        velikaNagradaKvalifikacija = kvalifikacije.getVelId();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaKvalifikacija.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteKvalifikacija(Long id){
        kvDAO.obrisiKvalifikacije(id);
        kvalifikacijePrikaz = kvDAO.getListaSvihKvalifikacijaSezone(vrednostSezonePrikaz);
        if(kvalifikacijePrikaz.isEmpty())
            kvalifikacijePrikaz.add(new Kvalifikacije());
        return zahtev.isXHR() ? zonaKvalifikacijePrikaz.getBody() : null;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Trka Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaTrke(){
        trka.setVelId(velikaNagradaTrke);
        Calendar c = Calendar.getInstance();
        c.setTime(trka.getVremeTrke());
        c.add(Calendar.HOUR_OF_DAY, satTrk);
        c.add(Calendar.MINUTE, minutTrk);
        trka.setVremeTrke(c.getTime());
        trkDAO.dodajIliIzmeniTrku(trka);
        velikaNagradaTrke = null;
        trka = new Trka();
        satTrk = 0;
        minutTrk = 0;
        return this;
    }
    
    Object onActionFromResetTrke(){
        velikaNagradaTrke = null;
        trka = new Trka();
        satTrk = 0;
        minutTrk = 0;
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTrke.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditTrka(Trka trkZaMenjanje){
        trka = trkZaMenjanje;
        Calendar c = Calendar.getInstance();
        c.setTime(trka.getVremeTrke());
        satTrk = c.get(Calendar.HOUR_OF_DAY);
        minutTrk = c.get(Calendar.MINUTE);
        velikaNagradaTrke = trka.getVelId();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTrke.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteTrka(Long id){
        trkDAO.obrisiTrku(id);
        trkePrikaz = trkDAO.getListaSvihTrkaSezone(vrednostSezonePrikaz);
        if(trkePrikaz.isEmpty())
            trkePrikaz.add(new Trka());
        return zahtev.isXHR() ? zonaTrkePrikaz.getBody() : null;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Pozicioni Rezultat Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaTreningRezultata(){
        pozicioniRezultat.setVozId(vozacTreningRezultata);
        if(pozicioniRezultat.getId()==null)
            pozicioniRezultat.setTreId(treningVelikeNagrade);
        prDAO.dodajIliIzmeniPozicioniRezultat(pozicioniRezultat);
        vozacTreningRezultata = new Vozac();
        treningVelikeNagrade = new Trening();
        kvalifikacijeVelikeNagrade = new Kvalifikacije();
        pozicioniRezultat = new PozicioniRezultat();
        return this;
    }
    
    @CommitAfter
    Object onSuccessFromUnosIzmenaKvalifikacionogRezultata(){
        pozicioniRezultat.setVozId(vozacKvalifikacionogRezultata);
        if(pozicioniRezultat.getId()==null)
            pozicioniRezultat.setKvaId(kvalifikacijeVelikeNagrade);
        prDAO.dodajIliIzmeniPozicioniRezultat(pozicioniRezultat);
        vozacKvalifikacionogRezultata = new Vozac();
        treningVelikeNagrade = new Trening();
        kvalifikacijeVelikeNagrade = new Kvalifikacije();
        pozicioniRezultat = new PozicioniRezultat();
        return this;
    }
    
    Object onActionFromResetPozicionogRezultata(){
        izborVelikeNagradePR = null;
        vozacTreningRezultata = new Vozac();
        vozacKvalifikacionogRezultata = new Vozac();
        treningVelikeNagrade = new Trening();
        kvalifikacijeVelikeNagrade = new Kvalifikacije();
        pozicioniRezultat = new PozicioniRezultat();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaPozicionogRezultata.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditPR(PozicioniRezultat prZaMenjanje){
        pozicioniRezultat = prZaMenjanje;
        if(treningVelikeNagradePRprikaz!=null){
            vozacTreningRezultata = pozicioniRezultat.getVozId();
        } else {
            vozacKvalifikacionogRezultata = pozicioniRezultat.getVozId();
        }
        treningVelikeNagrade = pozicioniRezultat.getTreId();
        kvalifikacijeVelikeNagrade = pozicioniRezultat.getKvaId();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaPozicionogRezultata.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeletePR(Long id){
        prDAO.obrisiPozicioniRezultat(id);
        if(treningVelikeNagradePRprikaz!=null){
            pozicioniRezultatPrikaz = prDAO.getListaSvihPozicionihRezultataTreninga(treningVelikeNagradePRprikaz);
        } else if (kvalifikacijaVelikeNagradePRprikaz!=null){
            pozicioniRezultatPrikaz = prDAO.getListaSvihPozicionihRezultataKvalifikacija(kvalifikacijaVelikeNagradePRprikaz);
        }
        if(pozicioniRezultatPrikaz.isEmpty()){
            pozicioniRezultatPrikaz.add(new PozicioniRezultat());
        }
        return zahtev.isXHR() ? zonaPozicioniRezultatPrikaz.getBody() : null;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Trkački Rezultat Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaTrkackogRezultata(){
        trkackiRezultat.setVozId(vozacTrkackogRezultata);
        if(trkackiRezultat.getId()==null)
            trkackiRezultat.setTrkId(trkaVelikeNagrade);
        trDAO.dodajIliIzmeniTrkackiRezultat(trkackiRezultat);
        vozacTrkackogRezultata = new Vozac();
        trkaVelikeNagrade = new Trka();
        trkackiRezultat = new TrkackiRezultat();
        return this;
    }
    
    Object onActionFromResetTrkackogRezultata(){
        izborVelikeNagradeTR = null;
        vozacTrkackogRezultata = new Vozac();
        trkaVelikeNagrade = new Trka();
        trkackiRezultat = new TrkackiRezultat();
        if(zahtev.isXHR()){
            psvRR.addRender(zonaFormeUnosIzmenaTrkackogRezultata).addRender(zonaFormeIzborTrkackiRezultatUnos);
        }
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTrkackogRezultata.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditTR(TrkackiRezultat trZaMenjanje){
        trkackiRezultat = trZaMenjanje;
        if(trkaVelikeNagradeTRprikaz!=null){
            vozacTrkackogRezultata = trkackiRezultat.getVozId();
        }
        trkaVelikeNagrade = trkackiRezultat.getTrkId();
        if(zahtev.isXHR()){
            psvRR.addRender(zonaFormeUnosIzmenaTrkackogRezultata).addRender(zonaFormeIzborTrkackiRezultatUnos);
        }
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTrkackogRezultata.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteTR(Long id){
        trDAO.obrisiTrkackiRezultat(id);
        trkackiRezultatPrikaz = trDAO.getListaSvihTrkackihRezultataTrke(trkaVelikeNagradeTRprikaz);
        if(trkackiRezultatPrikaz.isEmpty())
            trkackiRezultatPrikaz.add(new TrkackiRezultat());
        return zahtev.isXHR() ? zonaFormeUnosIzmenaTrkackogRezultata.getBody() : null;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Klasifikacija vozača metode">
    @CommitAfter
    Object onSuccessFromIzmenaKlasifikacijeVozaca(){
        if(klasifikacijaVozac.getVozId()!=null){
            klDAO.dodajIliIzmeniKlasifikaciju(klasifikacijaVozac);
            klasifikacijaVozac = new Klasifikacija();
        } else {
            izmenaKlasifikacijeVozaca.recordError("Nije izabran vozač");
            return null;
        }
        return zahtev.isXHR() ? zonaFormeIzmenaKlasifikacijeVozaca.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditKlv(Klasifikacija klZaMenjanje){
        klasifikacijaVozac = klZaMenjanje;
        return zahtev.isXHR() ? zonaFormeIzmenaKlasifikacijeVozaca.getBody() : null;
    }
    
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Klasifikacija tima metode">
    @CommitAfter
    Object onSuccessFromIzmenaKlasifikacijeTima(){
        if(klasifikacijaTim.getTimId()!=null){
            klDAO.dodajIliIzmeniKlasifikaciju(klasifikacijaTim);
            klasifikacijaTim = new Klasifikacija();
        } else {
            izmenaKlasifikacijeVozaca.recordError("Nije izabran tim");
            return null;
        }
        return zahtev.isXHR() ? zonaFormeIzmenaKlasifikacijeTima.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditKlt(Klasifikacija klZaMenjanje){
        klasifikacijaTim = klZaMenjanje;
        return zahtev.isXHR() ? zonaFormeIzmenaKlasifikacijeTima.getBody() : null;
    }
    
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Diskusione Grupe Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaDiskusioneGrupe(){
        dgrupa.setKatId(kategorijaDg);
        dgDAO.dodajIliIzmeniDiskusionuGrupu(dgrupa);
        kategorijaDg = new Kategorija();
        dgrupa = new DiskusionaGrupa();
        return this;
    }
    
    Object onActionFromResetDg(){
        kategorijaDg = new Kategorija();
        dgrupa = new DiskusionaGrupa();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaDiskusioneGrupe.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditDg(DiskusionaGrupa dgZaMenjanje){
        dgrupa = dgZaMenjanje;
        kategorijaDg = dgrupa.getKatId();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaDiskusioneGrupe.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteDg(Long id){
        dgDAO.obrisiDiskusionuGrupu(id);
        dgrupe = dgDAO.getListaSvihDiskusionihGrupaKategorije(izborKategorijeDg);
        if(dgrupe.isEmpty())
            dgrupe.add(new DiskusionaGrupa());
        return zahtev.isXHR() ? zonaDgrupePrikaz.getBody() : null;
    }
    
    Object onActionFromIzaberiDg(DiskusionaGrupa izabrana){
        izabranaDg = izabrana;
        prikazTabelaDiskusije = true;
//        if(zahtev.isXHR()){
//            psvRR.addRender(zonaPrikazTabelaDiskusije).addRender(zonaPrikazTabelaDiskusije2);
//        }
//        return zahtev.isXHR() ? zonaPrikazTabelaDiskusije.getBody() : null;
//        return zahtev.isXHR() ? zonaPrikazTabelaDiskusije.getBody() : null;
        return this;
    }
    
    @CommitAfter
    Object onActionFromDeleteDiskusija(Long id){
        diDAO.obrisiDiskusiju(id);
        diskusije = diDAO.getListaSvihDiskusijaDiskusioneGrupe(izabranaDg);
        if(diskusije.isEmpty()){
            diskusije.add(new Diskusija());
        }
        return zahtev.isXHR() ? zonaPrikazTabelaDiskusije.getBody() : null;
    }
    
    Object onActionFromResetDiskusijaPrikaz(){
        izabranaDg = null;
        prikazTabelaDiskusije = false;
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazTabelaDiskusije).addRender(zonaPrikazTabelaDiskusije2);
        }
        return zahtev.isXHR() ? zonaPrikazTabelaDiskusije.getBody() : null;
//        return zahtev.isXHR() ? zonaPrikazTabelaDiskusije.getBody() : null;
    }
//     </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Država Metode">
    @CommitAfter
    Object onSuccessFromUnosIzmenaDrzave(){
        drDAO.dodajIliIzmeniDrzavu(drzava);
        drzave = drDAO.getListaSvihDrzava();
        drzava=new Drzava();
//        if(zahtev.isXHR()){
//            psvRR.addRender(zonaGridaDrzava).addRender(zonaFormeUnosIzmenaDrzave);
//        }
//        return zahtev.isXHR() ? zonaGridaDrzava.getBody() : null;
        return this;
    }
    
    Object onActionFromResetDrzava(){
        drzava=new Drzava();
        return zahtev.isXHR() ? zonaFormeUnosIzmenaDrzave.getBody() : null;
//        return this;
    }
    
    @CommitAfter
    Object onActionFromEditDrzava(Drzava drZaMenjanje){
        drzava = drZaMenjanje;
        return zahtev.isXHR() ? zonaFormeUnosIzmenaDrzave.getBody() : null;
//        return this;
    }
    
    @CommitAfter
    Object onActionFromDeleteDrzava(Long id){
        drDAO.obrisiDrzavu(id);
        drzave = drDAO.getListaSvihDrzava();
        if(drzave.isEmpty())
            drzave.add(new Drzava());
        return zahtev.isXHR() ? zonaGridaDrzava.getBody() : null;
//        return this;
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get metode">
    
    public String getMD5Hash(String zaHash) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(zaHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
            }
    }

    public boolean getPrikazArtikala() {
        return prikazArtikala;
    }

    public boolean getPrikazKlijenata() {
        return prikazKlijenata;
    }

    public boolean getPrikazDiskusionihGrupa() {
        return prikazDiskusionihGrupa;
    }

    public boolean getPrikazSampionata() {
        return prikazSampionata;
    }
    
    public boolean getPrikazOstalog() {
        return prikazOstalog;
    }
    
    public JSONObject getOptions(){
        JSONObject json = new JSONObject();
        json.put("bJQueryUI", true);
        json.put("bStateSave", true);
        json.put("bAutoWidth", true);
        return json;
    }
    
    public String getOmiljenaKategorijaTabela(){
        if(redkorisnik.getKatId()!=null){
            return redkorisnik.getKatId().getNazivKategorije();
        } else {
            return "";
        }
    }
    
    public String getTipArtikla(){
        if(redartikal.getTipId()!=null){
            return redartikal.getTipId().getNazivTipa();
        } else {
            return "";
        }
    }
    
    public boolean getImaKorisnika(){
        if(korisnik==null){
            return false;
        }
        return korisnik.getId()!=null;
    }
    
    public boolean getPrikazUnosForme(){
        return prikazUnosForme;
    }
    
    public boolean getPrikazTabelePodataka(){
        return prikazTabelePodataka;
    }
    
    public boolean getUnosFormaVozac(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.Vozači);
    }
    
    public boolean getUnosFormaTim(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.Timovi);
    }
    
    public boolean getUnosFormaVelikaNagrada(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.VelikeNagrade);
    }
    
    public boolean getUnosFormaTrening(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.Treninzi);
    }
    
    public boolean getUnosFormaKvalifikacije(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.Kvalifikacije);
    }
                        
    public boolean getUnosFormaTrka(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.Trke);
    }
                        
    public boolean getUnosFormaPozicioniRezultat(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.PozicioniRezultati);
    }
    
    public boolean getUnosPozicionogRezultata(){
        return unosPR;
    }
    
    public boolean getUnosTreningRezultata(){
        return unosTreRez;
    }
                        
    public boolean getUnosFormaRezultatTrke(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.TrkackiRezultati);
    }
    
    public boolean getUnosTrkackogRezultata(){
        return unosTR;
    }
    
    public boolean getIzmenaFormaKlasifikacijaVozac(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.TabelaVozaca);
    }
    
    public boolean getIzmenaFormaKlasifikacijaTim(){
        if(tipoviPodatakaUnos==null)
            return false;
        return tipoviPodatakaUnos.equals(tipoviPodatakaUnos.TabelaTimova);
    }
    
    public boolean getPrikazTabeleVozac(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.Vozači);
    }
    
    public boolean getPrikazTabeleTim(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.Timovi);
    }
    
    public boolean getPrikazTabeleVelikaNagrada(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.VelikeNagrade);
    }
    
    public boolean getPrikazTabeleTrening(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.Treninzi);
    }
    
    public boolean getPrikazTabeleKvalifikacije(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.Kvalifikacije);
    }
                        
    public boolean getPrikazTabeleTrka(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.Trke);
    }
                        
    public boolean getPrikazTabelePozicioniRezultat(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.PozicioniRezultati);
    }
    
    public boolean getPrikazPozicionogRezultata(){
        return prikazPR;
    }
                        
    public boolean getPrikazTabeleRezultatTrke(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.TrkackiRezultati);
    }
    
    public boolean getPrikazTrkackogRezultata(){
        return prikazTR;
    }
    
    public boolean getPrikazKlasifikacijeVozaca(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.TabelaVozaca);
    }
    
    public boolean getPrikazKlasifikacijeTimova(){
        if(tipoviPodatakaPrikaz==null)
            return false;
        return tipoviPodatakaPrikaz.equals(tipoviPodatakaPrikaz.TabelaTimova);
    }
    
    public String getBodoviPrikaz(){
        if(redvozac.getKlaId()!=null){
            return ""+redvozac.getKlaId().getBodovi();
        } else {
            return "";
        }
    }
    
    public String getPozicijaPrikaz(){
        if(redvozac.getKlaId()!=null){
            return ""+redvozac.getKlaId().getPozicijaK();
        } else {
            return "";
        }
    }
    
    public String getTimPrikaz(){
        if(redvozac.getTimId()!=null){
            return ""+redvozac.getTimId().getNazivTima();
        } else {
            return "";
        }
    }
    
    public String getDrzavaPrikaz(){
        if(redvozac.getDrzId()!=null){
            return ""+redvozac.getDrzId().getNazivDrzave();
        } else {
            return "";
        }
    }
    
    public String getBodoviTimaPrikaz(){
        if(redtim.getKlaId()!=null){
            return ""+redtim.getKlaId().getBodovi();
        } else {
            return "";
        }
    }
    
    public String getPozicijaTimaPrikaz(){
        if(redtim.getKlaId()!=null){
            return ""+redtim.getKlaId().getPozicijaK();
        } else {
            return "";
        }
    }
    
    public String getDrzavaTimaPrikaz(){
        if(redtim.getDrzId()!=null){
            return ""+redtim.getDrzId().getNazivDrzave();
        } else {
            return "";
        }
    }
    
    public String getPobednikVelikeNagradePrikaz(){
        if(redvn.getVozId()!=null){
            return redvn.getVozId().toString();
        } else {
            return "";
        }
    }
    
    public String getDrzavaVelikeNagradePrikaz(){
        if(redvn.getDrzId()!=null){
            return redvn.getDrzId().getNazivDrzave();
        } else {
            return "";
        }
    }
    
    public String getVelikaNagradaTreningaPrikaz(){
        if(redtrening.getVelId()!=null){
            return redtrening.getVelId().getNazivVn();
        } else {
            return "";
        }
    }
    
    public String getVelikaNagradaKvalifikacijaPrikaz(){
        if(redkvalifikacije.getVelId()!=null){
            return redkvalifikacije.getVelId().getNazivVn();
        } else {
            return "";
        }
    }
    
    public String getVelikaNagradaTrkaPrikaz(){
        if(redtrka.getVelId()!=null){
            return redtrka.getVelId().getNazivVn();
        } else {
            return "";
        }
    }
    
    public String getVozacPozicionogRezultataPrikaz(){
        if(redpr.getVozId()!=null){
            return redpr.getVozId().toString();
        } else {
            return "";
        }
    }
    
    public String getVozacTrkackogRezultataPrikaz(){
        if(redtr.getVozId()!=null){
            return redtr.getVozId().toString();
        } else {
            return "";
        }
    }
    
    public String getVozacKlasifikacijaPrikaz(){
        if(redklv.getVozId()!=null){
            return redklv.getVozId().toString();
        } else {
            return "";
        }
    }
    
    public String getTimKlasifikacijaPrikaz(){
        if(redklt.getTimId()!=null){
            return redklt.getTimId().getNazivTima();
        } else {
            return "";
        }
    }
    
    public boolean getPrikazTabelaDg(){
        return prikazTabelaDg;
    }
    
    public boolean getPrikazTabelaDiskusije(){
        return prikazTabelaDiskusije;
    }
    
    public String getIzabranaDiskusionaGrupa(){
        if(izabranaDg!=null){
            return izabranaDg.getKatId().getNazivKategorije()+"-"+izabranaDg.getNazivDg();
        }
        return "";
    }
    //</editor-fold>

    @CommitAfter
    private void unosNoveKategorije() {
        Kategorija novaKat = new Kategorija();
        novaKat.setNazivKategorije(novaKategorijaUnos);
        kaDAO.dodajIliIzmeniKategoriju(novaKat);
    }

    @CommitAfter
    private void unosNoveSezone() {
        Sezona novaSez = new Sezona();
        novaSez.setGodina(novaSezonaUnos);
        novaSez.setKatId(vrednostKategorijeUnos);
        seDAO.dodajIliIzmeniSezonu(novaSez);
    }
}
