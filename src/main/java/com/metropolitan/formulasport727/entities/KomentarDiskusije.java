/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "komentar_diskusije")
@NamedQueries({
    @NamedQuery(name = "KomentarDiskusije.findAll", query = "SELECT k FROM KomentarDiskusije k")})
public class KomentarDiskusije extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Column(name = "NASLOV_KD")
    private String naslovKd;
    @Basic(optional = false)
    @Lob
    @Column(name = "TEKST_KD")
    private String tekstKd;
    @Basic(optional = false)
    @Column(name = "VREME_POSTAVLJANJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremePostavljanja;
    @JoinColumn(name = "DIS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Diskusija disId;
    @JoinColumn(name = "KOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Korisnik korId;

    public KomentarDiskusije() {
    }

    public KomentarDiskusije(Long id) {
        this.id = id;
    }

    public KomentarDiskusije(Long id, String tekstKd, Date vremePostavljanja) {
        this.id = id;
        this.tekstKd = tekstKd;
        this.vremePostavljanja = vremePostavljanja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslovKd() {
        return naslovKd;
    }

    public void setNaslovKd(String naslovKd) {
        this.naslovKd = naslovKd;
    }

    public String getTekstKd() {
        return tekstKd;
    }

    public void setTekstKd(String tekstKd) {
        this.tekstKd = tekstKd;
    }

    public Date getVremePostavljanja() {
        return vremePostavljanja;
    }

    public void setVremePostavljanja(Date vremePostavljanja) {
        this.vremePostavljanja = vremePostavljanja;
    }

    public Diskusija getDisId() {
        return disId;
    }

    public void setDisId(Diskusija disId) {
        this.disId = disId;
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
        if (!(object instanceof KomentarDiskusije)) {
            return false;
        }
        KomentarDiskusije other = (KomentarDiskusije) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Komentar Diskusije: "+getId();
    }
    
}
