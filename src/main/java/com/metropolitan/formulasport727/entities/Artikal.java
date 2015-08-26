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
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Klasa koja predstavlja entitet Artikal iz baze u aplikaciji 
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "artikal")
@NamedQueries({
    @NamedQuery(name = "Artikal.findAll", query = "SELECT a FROM Artikal a")})
public class Artikal extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_ARTIKLA")
    @Validate("required")
    private String nazivArtikla;
    @Column(name = "SLIKA_")
    private String slika;
    @Basic(optional = false)
    @Column(name = "KRATAK_OPIS")
    @Validate("required")
    private String kratakOpis;
    @Basic(optional = false)
    @Column(name = "CENA")
    @Validate("required")
    private double cena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artId")
    private List<KorpaArtikal> korpaArtikalList;
    @JoinColumn(name = "TIP_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipArtikla tipId;
    
    @Inject
    public Artikal() {
    }

    public Artikal(Long id) {
        this.id = id;
    }

    public Artikal(Long id, String nazivArtikla, String kratakOpis, double cena) {
        this.id = id;
        this.nazivArtikla = nazivArtikla;
        this.kratakOpis = kratakOpis;
        this.cena = cena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getKratakOpis() {
        return kratakOpis;
    }

    public void setKratakOpis(String kratakOpis) {
        this.kratakOpis = kratakOpis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public List<KorpaArtikal> getKorpaArtikalList() {
        return korpaArtikalList;
    }

    public void setKorpaArtikalList(List<KorpaArtikal> korpaArtikalList) {
        this.korpaArtikalList = korpaArtikalList;
    }

    public TipArtikla getTipId() {
        return tipId;
    }

    public void setTipId(TipArtikla tipId) {
        this.tipId = tipId;
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
        if (!(object instanceof Artikal)) {
            return false;
        }
        Artikal other = (Artikal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNazivArtikla();
    }
    
}
