/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.TipArtikla;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa TipArtiklaDAO
 * @author Miroslav Stipanović 727
 */
public class TipArtiklaDAOImpl implements TipArtiklaDAO {
    
    @Inject
    private Session session;

    @Override
    public List<TipArtikla> getListaSvihTipovaArtikla() {
        return session.createCriteria(TipArtikla.class).list();
    }

    @Override
    public TipArtikla getTipArtiklaById(Long id) {
        return (TipArtikla) session.createCriteria(TipArtikla.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniTipArtikla(TipArtikla tipArtikla) {
        session.merge(tipArtikla);
    }

    @Override
    public void obrisiTipArtikla(Long id) {
        session.delete(getTipArtiklaById(id));
    }
}
