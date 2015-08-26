/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.Sezona;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa SezonaDAO
 * @author Miroslav Stipanović 727
 */
public class SezonaDAOImpl implements SezonaDAO{

    @Inject
    private Session session;
    
    @Override
    public List<Sezona> getListaSvihSezona() {
        return session.createCriteria(Sezona.class).list();
    }
    
    @Override
    public List<Sezona> getListaSvihSezonaKategorije(Kategorija kategorija) {
        return session.createCriteria(Sezona.class).add(Restrictions.eq("katId", kategorija)).list();
    }

    @Override
    public Sezona getSezonaById(Long id) {
        return (Sezona) session.createCriteria(Sezona.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniSezonu(Sezona sezona) {
        session.merge(sezona);
    }

    @Override
    public void obrisiSezonu(Long id) {
        session.delete(getSezonaById(id));
    }
    
}
