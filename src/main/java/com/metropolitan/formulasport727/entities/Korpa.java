/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Klasa koja predstavlja entitet Korpa iz baze u aplikaciji
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "korpa")
@NamedQueries({
    @NamedQuery(name = "Korpa.findAll", query = "SELECT k FROM Korpa k")})
public class Korpa extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korId")
    private List<KorpaArtikal> korpaArtikalList;
    @JoinColumn(name = "KOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Korisnik korId;

    @Inject
    public Korpa() {
    }

    public Korpa(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<KorpaArtikal> getKorpaArtikalList() {
        return korpaArtikalList;
    }

    public void setKorpaArtikalList(List<KorpaArtikal> korpaArtikalList) {
        this.korpaArtikalList = korpaArtikalList;
    }

    public Korisnik getKorId() {
        return korId;
    }

    public void setKorId(Korisnik korId) {
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
        if (!(object instanceof Korpa)) {
            return false;
        }
        Korpa other = (Korpa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Korpa: "+getId();
    }
    
}
