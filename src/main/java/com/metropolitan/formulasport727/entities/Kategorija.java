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
@Table(name = "kategorija")
@NamedQueries({
    @NamedQuery(name = "Kategorija.findAll", query = "SELECT k FROM Kategorija k")})
public class Kategorija extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_KATEGORIJE")
    private String nazivKategorije;
    @OneToMany(mappedBy = "katId")
    private List<Korisnik> korisnikList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "katId")
    private List<Sezona> sezonaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "katId")
    private List<DiskusionaGrupa> diskusionaGrupaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "katId")
    private List<Vest> vestList;

    public Kategorija() {
    }

    public Kategorija(Long id) {
        this.id = id;
    }

    public Kategorija(Long id, String nazivKategorije) {
        this.id = id;
        this.nazivKategorije = nazivKategorije;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public List<Korisnik> getKorisnikList() {
        return korisnikList;
    }

    public void setKorisnikList(List<Korisnik> korisnikList) {
        this.korisnikList = korisnikList;
    }

    public List<Sezona> getSezonaList() {
        return sezonaList;
    }

    public void setSezonaList(List<Sezona> sezonaList) {
        this.sezonaList = sezonaList;
    }

    public List<DiskusionaGrupa> getDiskusionaGrupaList() {
        return diskusionaGrupaList;
    }

    public void setDiskusionaGrupaList(List<DiskusionaGrupa> diskusionaGrupaList) {
        this.diskusionaGrupaList = diskusionaGrupaList;
    }

    public List<Vest> getVestList() {
        return vestList;
    }

    public void setVestList(List<Vest> vestList) {
        this.vestList = vestList;
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
        if (!(object instanceof Kategorija)) {
            return false;
        }
        Kategorija other = (Kategorija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Kategorija: "+getNazivKategorije();
    }
    
}
