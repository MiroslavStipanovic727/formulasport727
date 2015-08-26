/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Vozac;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Implementacija interfejsa VozacDAO 
 * @author Miroslav Stipanović 727
 */
public class VozacDAOImpl implements VozacDAO {
    
    @Inject
    private Session session;

    @Override
    public List<Vozac> getListaSvihVozaca() {
        return session.createCriteria(Vozac.class)
                .addOrder(Order.asc("id")).list();
    }
    
    @Override
    public List<Vozac> getListaSvihVozacaSezone(Sezona sezona) {
        return session.createCriteria(Vozac.class).add(Restrictions.eq("sezId", sezona)).list();
    }

    @Override
    public Vozac getVozacById(Long id) {
        return (Vozac) session.createCriteria(Vozac.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public Vozac getLastVozac() {
        return getListaSvihVozaca().get(getListaSvihVozaca().size()-1);
    }

    @Override
    public void dodajIliIzmeniVozaca(Vozac vozac) {
        session.merge(vozac);
    }

    @Override
    public void obrisiVozaca(Long id) {
        session.delete(getVozacById(id));
    }
    
}
