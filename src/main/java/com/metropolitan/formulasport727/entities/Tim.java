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
@Table(name = "tim")
@NamedQueries({
    @NamedQuery(name = "Tim.findAll", query = "SELECT t FROM Tim t")})
public class Tim extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_TIMA")
    private String nazivTima;
    @Basic(optional = false)
    @Column(name = "LOGO_TIMA")
    private String logoTima;
    @JoinColumn(name = "KLA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Klasifikacija klaId;
    @JoinColumn(name = "SEZ_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Sezona sezId;
    @JoinColumn(name = "DRZ_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Drzava drzId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timId")
    private List<Vozac> vozacList;
    @OneToMany(mappedBy = "timId")
    private List<Klasifikacija> klasifikacijaList;

    public Tim() {
    }

    public Tim(Long id) {
        this.id = id;
    }

    public Tim(Long id, String nazivTima, String logoTima) {
        this.id = id;
        this.nazivTima = nazivTima;
        this.logoTima = logoTima;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivTima() {
        return nazivTima;
    }

    public void setNazivTima(String nazivTima) {
        this.nazivTima = nazivTima;
    }

    public String getLogoTima() {
        return logoTima;
    }

    public void setLogoTima(String logoTima) {
        this.logoTima = logoTima;
    }

    public Klasifikacija getKlaId() {
        return klaId;
    }

    public void setKlaId(Klasifikacija klaId) {
        this.klaId = klaId;
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

    public List<Vozac> getVozacList() {
        return vozacList;
    }

    public void setVozacList(List<Vozac> vozacList) {
        this.vozacList = vozacList;
    }

    public List<Klasifikacija> getKlasifikacijaList() {
        return klasifikacijaList;
    }

    public void setKlasifikacijaList(List<Klasifikacija> klasifikacijaList) {
        this.klasifikacijaList = klasifikacijaList;
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
        if (!(object instanceof Tim)) {
            return false;
        }
        Tim other = (Tim) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tim: " + getNazivTima();
    }
    
}
