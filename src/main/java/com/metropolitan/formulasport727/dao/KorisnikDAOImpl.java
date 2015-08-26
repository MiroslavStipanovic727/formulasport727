/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Korisnik;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa KorisnikDAO
 * @author Miroslav Stipanović 727
 */
public class KorisnikDAOImpl implements KorisnikDAO {
    @Inject
    private Session session;

    @Override
    public List<Korisnik> getListaSvihKorisnika() {
        return session.createCriteria(Korisnik.class).list();
    }
    
    @Override
    public Korisnik getKorisnikById(Long id) {
        return (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public Korisnik getKorisnikByEmail(String email) {
        return (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("email",email)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniKorisnika(Korisnik korisnik) {
        session.merge(korisnik);
    }
    
    @Override
    public void dodajKorisnika(Korisnik korisnik) {
        session.save(korisnik);
    }

    @Override
    public void obrisiKorisnika(String email) {
        session.delete(getKorisnikByEmail(email));
    }

    @Override
    public Korisnik proveriKorisnika(String emailIliKorisnickoIme, String sifra) {
        try {
            Korisnik k = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("email",
            emailIliKorisnickoIme)).add(Restrictions.eq("sifra", sifra)).uniqueResult();
            if (k == null) {
                k = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("korisnickoIme",
                emailIliKorisnickoIme)).add(Restrictions.eq("sifra", sifra)).uniqueResult();
            }
            if (k != null) {
                return k;
            } 
            return null;
        } catch (NullPointerException e) {
            return null;
        }

    }

    @Override
    public Korisnik registrujKorisnika(Korisnik korisnik) {
        return (Korisnik) session.merge(korisnik);
    }

    @Override
    public boolean proveraDaLiPostojiEmail(String email) {
        return ((Long)session.createCriteria(Korisnik.class).add(Restrictions.eq("email",
                email)).setProjection(Projections.rowCount()).uniqueResult() != 0);
    }
    
    @Override
    public boolean proveraDaLiPostojiKorisnickoIme(String korisnickoIme) {
        return ((Long)session.createCriteria(Korisnik.class).add(Restrictions.eq("korisnickoIme",
                korisnickoIme)).setProjection(Projections.rowCount()).uniqueResult() != 0);
    }
    
}
