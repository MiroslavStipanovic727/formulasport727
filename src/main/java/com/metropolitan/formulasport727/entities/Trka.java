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
@Table(name = "trka")
@NamedQueries({
    @NamedQuery(name = "Trka.findAll", query = "SELECT t FROM Trka t")})
public class Trka extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV_TRKE")
    private String nazivTrke;
    @Column(name = "NAJBRZI_KRUG")
    private String najbrziKrug;
    @Basic(optional = false)
    @Column(name = "VREME_TRKE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeTrke;
    @JoinColumn(name = "VEL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private VelikaNagrada velId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trkId")
    private List<TrkackiRezultat> trkackiRezultatList;

    public Trka() {
    }

    public Trka(Long id) {
        this.id = id;
    }

    public Trka(Long id, String nazivTrke, Date vremeTrke) {
        this.id = id;
        this.nazivTrke = nazivTrke;
        this.vremeTrke = vremeTrke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivTrke() {
        return nazivTrke;
    }

    public void setNazivTrke(String nazivTrke) {
        this.nazivTrke = nazivTrke;
    }

    public String getNajbrziKrug() {
        return najbrziKrug;
    }

    public void setNajbrziKrug(String najbrziKrug) {
        this.najbrziKrug = najbrziKrug;
    }

    public Date getVremeTrke() {
        return vremeTrke;
    }

    public void setVremeTrke(Date vremeTrke) {
        this.vremeTrke = vremeTrke;
    }

    public VelikaNagrada getVelId() {
        return velId;
    }

    public void setVelId(VelikaNagrada velId) {
        this.velId = velId;
    }

    public List<TrkackiRezultat> getTrkackiRezultatList() {
        return trkackiRezultatList;
    }

    public void setTrkackiRezultatList(List<TrkackiRezultat> trkackiRezultatList) {
        this.trkackiRezultatList = trkackiRezultatList;
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
        if (!(object instanceof Trka)) {
            return false;
        }
        Trka other = (Trka) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trka: "+getNazivTrke();
    }
    
}
