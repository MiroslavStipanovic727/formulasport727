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
@Table(name = "diskusiona_grupa")
@NamedQueries({
    @NamedQuery(name = "DiskusionaGrupa.findAll", query = "SELECT d FROM DiskusionaGrupa d")})
public class DiskusionaGrupa extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_DG")
    private String nazivDg;
    @Basic(optional = false)
    @Column(name = "OPIS_DG")
    private String opisDg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disId")
    private List<Diskusija> diskusijaList;
    @JoinColumn(name = "KAT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Kategorija katId;

    public DiskusionaGrupa() {
    }

    public DiskusionaGrupa(Long id) {
        this.id = id;
    }

    public DiskusionaGrupa(Long id, String nazivDg, String opisDg) {
        this.id = id;
        this.nazivDg = nazivDg;
        this.opisDg = opisDg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivDg() {
        return nazivDg;
    }

    public void setNazivDg(String nazivDg) {
        this.nazivDg = nazivDg;
    }

    public String getOpisDg() {
        return opisDg;
    }

    public void setOpisDg(String opisDg) {
        this.opisDg = opisDg;
    }

    public List<Diskusija> getDiskusijaList() {
        return diskusijaList;
    }

    public void setDiskusijaList(List<Diskusija> diskusijaList) {
        this.diskusijaList = diskusijaList;
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
        if (!(object instanceof DiskusionaGrupa)) {
            return false;
        }
        DiskusionaGrupa other = (DiskusionaGrupa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Diskusiona grupa: " + getNazivDg();
    }
    
}
