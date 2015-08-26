/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Klasa koja predstavlja entitet Korpa Artikal iz baze u aplikaciji
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "korpa_artikal")
@NamedQueries({
    @NamedQuery(name = "KorpaArtikal.findAll", query = "SELECT k FROM KorpaArtikal k")})
public class KorpaArtikal extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "ART_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Artikal artId;
    @JoinColumn(name = "KOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Korpa korId;

    @Inject
    public KorpaArtikal() {
    }

    public KorpaArtikal(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artikal getArtId() {
        return artId;
    }

    public void setArtId(Artikal artId) {
        this.artId = artId;
    }

    public Korpa getKorId() {
        return korId;
    }

    public void setKorId(Korpa korId) {
        this.korId = korId;
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
        if (!(object instanceof KorpaArtikal)) {
            return false;
        }
        KorpaArtikal other = (KorpaArtikal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KorpaArtikal: " + getId();
    }
    
}
