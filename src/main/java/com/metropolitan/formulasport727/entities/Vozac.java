/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "vozac")
@NamedQueries({
    @NamedQuery(name = "Vozac.findAll", query = "SELECT v FROM Vozac v")})
public class Vozac extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "IME_VOZACA")
    private String imeVozaca;
    @Basic(optional = false)
    @Column(name = "PREZIME_VOZACA")
    private String prezimeVozaca;
    @Column(name = "BROJ")
    private Integer broj;
    @Basic(optional = false)
    @Column(name = "DATUM_RODJENJA")
    @Temporal(TemporalType.DATE)
    private Date datumRodjenja;
    @Basic(optional = false)
    @Column(name = "SLIKA")
    private String slika;
    @OneToMany(mappedBy = "vozId")
    private List<VelikaNagrada> velikaNagradaList;
    @JoinColumn(name = "KLA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Klasifikacija klaId;
    @JoinColumn(name = "TIM_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Tim timId;
    @JoinColumn(name = "SEZ_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Sezona sezId;
    @JoinColumn(name = "DRZ_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Drzava drzId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vozId")
    private List<PozicioniRezultat> pozicioniRezultatList;
    @OneToMany(mappedBy = "vozId")
    private List<Klasifikacija> klasifikacijaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vozId")
    private List<TrkackiRezultat> trkackiRezultatList;

    public Vozac() {
    }

    public Vozac(Long id) {
        this.id = id;
    }

    public Vozac(Long id, String imeVozaca, String prezimeVozaca, Date datumRodjenja, String slika) {
        this.id = id;
        this.imeVozaca = imeVozaca;
        this.prezimeVozaca = prezimeVozaca;
        this.datumRodjenja = datumRodjenja;
        this.slika = slika;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImeVozaca() {
        return imeVozaca;
    }

    public void setImeVozaca(String imeVozaca) {
        this.imeVozaca = imeVozaca;
    }

    public String getPrezimeVozaca() {
        return prezimeVozaca;
    }

    public void setPrezimeVozaca(String prezimeVozaca) {
        this.prezimeVozaca = prezimeVozaca;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public List<VelikaNagrada> getVelikaNagradaList() {
        return velikaNagradaList;
    }

    public void setVelikaNagradaList(List<VelikaNagrada> velikaNagradaList) {
        this.velikaNagradaList = velikaNagradaList;
    }

    public Klasifikacija getKlaId() {
        return klaId;
    }

    public void setKlaId(Klasifikacija klaId) {
        this.klaId = klaId;
    }

    public Tim getTimId() {
        return timId;
    }

    public void setTimId(Tim timId) {
        this.timId = timId;
    }

    public Sezona getSezId() {
        return sezId;
    }

    public void setSezId(Sezona sezId) {
        this.sezId = sezId;
    }

    public Drzava getDrzId() {
        return drzId;
    }

    public void setDrzId(Drzava drzId) {
        this.drzId = drzId;
    }

    public List<PozicioniRezultat> getPozicioniRezultatList() {
        return pozicioniRezultatList;
    }

    public void setPozicioniRezultatList(List<PozicioniRezultat> pozicioniRezultatList) {
        this.pozicioniRezultatList = pozicioniRezultatList;
    }

    public List<Klasifikacija> getKlasifikacijaList() {
        return klasifikacijaList;
    }

    public void setKlasifikacijaList(List<Klasifikacija> klasifikacijaList) {
        this.klasifikacijaList = klasifikacijaList;
    }

    public List<TrkackiRezultat> getTrkackiRezultatList() {
        return trkackiRezultatList;
    }

    public void setTrkackiRezultatList(List<TrkackiRezultat> trkackiRezultatList) {
        this.trkackiRezultatList = trkackiRezultatList;
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
        if (!(object instanceof Vozac)) {
            return false;
        }
        Vozac other = (Vozac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vozač: "+getImeVozaca().substring(0, 1)+"."+getPrezimeVozaca();
    }
    
}
