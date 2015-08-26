/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kategorija;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa KategorijaDAO
 * @author Miroslav Stipanović 727
 */
public class KategorijaDAOImpl implements KategorijaDAO{

    @Inject
    private Session session;
    
    @Override
    public List<Kategorija> getListaSvihKategorija() {
        return session.createCriteria(Kategorija.class).list();
    }

    @Override
    public Kategorija getKategorijaById(Long id) {
        return (Kategorija) session.createCriteria(Kategorija.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniKategoriju(Kategorija kategorija) {
        session.merge(kategorija);
    }

    @Override
    public void obrisiKategoriju(Long id) {
        session.delete(getKategorijaById(id));
    }
    
}
