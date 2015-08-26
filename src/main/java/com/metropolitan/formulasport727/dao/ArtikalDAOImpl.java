/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Artikal;
import com.metropolitan.formulasport727.entities.TipArtikla;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa ArtikalDAO
 * @author Miroslav Stipanović 727
 */
public class ArtikalDAOImpl implements ArtikalDAO{
    
    @Inject
    private Session session;

    @Override
    public List<Artikal> getListaSvihArtikala() {
        return session.createCriteria(Artikal.class).list();
    }

    @Override
    public List<Artikal> getListaSvihArtikalaTipaArtikla(TipArtikla tipArtikla) {
        return session.createCriteria(Artikal.class).add(Restrictions.eq("tipId", tipArtikla)).list();
    }

    @Override
    public Artikal getArtikalById(Long id) {
        return (Artikal) session.createCriteria(Artikal.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniArtikal(Artikal artikal) {
        session.merge(artikal);
    }

    @Override
    public void obrisiArtikal(Long id) {
        session.delete(getArtikalById(id));
    }
    
}
