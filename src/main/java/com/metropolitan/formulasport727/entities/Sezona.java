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
@Table(name = "sezona")
@NamedQueries({
    @NamedQuery(name = "Sezona.findAll", query = "SELECT s FROM Sezona s")})
public class Sezona extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "GODINA")
    private String godina;
    @JoinColumn(name = "KAT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Kategorija katId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sezId")
    private List<Tim> timList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sezId")
    private List<VelikaNagrada> velikaNagradaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sezId")
    private List<Vozac> vozacList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sezId")
    private List<Klasifikacija> klasifikacijaList;

    public Sezona() {
    }

    public Sezona(Long id) {
        this.id = id;
    }

    public Sezona(Long id, String godina) {
        this.id = id;
        this.godina = godina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public Kategorija getKatId() {
        return katId;
    }

    public void setKatId(Kategorija katId) {
        this.katId = katId;
    }

    public List<Tim> getTimList() {
        return timList;
    }

    public void setTimList(List<Tim> timList) {
        this.timList = timList;
    }

    public List<VelikaNagrada> getVelikaNagradaList() {
        return velikaNagradaList;
    }

    public void setVelikaNagradaList(List<VelikaNagrada> velikaNagradaList) {
        this.velikaNagradaList = velikaNagradaList;
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
        if (!(object instanceof Sezona)) {
            return false;
        }
        Sezona other = (Sezona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sezona: "+getGodina();
    }
    
}
