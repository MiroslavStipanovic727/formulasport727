/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Drzava;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa DrzavaDAO
 * @author Miroslav Stipanović 727
 */
public class DrzavaDAOImpl implements DrzavaDAO{
    
    @Inject
    private Session session;

    @Override
    public List<Drzava> getListaSvihDrzava() {
        return session.createCriteria(Drzava.class).list();
    }

    @Override
    public Drzava getDrzavaById(Long id) {
        return (Drzava) session.createCriteria(Drzava.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniDrzavu(Drzava drzava) {
        session.merge(drzava);
    }

    @Override
    public void obrisiDrzavu(Long id) {
        session.delete(getDrzavaById(id));
    }
}
