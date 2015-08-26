/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.pages.diskusionegrupe;

import com.metropolitan.formulasport727.dao.DiskusijaDAO;
import com.metropolitan.formulasport727.data.Uloga;
import com.metropolitan.formulasport727.entities.Diskusija;
import com.metropolitan.formulasport727.entities.DiskusionaGrupa;
import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.pages.DiskusioneGrupe;
import com.metropolitan.formulasport727.services.ProtectedPage;
import java.util.Calendar;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Klasa koja sadrži sve atribute i metode neophodne za funkcionisanje stranice 
 * za unos novih ili izmenu starih diskusija
 * @author Miroslav Stipanović 727
 */
@ProtectedPage
@RolesAllowed(value={"Administrator","Klijent", "Urednik", "Moderator"})
public class NovaDiskusija {
    
    @Persist
    private DiskusionaGrupa diskusionaGrupa;
    
    @Property
    private String naslovDiskusije;
    @Property
    private String opisDiskusije;
    @Inject
    private DiskusijaDAO diDAO;
    
    @Property
    @SessionState
    private Korisnik ulogovaniKorisnik;
    
    @InjectPage
    private DiskusioneGrupe dgStrana; 
    @InjectPage
    private IzabranaDiskusija izDiskusija;
    
    @Persist
    private Diskusija editDiskusija;
    
    void onActivate(){
        if(!ulogovaniKorisnik.getUloga().equals(Uloga.Moderator))
            editDiskusija = new Diskusija();
        if(getIzmenaDiskusije()){
            naslovDiskusije = editDiskusija.getNaslovDiskusije();
            opisDiskusije = editDiskusija.getOpisDiskusije();
        }
    }

    @CommitAfter
    Object onSuccessFromNovaDiskusija(){
        if(getIzmenaDiskusije()){
            getEditDiskusija().setNaslovDiskusije(naslovDiskusije);
            getEditDiskusija().setOpisDiskusije(opisDiskusije);
            diDAO.dodajIliIzmeniDiskusiju(getEditDiskusija());
            izDiskusija.setDiskusija(getEditDiskusija());
            setEditDiskusija(new Diskusija());
            return izDiskusija;
        } else {
            Diskusija nova = new Diskusija();
            nova.setNaslovDiskusije(naslovDiskusije);
            nova.setOpisDiskusije(opisDiskusije);
            nova.setDisId(diskusionaGrupa);
            nova.setKorId(ulogovaniKorisnik);
            nova.setVremeOtvaranja(Calendar.getInstance().getTime());
            if(ulogovaniKorisnik.getUloga().equals(Uloga.Klijent)){
                nova.setOdobrena(false);
            } else {
               nova.setOdobrena(true); 
            }
            diDAO.dodajIliIzmeniDiskusiju(nova);
            return dgStrana;
        }
    }
    
    public DiskusionaGrupa getDiskusionaGrupa() {
        return diskusionaGrupa;
    }

    public void setDiskusionaGrupa(DiskusionaGrupa diskusionaGrupa) {
        this.diskusionaGrupa = diskusionaGrupa;
    }

    public Diskusija getEditDiskusija() {
        return editDiskusija;
    }

    public void setEditDiskusija(Diskusija editDiskusija) {
        this.editDiskusija = editDiskusija;
    }
    
    public boolean getIzmenaDiskusije(){
        return getEditDiskusija()!=null&&getEditDiskusija().getId()!=null;
    }
    
}
