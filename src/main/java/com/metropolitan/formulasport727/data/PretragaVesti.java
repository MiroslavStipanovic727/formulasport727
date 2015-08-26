/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.data;

import java.util.Date;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Pomoćna data klasa za pretragu vesti koja sadrži datume pretrage
 * @author Miroslav Stipanović 727
 */
public class PretragaVesti {
    @Validate("required")
    private Date datum1;
    @Validate("required")
    private Date datum2;

    @Inject
    public PretragaVesti() {
    }

    public PretragaVesti(Date datum1, Date datum2) {
        this.datum1 = datum1;
        this.datum2 = datum2;
    }

    
    /**
     * @return the datum1
     */
    public Date getDatum1() {
        return datum1;
    }

    /**
     * @param datum1 the datum1 to set
     */
    public void setDatum1(Date datum1) {
        this.datum1 = datum1;
    }

    /**
     * @return the datum2
     */
    public Date getDatum2() {
        return datum2;
    }

    /**
     * @param datum2 the datum2 to set
     */
    public void setDatum2(Date datum2) {
        this.datum2 = datum2;
    }
}
