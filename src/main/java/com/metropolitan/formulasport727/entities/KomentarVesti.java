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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Klasa koja predstavlja entitet Komentar Vesti iz baze u aplikaciji
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "komentar_vesti")
@NamedQueries({
    @NamedQuery(name = "KomentarVesti.findAll", query = "SELECT k FROM KomentarVesti k")})
public class KomentarVesti extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "VREME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vreme;
    @Column(name = "NASLOV_KOMENTARA")
    private String naslovKomentara;
    @Basic(optional = false)
    @Column(name = "TEKST_KOMENTARA")
    @Validate("required")
    private String tekstKomentara;
    @JoinColumn(name = "VES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Vest vesId;
    @JoinColumn(name = "KOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Korisnik korId;

    @Inject
    public KomentarVesti() {
    }

    public KomentarVesti(Long id) {
        this.id = id;
    }

    public KomentarVesti(Long id, Date vreme, String tekstKomentara) {
        this.id = id;
        this.vreme = vreme;
        this.tekstKomentara = tekstKomentara;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public String getNaslovKomentara() {
        return naslovKomentara;
    }

    public void setNaslovKomentara(String naslovKomentara) {
        this.naslovKomentara = naslovKomentara;
    }

    public String getTekstKomentara() {
        return tekstKomentara;
    }

    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }

    public Vest getVesId() {
        return vesId;
    }

    public void setVesId(Vest vesId) {
        this.vesId = vesId;
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
        if (!(object instanceof KomentarVesti)) {
            return false;
        }
        KomentarVesti other = (KomentarVesti) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Komentar Vesti: " + getId();
    }
    
}
