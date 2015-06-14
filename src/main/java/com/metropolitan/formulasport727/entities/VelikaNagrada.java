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
@Table(name = "velika_nagrada")
@NamedQueries({
    @NamedQuery(name = "VelikaNagrada.findAll", query = "SELECT v FROM VelikaNagrada v")})
public class VelikaNagrada extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_VN")
    private String nazivVn;
    @Basic(optional = false)
    @Column(name = "DATUM_POCETKA")
    @Temporal(TemporalType.DATE)
    private Date datumPocetka;
    @Basic(optional = false)
    @Column(name = "DATUM_ZAVRSETKA")
    @Temporal(TemporalType.DATE)
    private Date datumZavrsetka;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "velId")
    private List<Kvalifikacije> kvalifikacijeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "velId")
    private List<Trka> trkaList;
    @JoinColumn(name = "VOZ_ID", referencedColumnName = "ID")
    @ManyToOne
    private Vozac vozId;
    @JoinColumn(name = "SEZ_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Sezona sezId;
    @JoinColumn(name = "DRZ_ID", referencedColumnName = "ID")
    @ManyToOne
    private Drzava drzId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "velId")
    private List<Trening> treningList;

    public VelikaNagrada() {
    }

    public VelikaNagrada(Long id) {
        this.id = id;
    }

    public VelikaNagrada(Long id, String nazivVn, Date datumPocetka, Date datumZavrsetka) {
        this.id = id;
        this.nazivVn = nazivVn;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivVn() {
        return nazivVn;
    }

    public void setNazivVn(String nazivVn) {
        this.nazivVn = nazivVn;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public List<Kvalifikacije> getKvalifikacijeList() {
        return kvalifikacijeList;
    }

    public void setKvalifikacijeList(List<Kvalifikacije> kvalifikacijeList) {
        this.kvalifikacijeList = kvalifikacijeList;
    }

    public List<Trka> getTrkaList() {
        return trkaList;
    }

    public void setTrkaList(List<Trka> trkaList) {
        this.trkaList = trkaList;
    }

    public Vozac getVozId() {
        return vozId;
    }

    public void setVozId(Vozac vozId) {
        this.vozId = vozId;
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

    public List<Trening> getTreningList() {
        return treningList;
    }

    public void setTreningList(List<Trening> treningList) {
        this.treningList = treningList;
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
        if (!(object instanceof VelikaNagrada)) {
            return false;
        }
        VelikaNagrada other = (VelikaNagrada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Velika Nagrada: "+getNazivVn();
    }
    
}
