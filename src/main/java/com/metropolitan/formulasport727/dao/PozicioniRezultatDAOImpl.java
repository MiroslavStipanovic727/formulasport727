/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kvalifikacije;
import com.metropolitan.formulasport727.entities.PozicioniRezultat;
import com.metropolitan.formulasport727.entities.Trening;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa PozicioniRezultatDAO
 * @author Miroslav Stipanović 727
 */
public class PozicioniRezultatDAOImpl implements PozicioniRezultatDAO{
    
    @Inject
    private Session session;

    @Override
    public List<PozicioniRezultat> getListaSvihPozicionihRezultata() {
        return session.createCriteria(PozicioniRezultat.class).list();
    }

    @Override
    public List<PozicioniRezultat> getListaSvihPozicionihRezultataTreninga(Trening trening) {
        return session.createCriteria(PozicioniRezultat.class).add(Restrictions.eq("treId", trening)).list();
    }

    @Override
    public List<PozicioniRezultat> getListaSvihPozicionihRezultataKvalifikacija(Kvalifikacije kvalifikacije) {
        return session.createCriteria(PozicioniRezultat.class).add(Restrictions.eq("kvaId", kvalifikacije)).list();
    }

    @Override
    public PozicioniRezultat getPozicioniRezultatById(Long id) {
        return (PozicioniRezultat) session.createCriteria(PozicioniRezultat.class)
                .add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniPozicioniRezultat(PozicioniRezultat pozicioniRezultat) {
        session.merge(pozicioniRezultat);
    }

    @Override
    public void obrisiPozicioniRezultat(Long id) {
        session.delete(getPozicioniRezultatById(id));
    }
    
}
