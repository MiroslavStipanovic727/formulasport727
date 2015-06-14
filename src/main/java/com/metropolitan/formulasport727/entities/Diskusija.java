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
 *
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "diskusija")
@NamedQueries({
    @NamedQuery(name = "Diskusija.findAll", query = "SELECT d FROM Diskusija d")})
public class Diskusija extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NASLOV_DISKUSIJE")
    private String naslovDiskusije;
    @Basic(optional = false)
    @Lob
    @Column(name = "OPIS_DISKUSIJE")
    private String opisDiskusije;
    @Basic(optional = false)
    @Column(name = "VREME_OTVARANJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeOtvaranja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disId")
    private List<KomentarDiskusije> komentarDiskusijeList;
    @JoinColumn(name = "KOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Korisnik korId;
    @JoinColumn(name = "DIS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private DiskusionaGrupa disId;

    public Diskusija() {
    }

    public Diskusija(Long id) {
        this.id = id;
    }

    public Diskusija(Long id, String naslovDiskusije, String opisDiskusije, Date vremeOtvaranja) {
        this.id = id;
        this.naslovDiskusije = naslovDiskusije;
        this.opisDiskusije = opisDiskusije;
        this.vremeOtvaranja = vremeOtvaranja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslovDiskusije() {
        return naslovDiskusije;
    }

    public void setNaslovDiskusije(String naslovDiskusije) {
        this.naslovDiskusije = naslovDiskusije;
    }

    public String getOpisDiskusije() {
        return opisDiskusije;
    }

    public void setOpisDiskusije(String opisDiskusije) {
        this.opisDiskusije = opisDiskusije;
    }

    public Date getVremeOtvaranja() {
        return vremeOtvaranja;
    }

    public void setVremeOtvaranja(Date vremeOtvaranja) {
        this.vremeOtvaranja = vremeOtvaranja;
    }

    public List<KomentarDiskusije> getKomentarDiskusijeList() {
        return komentarDiskusijeList;
    }

    public void setKomentarDiskusijeList(List<KomentarDiskusije> komentarDiskusijeList) {
        this.komentarDiskusijeList = komentarDiskusijeList;
    }

    public Korisnik getKorId() {
        return korId;
    }

    public void setKorId(Korisnik korId) {
        this.korId = korId;
    }

    public DiskusionaGrupa getDisId() {
        return disId;
    }

    public void setDisId(DiskusionaGrupa disId) {
        this.disId = disId;
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
        if (!(object instanceof Diskusija)) {
            return false;
        }
        Diskusija other = (Diskusija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Diskusija: "+getNaslovDiskusije();
    }
    
}
