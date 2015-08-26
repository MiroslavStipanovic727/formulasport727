/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Diskusija;
import com.metropolitan.formulasport727.entities.KomentarDiskusije;
import com.metropolitan.formulasport727.entities.Korisnik;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa KomentarDiskusijeDAO
 * @author Miroslav Stipanović 727
 */
public class KomentarDiskusijeDAOImpl implements KomentarDiskusijeDAO{
    
    @Inject
    private Session session;

    @Override
    public List<KomentarDiskusije> getListaSvihKomentaraDiskusije() {
        return session.createCriteria(KomentarDiskusije.class).list();
    }

    @Override
    public List<KomentarDiskusije> getListaSvihKomentaraDiskusijeUDiskusiji(Diskusija diskusija) {
        return session.createCriteria(KomentarDiskusije.class).add(Restrictions.eq("disId", diskusija)).list();
    }

    @Override
    public List<KomentarDiskusije> getListaSvihKomentaraDiskusijeKorisnika(Korisnik korisnik) {
        return session.createCriteria(KomentarDiskusije.class).add(Restrictions.eq("korId", korisnik)).list();
    }

    @Override
    public List<KomentarDiskusije> getListaSvihKomentaraDiskusijeKorisnikaUDiskusiji(Diskusija diskusija, Korisnik korisnik) {
        return session.createCriteria(KomentarDiskusije.class).add(Restrictions.eq("disId", diskusija))
                .add(Restrictions.eq("korId", korisnik)).list();
    }

    @Override
    public KomentarDiskusije getKomentarDiskusijeById(Long id) {
        return (KomentarDiskusije) session.createCriteria(KomentarDiskusije.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniKomentarDiskusije(KomentarDiskusije komentarDiskusije) {
        session.merge(komentarDiskusije);
    }

    @Override
    public void obrisiKomentarDiskusije(Long id) {
        session.delete(getKomentarDiskusijeById(id));
    }
}
