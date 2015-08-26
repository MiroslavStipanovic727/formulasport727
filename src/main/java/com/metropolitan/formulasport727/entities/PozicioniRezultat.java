/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Klasa koja predstavlja entitet Pozicioni Rezultat iz baze u aplikaciji
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "pozicioni_rezultat")
@NamedQueries({
    @NamedQuery(name = "PozicioniRezultat.findAll", query = "SELECT p FROM PozicioniRezultat p")})
public class PozicioniRezultat extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "VREME_REZULTATA")
    @Validate("required")
    private String vremeRezultata;
    @Basic(optional = false)
    @Column(name = "POZICIJA_PR")
    @Validate("required")
    private int pozicijaPr;
    @JoinColumn(name = "VOZ_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Vozac vozId;
    @JoinColumn(name = "TRE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Trening treId;
    @JoinColumn(name = "KVA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Kvalifikacije kvaId;

    @Inject
    public PozicioniRezultat() {
    }

    public PozicioniRezultat(Long id) {
        this.id = id;
    }

    public PozicioniRezultat(Long id, String vremeRezultata, int pozicijaPr) {
        this.id = id;
        this.vremeRezultata = vremeRezultata;
        this.pozicijaPr = pozicijaPr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVremeRezultata() {
        return vremeRezultata;
    }

    public void setVremeRezultata(String vremeRezultata) {
        this.vremeRezultata = vremeRezultata;
    }

    public int getPozicijaPr() {
        return pozicijaPr;
    }

    public void setPozicijaPr(int pozicijaPr) {
        this.pozicijaPr = pozicijaPr;
    }

    public Vozac getVozId() {
        return vozId;
    }

    public void setVozId(Vozac vozId) {
        this.vozId = vozId;
    }

    public Trening getTreId() {
        return treId;
    }

    public void setTreId(Trening treId) {
        this.treId = treId;
    }

    public Kvalifikacije getKvaId() {
        return kvaId;
    }

    public void setKvaId(Kvalifikacije kvaId) {
        this.kvaId = kvaId;
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
        if (!(object instanceof PozicioniRezultat)) {
            return false;
        }
        PozicioniRezultat other = (PozicioniRezultat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pozicioni Rezultat: "+getId();
    }
    
}
