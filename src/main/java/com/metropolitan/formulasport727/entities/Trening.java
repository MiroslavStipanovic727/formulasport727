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
@Table(name = "trening")
@NamedQueries({
    @NamedQuery(name = "Trening.findAll", query = "SELECT t FROM Trening t")})
public class Trening extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_TRENINGA")
    private String nazivTreninga;
    @Basic(optional = false)
    @Column(name = "VREME_TRENINGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeTreninga;
    @OneToMany(mappedBy = "treId")
    private List<PozicioniRezultat> pozicioniRezultatList;
    @JoinColumn(name = "VEL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private VelikaNagrada velId;

    public Trening() {
    }

    public Trening(Long id) {
        this.id = id;
    }

    public Trening(Long id, String nazivTreninga, Date vremeTreninga) {
        this.id = id;
        this.nazivTreninga = nazivTreninga;
        this.vremeTreninga = vremeTreninga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivTreninga() {
        return nazivTreninga;
    }

    public void setNazivTreninga(String nazivTreninga) {
        this.nazivTreninga = nazivTreninga;
    }

    public Date getVremeTreninga() {
        return vremeTreninga;
    }

    public void setVremeTreninga(Date vremeTreninga) {
        this.vremeTreninga = vremeTreninga;
    }

    public List<PozicioniRezultat> getPozicioniRezultatList() {
        return pozicioniRezultatList;
    }

    public void setPozicioniRezultatList(List<PozicioniRezultat> pozicioniRezultatList) {
        this.pozicioniRezultatList = pozicioniRezultatList;
    }

    public VelikaNagrada getVelId() {
        return velId;
    }

    public void setVelId(VelikaNagrada velId) {
        this.velId = velId;
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
        if (!(object instanceof Trening)) {
            return false;
        }
        Trening other = (Trening) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trening: "+getNazivTreninga();
    }
    
}
