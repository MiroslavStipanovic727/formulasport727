/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Klasa koja predstavlja entitet Trkacki Rezultat iz baze u aplikaciji
 * @author Miroslav Stipanović 727
 */
@Entity
@Table(name = "trkacki_rezultat")
@NamedQueries({
    @NamedQuery(name = "TrkackiRezultat.findAll", query = "SELECT t FROM TrkackiRezultat t")})
public class TrkackiRezultat extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "OSTVARENI_REZULTAT")
    @Validate("required")
    private String ostvareniRezultat;
    @Basic(optional = false)
    @Column(name = "POZICIJA_TR")
    @Validate("required")
    private int pozicijaTr;
    @JoinColumn(name = "VOZ_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Vozac vozId;
    @JoinColumn(name = "TRK_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Trka trkId;

    @Inject
    public TrkackiRezultat() {
    }

    public TrkackiRezultat(Long id) {
        this.id = id;
    }

    public TrkackiRezultat(Long id, String ostvareniRezultat, int pozicijaTr) {
        this.id = id;
        this.ostvareniRezultat = ostvareniRezultat;
        this.pozicijaTr = pozicijaTr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOstvareniRezultat() {
        return ostvareniRezultat;
    }

    public void setOstvareniRezultat(String ostvareniRezultat) {
        this.ostvareniRezultat = ostvareniRezultat;
    }

    public int getPozicijaTr() {
        return pozicijaTr;
    }

    public void setPozicijaTr(int pozicijaTr) {
        this.pozicijaTr = pozicijaTr;
    }

    public Vozac getVozId() {
        return vozId;
    }

    public void setVozId(Vozac vozId) {
        this.vozId = vozId;
    }

    public Trka getTrkId() {
        return trkId;
    }

    public void setTrkId(Trka trkId) {
        this.trkId = trkId;
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
        if (!(object instanceof TrkackiRezultat)) {
            return false;
        }
        TrkackiRezultat other = (TrkackiRezultat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trkački Rezultat: "+getVozId().toString()+" - " + getOstvareniRezultat();
    }
    
}
