/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Artikal;
import com.metropolitan.formulasport727.entities.Korpa;
import com.metropolitan.formulasport727.entities.KorpaArtikal;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *Implementacija interfejsa KorpaArtikalDAO
 * @author Miroslav Stipanović 727
 */
public class KorpaArtikalDAOImpl implements KorpaArtikalDAO{
    
    @Inject
    private Session session;

    @Override
    public List<KorpaArtikal> getListaSvihKorpaArtikala() {
        return session.createCriteria(KorpaArtikal.class).list();
    }

    @Override
    public List<KorpaArtikal> getListaSvihKorpaArtikalaKorpe(Korpa korpa) {
        return session.createCriteria(KorpaArtikal.class).add(Restrictions.eq("korId", korpa)).list();
    }
    
    @Override
    public List<KorpaArtikal> getListaSvihJedinstvenihKorpaArtikalaKorpe(Korpa korpa) {
        return session.createCriteria(KorpaArtikal.class).add(Restrictions.eq("korId", korpa))
                .setProjection(Projections.projectionList()
                            .add(Projections.property("id").as("id"))
                            .add(Projections.property("korId").as("korId"))
                            .add(Projections.groupProperty("artId").as("artId"))         
                    ).setResultTransformer(Transformers.aliasToBean(KorpaArtikal.class)).list();
    }
    
    @Override
    public List<KorpaArtikal> getListaSvihKorpaArtikalaKorpeSaArtiklom(Korpa korpa, Artikal artikal) {
        return session.createCriteria(KorpaArtikal.class).add(Restrictions.eq("korId", korpa))
                .add(Restrictions.eq("artId", artikal)).list();
    }

    @Override
    public KorpaArtikal getKorpaArtikalById(Long id) {
        return (KorpaArtikal) session.createCriteria(KorpaArtikal.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniKorpuArtikal(KorpaArtikal korpaArtikal) {
        session.merge(korpaArtikal);
    }

    @Override
    public void obrisiKorpuArtikal(Long id) {
        session.delete(getKorpaArtikalById(id));
    }

    @Override
    public int brojPojavljivanjaArtiklaUKorpi(Korpa korpa, Artikal artikal) {
        return ((Long)session.createCriteria(KorpaArtikal.class)
                .add(Restrictions.eq("korId",korpa))
                .add(Restrictions.eq("artId", artikal))
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
}
