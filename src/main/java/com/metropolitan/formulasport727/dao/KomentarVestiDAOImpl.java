/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.KomentarVesti;
import com.metropolitan.formulasport727.entities.Vest;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa KomentarVestiDAO
 * @author Miroslav Stipanović 727
 */
public class KomentarVestiDAOImpl implements KomentarVestiDAO{
    
    @Inject
    private Session session;

    @Override
    public List<KomentarVesti> getListaSvihKomentaraVesti() {
        return session.createCriteria(KomentarVesti.class).list();
    }
    
    @Override
    public List<KomentarVesti> getListaSvihKomentaraKonkretneVesti(Vest vest) {
        return session.createCriteria(KomentarVesti.class).add(Restrictions.eq("vesId", vest))
                .addOrder(Order.asc("vreme")).list();
    }

    @Override
    public KomentarVesti getKomentarVestiById(Long id) {
        return (KomentarVesti) session.createCriteria(KomentarVesti.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniKomentarVesti(KomentarVesti komentarVesti) {
        session.merge(komentarVesti);
    }

    @Override
    public void obrisiKomentarVesti(Long id) {
        session.delete(getKomentarVestiById(id));
    }
    
}
