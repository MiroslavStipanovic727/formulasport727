/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.DiskusionaGrupa;
import com.metropolitan.formulasport727.entities.Kategorija;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa DiskusionaGrupaDAO
 * @author Miroslav Stipanović 727
 */
public class DiskusionaGrupaDAOImpl implements DiskusionaGrupaDAO{
    
    @Inject
    private Session session;

    @Override
    public List<DiskusionaGrupa> getListaSvihDiskusionihGrupa() {
        return session.createCriteria(DiskusionaGrupa.class).list();
    }

    @Override
    public List<DiskusionaGrupa> getListaSvihDiskusionihGrupaKategorije(Kategorija kategorija) {
        return session.createCriteria(DiskusionaGrupa.class).add(Restrictions.eq("katId", kategorija))
                .list();
    }

    @Override
    public DiskusionaGrupa getDiskusionaGrupaById(Long id) {
        return (DiskusionaGrupa) session.createCriteria(DiskusionaGrupa.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajIliIzmeniDiskusionuGrupu(DiskusionaGrupa diskusionaGrupa) {
        session.merge(diskusionaGrupa);
    }

    @Override
    public void obrisiDiskusionuGrupu(Long id) {
        session.delete(getDiskusionaGrupaById(id));
    }
}
