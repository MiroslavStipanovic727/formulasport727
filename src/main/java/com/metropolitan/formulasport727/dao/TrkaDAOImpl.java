/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Trka;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa TrkaDAO
 * @author Miroslav Stipanović 727
 */
public class TrkaDAOImpl implements TrkaDAO{
    
    @Inject
    private Session session;

    @Override
    public List<Trka> getListaSvihTrka() {
        return session.createCriteria(Trka.class).list();
    }

    @Override
    public List<Trka> getListaSvihTrkaVelikeNagrade(VelikaNagrada velikaNagrada) {
        return session.createCriteria(Trka.class).add(Restrictions.eq("velId", velikaNagrada))
                .addOrder(Order.asc("vremeTrke")).list();
    }
    
    @Override
    public List<Trka> getListaSvihTrkaSezone(Sezona sezona) {
        return session.createCriteria(Trka.class).createAlias("velId", "v")
                .add(Restrictions.eq("v.sezId", sezona)).list();
    }

    @Override
    public Trka getTrkaById(Long id) {
        return (Trka) session.createCriteria(Trka.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniTrku(Trka trka) {
        session.merge(trka);
    }

    @Override
    public void obrisiTrku(Long id) {
        session.delete(getTrkaById(id));
    }
}
