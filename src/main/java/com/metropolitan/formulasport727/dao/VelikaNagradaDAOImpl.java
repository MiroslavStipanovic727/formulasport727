/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.VelikaNagrada;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa VelikaNagradaDAO
 * @author Miroslav Stipanović 727
 */
public class VelikaNagradaDAOImpl implements VelikaNagradaDAO{
    
    @Inject
    private Session session;

    @Override
    public List<VelikaNagrada> getListaSvihVelikihNagrada() {
        return session.createCriteria(VelikaNagrada.class).list();
    }

    @Override
    public List<VelikaNagrada> getListaSvihVelikihNagradaSezone(Sezona sezona) {
        return session.createCriteria(VelikaNagrada.class).add(Restrictions.eq("sezId", sezona))
                .addOrder(Order.asc("datumPocetka")).list();
    }

    @Override
    public VelikaNagrada getVelikaNagradaById(Long id) {
        return (VelikaNagrada) session.createCriteria(VelikaNagrada.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniVelikuNagradu(VelikaNagrada velikaNagrada) {
        session.merge(velikaNagrada);
    }

    @Override
    public void obrisiVelikuNagradu(Long id) {
        session.delete(getVelikaNagradaById(id));
    }
    
    
    
}
