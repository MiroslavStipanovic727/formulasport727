/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages;

// <editor-fold defaultstate="collapsed" desc="import">
import com.metropolitan.formulasport727.dao.ArtikalDAO;
import com.metropolitan.formulasport727.dao.KorpaArtikalDAO;
import com.metropolitan.formulasport727.dao.TipArtiklaDAO;
import com.metropolitan.formulasport727.entities.Artikal;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.entities.Korpa;
import com.metropolitan.formulasport727.entities.KorpaArtikal;
import com.metropolitan.formulasport727.entities.TipArtikla;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
//</editor-fold>


/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje sekcije
 * Web Prodavanica sajta
 * @author Miroslav Stipanović 727
 */
public class WebProdavnica {
    
    // <editor-fold defaultstate="collapsed" desc="Atributi">
    @Inject
    private Request zahtev;
    @Inject
    private AjaxResponseRenderer psvRR;
    @InjectComponent
    private Zone zonaFormeIzborTipa;
    @InjectComponent
    private Zone zonaPrikazaArtikala;
    @InjectComponent
    private Zone zonaPrikazaKorpe;    
    
    @Property
    @Persist
    private TipArtikla izborKategorijeArtikla;
    @Property
    @Persist
    private List<TipArtikla> kategorijeArtikala;
    @Inject
    private TipArtiklaDAO taDAO;
    
    @Property
    private List<Artikal> artikliKategorije;
    @Property
    private Artikal artikalKategorije;
    @Inject
    private ArtikalDAO arDAO;
    
    @Property
    private int kolicina;
    @Property
    @SessionState
    private Korpa korpa;
    @Inject
    private KorpaArtikalDAO kaDAO;
    @Property
    private List<KorpaArtikal> korpaArtikala;
    @Property
    private KorpaArtikal redKorpe;
    
    @Property
    @SessionState
    private Korisnik ulogovaniKorisnik;
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Value Encoderi">
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
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="On Metode">
    void onActivate(){
        if(getUlogovan()){
            if(kategorijeArtikala==null||kategorijeArtikala.isEmpty())
                kategorijeArtikala = taDAO.getListaSvihTipovaArtikla();
            if(izborKategorijeArtikla==null||izborKategorijeArtikla.getId()==null)
                izborKategorijeArtikla = kategorijeArtikala.get(0);
            if(artikliKategorije == null||artikliKategorije.isEmpty())
                artikliKategorije = arDAO.getListaSvihArtikalaTipaArtikla(izborKategorijeArtikla);
            korpaArtikala = kaDAO.getListaSvihJedinstvenihKorpaArtikalaKorpe(korpa);
        }
    }
    
    Object onSuccessFromIzborTipaArtikla(){
        artikliKategorije = arDAO.getListaSvihArtikalaTipaArtikla(izborKategorijeArtikla);
        if(zahtev.isXHR()){
            psvRR.addRender(zonaPrikazaArtikala).addRender(zonaFormeIzborTipa);
        }
        return zahtev.isXHR() ? zonaPrikazaArtikala.getBody() : null;
    }
    
    @CommitAfter
    Object onSuccessFromDodavanjeUKorpu(Artikal artZaDod){
        KorpaArtikal kaZaDod = new KorpaArtikal();
        kaZaDod.setKorId(korpa);
        kaZaDod.setArtId(artZaDod);
        for(int i=0;i<kolicina;i++){
            kaDAO.dodajIliIzmeniKorpuArtikal(kaZaDod);
        }
        korpaArtikala = kaDAO.getListaSvihJedinstvenihKorpaArtikalaKorpe(korpa); 
        return zahtev.isXHR() ? zonaPrikazaKorpe.getBody() : null;
    }
    
    @CommitAfter
    Object onIzbaciIzKorpe(Artikal artZaIzbacivanje){
        List<KorpaArtikal> listaKaZaIzbacivanje = kaDAO.getListaSvihKorpaArtikalaKorpeSaArtiklom(korpa, artZaIzbacivanje);
        for(KorpaArtikal ka : listaKaZaIzbacivanje){
            kaDAO.obrisiKorpuArtikal(ka.getId());
        }
        korpaArtikala = kaDAO.getListaSvihJedinstvenihKorpaArtikalaKorpe(korpa);
        return zahtev.isXHR() ? zonaPrikazaKorpe.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromPlati(){
        List<KorpaArtikal> checkout = kaDAO.getListaSvihKorpaArtikalaKorpe(korpa);
        for(KorpaArtikal ka : checkout){
            kaDAO.obrisiKorpuArtikal(ka.getId());
        }
        korpaArtikala = kaDAO.getListaSvihJedinstvenihKorpaArtikalaKorpe(korpa);
        return zahtev.isXHR() ? zonaPrikazaKorpe.getBody() : null;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Metode">
    public boolean getImaSliku(){
        return artikalKategorije.getSlika()!=null;
    }
    
    public double getUkupnaCena(){
        double ukupnaCena = 0.0;
        for(KorpaArtikal ka : kaDAO.getListaSvihKorpaArtikalaKorpe(korpa))
            ukupnaCena += ka.getArtId().getCena();
        return ukupnaCena;
    }
    
    public boolean getUlogovan(){
        return ulogovaniKorisnik!=null&&ulogovaniKorisnik.getEmail()!=null;
    }
    
    public int kolicinaArtikla(Artikal artZaMerenje){
        return kaDAO.brojPojavljivanjaArtiklaUKorpi(korpa, artZaMerenje);
    }
    //</editor-fold>
}
