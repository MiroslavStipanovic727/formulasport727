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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "drzava")
@NamedQueries({
    @NamedQuery(name = "Drzava.findAll", query = "SELECT d FROM Drzava d")})
public class Drzava extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_DRZAVE")
    private String nazivDrzave;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drzId")
    private List<Tim> timList;
    @OneToMany(mappedBy = "drzId")
    private List<VelikaNagrada> velikaNagradaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drzId")
    private List<Vozac> vozacList;

    public Drzava() {
    }

    public Drzava(Long id) {
        this.id = id;
    }

    public Drzava(Long id, String nazivDrzave) {
        this.id = id;
        this.nazivDrzave = nazivDrzave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivDrzave() {
        return nazivDrzave;
    }

    public void setNazivDrzave(String nazivDrzave) {
        this.nazivDrzave = nazivDrzave;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drzava)) {
            return false;
        }
        Drzava other = (Drzava) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Država: "+getNazivDrzave();
    }
    
}
