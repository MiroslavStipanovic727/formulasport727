/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "kvalifikacije")
@NamedQueries({
    @NamedQuery(name = "Kvalifikacije.findAll", query = "SELECT k FROM Kvalifikacije k")})
public class Kvalifikacije extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_KVALIFIKACIJA")
    private String nazivKvalifikacija;
    @Basic(optional = false)
    @Column(name = "VREME_KVALIFIKACIJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeKvalifikacija;
    @JoinColumn(name = "VEL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private VelikaNagrada velId;
    @OneToMany(mappedBy = "kvaId")
    private List<PozicioniRezultat> pozicioniRezultatList;

    public Kvalifikacije() {
    }

    public Kvalifikacije(Long id) {
        this.id = id;
    }

    public Kvalifikacije(Long id, String nazivKvalifikacija, Date vremeKvalifikacija) {
        this.id = id;
        this.nazivKvalifikacija = nazivKvalifikacija;
        this.vremeKvalifikacija = vremeKvalifikacija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivKvalifikacija() {
        return nazivKvalifikacija;
    }

    public void setNazivKvalifikacija(String nazivKvalifikacija) {
        this.nazivKvalifikacija = nazivKvalifikacija;
    }

    public Date getVremeKvalifikacija() {
        return vremeKvalifikacija;
    }

    public void setVremeKvalifikacija(Date vremeKvalifikacija) {
        this.vremeKvalifikacija = vremeKvalifikacija;
    }

    public VelikaNagrada getVelId() {
        return velId;
    }

    public void setVelId(VelikaNagrada velId) {
        this.velId = velId;
    }

    public List<PozicioniRezultat> getPozicioniRezultatList() {
        return pozicioniRezultatList;
    }

    public void setPozicioniRezultatList(List<PozicioniRezultat> pozicioniRezultatList) {
        this.pozicioniRezultatList = pozicioniRezultatList;
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
        if (!(object instanceof Kvalifikacije)) {
            return false;
        }
        Kvalifikacije other = (Kvalifikacije) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Kvalfikacije: "+getNazivKvalifikacija();
    }
    
}
