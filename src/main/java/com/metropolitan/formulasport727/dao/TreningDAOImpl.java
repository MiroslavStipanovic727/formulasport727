/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Trening;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa TreningDAO
 * @author Miroslav Stipanović 727
 */
public class TreningDAOImpl implements TreningDAO{
    
    @Inject
    private Session session;

    @Override
    public List<Trening> getListaSvihTreninga() {
        return session.createCriteria(Trening.class).list();
    }

    @Override
    public List<Trening> getListaSvihTreningaVelikeNagrade(VelikaNagrada velikaNagrada) {
        return session.createCriteria(Trening.class).add(Restrictions.eq("velId", velikaNagrada))
                .addOrder(Order.asc("vremeTreninga")).list();
    }
    
    @Override
    public List<Trening> getListaSvihTreningaSezone(Sezona sezona) {
        return session.createCriteria(Trening.class).createAlias("velId", "v")
                .add(Restrictions.eq("v.sezId", sezona)).list();
    }

    @Override
    public Trening getTreningById(Long id) {
        return (Trening) session.createCriteria(Trening.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniTrening(Trening trening) {
        session.merge(trening);
    }

    @Override
    public void obrisiTrening(Long id) {
        session.delete(getTreningById(id));
    }
    
    
    
}
