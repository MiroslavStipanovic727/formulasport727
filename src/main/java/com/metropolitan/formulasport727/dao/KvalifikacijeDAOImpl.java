/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kvalifikacije;
import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa KvalifikacijeDAO
 * @author Miroslav Stipanović 727
 */
public class KvalifikacijeDAOImpl implements KvalifikacijeDAO{
    
    @Inject
    private Session session;

    @Override
    public List<Kvalifikacije> getListaSvihKvalifikacija() {
        return session.createCriteria(Kvalifikacije.class).list();
    }

    @Override
    public List<Kvalifikacije> getListaSvihKvalifikacijaVelikeNagrade(VelikaNagrada velikaNagrada) {
        return session.createCriteria(Kvalifikacije.class).add(Restrictions.eq("velId", velikaNagrada))
                .addOrder(Order.asc("vremeKvalifikacija")).list();
    }
    
    @Override
    public List<Kvalifikacije> getListaSvihKvalifikacijaSezone(Sezona sezona) {
        return session.createCriteria(Kvalifikacije.class).createAlias("velId", "v")
                .add(Restrictions.eq("v.sezId", sezona)).list();
    }

    @Override
    public Kvalifikacije getKvalifikacijeById(Long id) {
        return (Kvalifikacije) session.createCriteria(Kvalifikacije.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniKvalifikacije(Kvalifikacije kvalifikacije) {
        session.merge(kvalifikacije);
    }

    @Override
    public void obrisiKvalifikacije(Long id) {
        session.delete(getKvalifikacijeById(id));
    }
    
}
