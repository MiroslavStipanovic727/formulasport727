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
@Table(name = "tip_artikla")
@NamedQueries({
    @NamedQuery(name = "TipArtikla.findAll", query = "SELECT t FROM TipArtikla t")})
public class TipArtikla extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_TIPA")
    private String nazivTipa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipId")
    private List<Artikal> artikalList;

    public TipArtikla() {
    }

    public TipArtikla(Long id) {
        this.id = id;
    }

    public TipArtikla(Long id, String nazivTipa) {
        this.id = id;
        this.nazivTipa = nazivTipa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    public List<Artikal> getArtikalList() {
        return artikalList;
    }

    public void setArtikalList(List<Artikal> artikalList) {
        this.artikalList = artikalList;
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
        if (!(object instanceof TipArtikla)) {
            return false;
        }
        TipArtikla other = (TipArtikla) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tip Artikla: "+getNazivTipa();
    }
    
}
