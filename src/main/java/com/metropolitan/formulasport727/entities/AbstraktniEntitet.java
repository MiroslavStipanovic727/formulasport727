/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Klasa koja predstvlja entitet koji nasleđuju svi ostali entiteti
 * @author Miroslav Stipanović 727
 */
@MappedSuperclass
public class AbstraktniEntitet implements Serializable, Comparable<AbstraktniEntitet> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    protected Long id;

    @Override
    public int compareTo(AbstraktniEntitet o) {
        return this.toString().compareTo(o.toString());
    }
    
}
