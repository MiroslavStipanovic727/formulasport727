/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "korisnik")
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")})
public class Korisnik extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "KORISNICKO_IME")
    private String korisnickoIme;
    @Basic(optional = false)
    @Column(name = "SIFRA")
    private String sifra;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "LOKACIJA")
    private String lokacija;
    @Column(name = "IME_KORISNIKA")
    private String imeKorisnika;
    @Column(name = "PREZIME_KORISNIKA")
    private String prezimeKorisnika;
    @Basic(optional = false)
    @Column(name = "ULOGA")
    private String uloga;
    @JoinColumn(name = "KAT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Kategorija katId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korId")
    private List<KomentarDiskusije> komentarDiskusijeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korId")
    private List<KomentarVesti> komentarVestiList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korId")
    private List<Diskusija> diskusijaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korId")
    private List<Korpa> korpaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korId")
    private List<Vest> vestList;

    public Korisnik() {
    }

    public Korisnik(Long id) {
        this.id = id;
    }

    public Korisnik(Long id, String korisnickoIme, String sifra, String email, String uloga) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.email = email;
        this.uloga = uloga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getImeKorisnika() {
        return imeKorisnika;
    }

    public void setImeKorisnika(String imeKorisnika) {
        this.imeKorisnika = imeKorisnika;
    }

    public String getPrezimeKorisnika() {
        return prezimeKorisnika;
    }

    public void setPrezimeKorisnika(String prezimeKorisnika) {
        this.prezimeKorisnika = prezimeKorisnika;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public Kategorija getKatId() {
        return katId;
    }

    public void setKatId(Kategorija katId) {
        this.katId = katId;
    }

    public List<KomentarDiskusije> getKomentarDiskusijeList() {
        return komentarDiskusijeList;
    }

    public void setKomentarDiskusijeList(List<KomentarDiskusije> komentarDiskusijeList) {
        this.komentarDiskusijeList = komentarDiskusijeList;
    }

    public List<KomentarVesti> getKomentarVestiList() {
        return komentarVestiList;
    }

    public void setKomentarVestiList(List<KomentarVesti> komentarVestiList) {
        this.komentarVestiList = komentarVestiList;
    }

    public List<Diskusija> getDiskusijaList() {
        return diskusijaList;
    }

    public void setDiskusijaList(List<Diskusija> diskusijaList) {
        this.diskusijaList = diskusijaList;
    }

    public List<Korpa> getKorpaList() {
        return korpaList;
    }

    public void setKorpaList(List<Korpa> korpaList) {
        this.korpaList = korpaList;
    }

    public List<Vest> getVestList() {
        return vestList;
    }

    public void setVestList(List<Vest> vestList) {
        this.vestList = vestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Korisnik: " + getEmail();
    }
    
}
