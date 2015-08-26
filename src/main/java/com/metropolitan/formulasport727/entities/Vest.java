/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Klasa koja predstavlja entitet Vest iz baze u aplikaciji
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "vest")
@NamedQueries({
    @NamedQuery(name = "Vest.findAll", query = "SELECT v FROM Vest v")})
public class Vest extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NASLOV_VESTI")
    private String naslovVesti;
    @Basic(optional = false)
    @Lob
    @Column(name = "TEKST_VESTI")
    private String tekstVesti;
    @Basic(optional = false)
    @Column(name = "VREME_OBJAVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeObjave;
    @Basic(optional = false)
    @Column(name = "SLIKA_VESTI")
    private String slikaVesti;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vesId")
    private List<KomentarVesti> komentarVestiList;
    @JoinColumn(name = "KOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Korisnik korId;
    @JoinColumn(name = "KAT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Kategorija katId;

    public Vest() {
    }

    public Vest(Long id) {
        this.id = id;
    }

    public Vest(Long id, String naslovVesti, String tekstVesti, Date vremeObjave, String slikaVesti) {
        this.id = id;
        this.naslovVesti = naslovVesti;
        this.tekstVesti = tekstVesti;
        this.vremeObjave = vremeObjave;
        this.slikaVesti = slikaVesti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslovVesti() {
        return naslovVesti;
    }

    public void setNaslovVesti(String naslovVesti) {
        this.naslovVesti = naslovVesti;
    }

    public String getTekstVesti() {
        return tekstVesti;
    }

    public void setTekstVesti(String tekstVesti) {
        this.tekstVesti = tekstVesti;
    }

    public Date getVremeObjave() {
        return vremeObjave;
    }

    public void setVremeObjave(Date vremeObjave) {
        this.vremeObjave = vremeObjave;
    }

    public String getSlikaVesti() {
        return slikaVesti;
    }

    public void setSlikaVesti(String slikaVesti) {
        this.slikaVesti = slikaVesti;
    }

    public List<KomentarVesti> getKomentarVestiList() {
        return komentarVestiList;
    }

    public void setKomentarVestiList(List<KomentarVesti> komentarVestiList) {
        this.komentarVestiList = komentarVestiList;
    }

    public Korisnik getKorId() {
        return korId;
    }

    public void setKorId(Korisnik korId) {
        this.korId = korId;
    }

    public Kategorija getKatId() {
        return katId;
    }

    public void setKatId(Kategorija katId) {
        this.katId = katId;
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
        if (!(object instanceof Vest)) {
            return false;
        }
        Vest other = (Vest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNaslovVesti();
    }
    
}
