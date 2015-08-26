/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Tim;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa TimDAO
 * @author Miroslav Stipanović 727
 */
public class TimDAOImpl implements TimDAO{
    
    @Inject
    private Session session;

    @Override
    public List<Tim> getListaSvihTimova() {
        return session.createCriteria(Tim.class)
                .addOrder(Order.asc("id")).list();
    }
    
    @Override
    public List<Tim> getListaSvihTimovaSezone(Sezona sezona) {
        return session.createCriteria(Tim.class).add(Restrictions.eq("sezId", sezona)).list();
    }

    @Override
    public Tim getTimById(Long id) {
        return (Tim) session.createCriteria(Tim.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public Tim getLastTim() {
        return getListaSvihTimova().get(getListaSvihTimova().size()-1);
    }

    @Override
    public void dodajIliIzmeniTim(Tim tim) {
        session.merge(tim);
    }

    @Override
    public void obrisiTim(Long id) {
        session.delete(getTimById(id));
    }
    
}
