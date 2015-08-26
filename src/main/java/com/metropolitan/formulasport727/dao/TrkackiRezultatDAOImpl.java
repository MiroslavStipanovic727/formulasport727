/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Trka;
import com.metropolitan.formulasport727.entities.TrkackiRezultat;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa TrkackiRezultatDAO
 * @author Miroslav Stipanović 727
 */
public class TrkackiRezultatDAOImpl implements TrkackiRezultatDAO{
    
    @Inject
    private Session session;

    @Override
    public List<TrkackiRezultat> getListaSvihTrkackihRezultata() {
        return session.createCriteria(TrkackiRezultat.class).list();
    }

    @Override
    public List<TrkackiRezultat> getListaSvihTrkackihRezultataTrke(Trka trka) {
        return session.createCriteria(TrkackiRezultat.class).add(Restrictions.eq("trkId", trka)).list();
    }

    @Override
    public TrkackiRezultat getTrkackiRezultatById(Long id) {
        return (TrkackiRezultat) session.createCriteria(TrkackiRezultat.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniTrkackiRezultat(TrkackiRezultat trkackiRezultat) {
        session.merge(trkackiRezultat);
    }

    @Override
    public void obrisiTrkackiRezultat(Long id) {
        session.delete(getTrkackiRezultatById(id));
    }
}
