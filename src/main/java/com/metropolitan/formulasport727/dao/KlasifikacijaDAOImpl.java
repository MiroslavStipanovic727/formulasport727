/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Klasifikacija;
import com.metropolitan.formulasport727.entities.Sezona;
import com.metropolitan.formulasport727.entities.Tim;
import com.metropolitan.formulasport727.entities.Vozac;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa KlasifikacijaDAO
 * @author Miroslav Stipanović 727
 */
public class KlasifikacijaDAOImpl implements KlasifikacijaDAO{
    
    @Inject
    private Session session;

    @Override
    public List<Klasifikacija> getListaSvihKlasifikacija() {
        return session.createCriteria(Klasifikacija.class)
                .addOrder(Order.asc("id")).list();
    }

    @Override
    public List<Klasifikacija> getListaSvihKlasifikacijaVozaca() {
        return session.createCriteria(Klasifikacija.class).add(Restrictions.isNotNull("vozId")).list();
    }

    @Override
    public List<Klasifikacija> getListaSvihKlasifikacijaTimova() {
        return session.createCriteria(Klasifikacija.class).add(Restrictions.isNotNull("timId")).list();
    }
    
    @Override
    public List<Klasifikacija> getListaSvihKlasifikacijaSezone(Sezona sezona) {
        return session.createCriteria(Klasifikacija.class).add(Restrictions.isNull("vozId"))
                .add(Restrictions.isNull("timId")).add(Restrictions.eq("sezId", sezona))
                .addOrder(Order.asc("id")).list();
    }

    @Override
    public List<Klasifikacija> getListaSvihKlasifikacijaVozacaSezone(Sezona sezona) {
        return session.createCriteria(Klasifikacija.class).add(Restrictions.isNotNull("vozId"))
                .add(Restrictions.eq("sezId", sezona)).addOrder(Order.asc("pozicijaK")).list();
    }

    @Override
    public List<Klasifikacija> getListaSvihKlasifikacijaTimovaSezone(Sezona sezona) {
        return session.createCriteria(Klasifikacija.class).add(Restrictions.isNotNull("timId"))
                .add(Restrictions.eq("sezId", sezona)).addOrder(Order.asc("pozicijaK")).list();
    }

    @Override
    public Klasifikacija getKlasifikacijaById(Long id) {
        return (Klasifikacija) session.createCriteria(Klasifikacija.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public Klasifikacija getKlasifikacijaByVozac(Vozac vozac){
        return (Klasifikacija) session.createCriteria(Klasifikacija.class).add(Restrictions.eq("vozId", vozac)).uniqueResult();
    }
    
    @Override
    public Klasifikacija getKlasifikacijaByTim(Tim tim){
        return (Klasifikacija) session.createCriteria(Klasifikacija.class).add(Restrictions.eq("timId", tim)).uniqueResult();
    }
    
    @Override
    public Klasifikacija getLastKlasifikacija() {
        List<Klasifikacija> lista = getListaSvihKlasifikacija();
        for(Klasifikacija k:lista){
            System.out.println("Klasifikacija ID: "+k.getId());
        }
        System.out.println("Ukupno klasifikacija = "+lista.size());
        return lista.get(lista.size()-1);
    }

    @Override
    public void dodajIliIzmeniKlasifikaciju(Klasifikacija klasifikacija) {
        session.merge(klasifikacija);
    }
    
    @Override
    public void izmeniKlasifikaciju(Klasifikacija klasifikacija){
        session.update(klasifikacija);
    }

    @Override
    public void obrisiKlasifikaciju(Long id) {
        session.delete(getKlasifikacijaById(id));
    }
    
}
