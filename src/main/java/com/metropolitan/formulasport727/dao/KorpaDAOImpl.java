/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Korisnik;
import com.metropolitan.formulasport727.entities.Korpa;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa KorpaDAO
 * @author Miroslav Stipanović 727
 */
public class KorpaDAOImpl implements KorpaDAO{
    
    @Inject
    private Session session;

    @Override
    public List<Korpa> getListaSvihKorpi() {
        return session.createCriteria(Korpa.class).list();
    }

    @Override
    public List<Korpa> getListaSvihKorpiKorisnika(Korisnik korisnik) {
        return session.createCriteria(Korpa.class).add(Restrictions.eq("korId", korisnik)).list();
    }

    @Override
    public Korpa getKorpaById(Long id) {
        return (Korpa) session.createCriteria(Korpa.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public Korpa getKorpaKorisnika(Korisnik korisnik){
        return (Korpa) session.createCriteria(Korpa.class).add(Restrictions.eq("korId", korisnik)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniKorpu(Korpa korpa) {
        session.merge(korpa);
    }

    @Override
    public void obrisiKorpu(Long id) {
        session.delete(getKorpaById(id));
    }
    
}
