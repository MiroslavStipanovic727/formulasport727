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
@Table(name = "klasifikacija")
@NamedQueries({
    @NamedQuery(name = "Klasifikacija.findAll", query = "SELECT k FROM Klasifikacija k")})
public class Klasifikacija extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "POZICIJA_K")
    private int pozicijaK;
    @Basic(optional = false)
    @Column(name = "BODOVI")
    private double bodovi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klaId")
    private List<Tim> timList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klaId")
    private List<Vozac> vozacList;
    @JoinColumn(name = "VOZ_ID", referencedColumnName = "ID")
    @ManyToOne
    private Vozac vozId;
    @JoinColumn(name = "TIM_ID", referencedColumnName = "ID")
    @ManyToOne
    private Tim timId;
    @JoinColumn(name = "SEZ_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Sezona sezId;

    public Klasifikacija() {
    }

    public Klasifikacija(Long id) {
        this.id = id;
    }

    public Klasifikacija(Long id, int pozicijaK, double bodovi) {
        this.id = id;
        this.pozicijaK = pozicijaK;
        this.bodovi = bodovi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPozicijaK() {
        return pozicijaK;
    }

    public void setPozicijaK(int pozicijaK) {
        this.pozicijaK = pozicijaK;
    }

    public double getBodovi() {
        return bodovi;
    }

    public void setBodovi(double bodovi) {
        this.bodovi = bodovi;
    }

    public List<Tim> getTimList() {
        return timList;
    }

    public void setTimList(List<Tim> timList) {
        this.timList = timList;
    }

    public List<Vozac> getVozacList() {
        return vozacList;
    }

    public void setVozacList(List<Vozac> vozacList) {
        this.vozacList = vozacList;
    }

    public Vozac getVozId() {
        return vozId;
    }

    public void setVozId(Vozac vozId) {
        this.vozId = vozId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klasifikacija)) {
            return false;
        }
        Klasifikacija other = (Klasifikacija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Klasifikacija: "+getId();
    }
    
}
