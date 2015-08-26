/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Kategorija;
import com.metropolitan.formulasport727.entities.Vest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Implementacija interfejsa VestDAO
 * @author Miroslav Stipanović 727
 */
public class VestDAOImpl implements VestDAO{

    @Inject
    private Session session;
    
    @Override
    public List<Vest> getListaSvihVesti() {
        return session.createCriteria(Vest.class).list();
    }

    @Override
    public Vest getVestById(Long id) {
        return (Vest) session.createCriteria(Vest.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniVest(Vest vest) {
        session.merge(vest);
    }

    @Override
    public void obrisiVest(Long id) {
        session.delete(getVestById(id));
    }

    @Override
    public List<Vest> getListaPretrazenihVesti(Date pocetni, Date zavrsni, Kategorija kategorija) {
        Calendar c = Calendar.getInstance(); 
        if(zavrsni.before(Calendar.getInstance().getTime())){
            c.setTime(zavrsni); 
        } else {
            c.setTime(Calendar.getInstance().getTime());
        }
        c.add(Calendar.DATE, 1);
        zavrsni = c.getTime();
        return session.createCriteria(Vest.class)
                .add(Restrictions.between("vremeObjave", pocetni, zavrsni))
                .add(Restrictions.eq("katId", kategorija))
                .addOrder(Order.desc("vremeObjave")).setResultTransformer(
                Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Vest> getListaPoslednjihObjavljenihVesti() {
        Calendar c = Calendar.getInstance();
        Date trenutnoVreme = c.getTime();
        return session.createCriteria(Vest.class).add(Restrictions.lt("vremeObjave", trenutnoVreme))
                .addOrder(Order.desc("vremeObjave")).setResultTransformer(
                Criteria.DISTINCT_ROOT_ENTITY).list();
    }
    
}
