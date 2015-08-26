/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.formulasport727.dao;

import com.metropolitan.formulasport727.entities.Diskusija;
import com.metropolitan.formulasport727.entities.DiskusionaGrupa;
import com.metropolitan.formulasport727.entities.Korisnik;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *Implementacija interfejsa DiskusijaDAO
 * @author Miroslav Stipanović 727
 */
public class DiskusijaDAOImpl implements DiskusijaDAO{
    
    @Inject
    private Session session;

    @Override
    public List<Diskusija> getListaSvihDiskusija() {
        return session.createCriteria(Diskusija.class).list();
    }
    
    @Override
    public List<Diskusija> getListaSvihOdobrenihDiskusija() {
        return session.createCriteria(Diskusija.class).add(Restrictions.eq("odobrena", true)).list();
    }
    
    @Override
    public List<Diskusija> getListaSvihNeodobrenihDiskusija() {
        return session.createCriteria(Diskusija.class).add(Restrictions.eq("odobrena", false)).list();
    }

    @Override
    public List<Diskusija> getListaSvihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa) {
        return session.createCriteria(Diskusija.class).add(Restrictions.eq("disId", diskusionaGrupa)).list();
    }
    
    @Override
    public List<Diskusija> getListaSvihOdobrenihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa) {
        return session.createCriteria(Diskusija.class).add(Restrictions.eq("disId", diskusionaGrupa))
                .add(Restrictions.eq("odobrena", true)).list();
    }
    
    @Override
    public List<Diskusija> getListaSvihNeodobrenihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa) {
        return session.createCriteria(Diskusija.class).add(Restrictions.eq("disId", diskusionaGrupa))
                .add(Restrictions.eq("odobrena", false)).list();
    }
    
    @Override
    public List<Diskusija> getListaSvihDiskusijaKorisnika(Korisnik korisnik) {
        return session.createCriteria(Diskusija.class).add(Restrictions.eq("korId", korisnik)).list();
    }
    
    @Override
    public List<Diskusija> getListaSvihOdobrenihDiskusijaKorisnika(Korisnik korisnik) {
        return session.createCriteria(Diskusija.class).add(Restrictions.eq("korId", korisnik))
                .add(Restrictions.eq("odobrena", true)).list();
    }

    @Override
    public List<Diskusija> getListaSvihDiskusijaKorisnikaUDiskusionojGrupi(DiskusionaGrupa diskusionaGrupa, Korisnik korisnik) {
        return session.createCriteria(Diskusija.class).add(Restrictions.eq("disId", diskusionaGrupa))
                .add(Restrictions.eq("korId", korisnik)).list();
    }
    
    @Override
    public List<Diskusija> getListaSvihOdobrenihDiskusijaKorisnikaUDiskusionojGrupi(DiskusionaGrupa diskusionaGrupa, Korisnik korisnik) {
        return session.createCriteria(Diskusija.class).add(Restrictions.eq("disId", diskusionaGrupa))
                .add(Restrictions.eq("korId", korisnik)).add(Restrictions.eq("odobrena", true)).list();
    }

    @Override
    public Diskusija getDiskusijaById(Long id) {
        return (Diskusija) session.createCriteria(Diskusija.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public List<Diskusija> getListaDiskusijaPoKljucnimRecima(String kljucneReci){
        String[] split = kljucneReci.split(" ");
        Set<Diskusija> diskusije = new TreeSet<>();
        for(String s : split){
            System.out.println("PRETRAŽUJEM SVE ODOBRENE DISKUSIJE SA KLJUCNOM RECI: "+s);
            diskusije.addAll(session.createCriteria(Diskusija.class)
                    .add(Restrictions.or(Restrictions.ilike("naslovDiskusije", s + " %"),
                            Restrictions.ilike("naslovDiskusije", "% "+s),
                            Restrictions.ilike("naslovDiskusije", "% "+s+" %"),
                            Restrictions.ilike("naslovDiskusije", s),
                            Restrictions.ilike("opisDiskusije", s),
                            Restrictions.ilike("opisDiskusije", "% "+s+" %"),
                            Restrictions.ilike("opisDiskusije", s + "%"),
                            Restrictions.ilike("opisDiskusije", "%"+s)))
                    .add(Restrictions.eq("odobrena", true)).list());
        }
        List<Diskusija> lista = new ArrayList<>();
        lista.addAll(diskusije);
        return lista;
    }

    @Override
    public void dodajIliIzmeniDiskusiju(Diskusija diskusija) {
        session.merge(diskusija);
    }

    @Override
    public void obrisiDiskusiju(Long id) {
        session.delete(getDiskusijaById(id));
    }
    
    @Override
    public int getBrojDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa){
        return ((Long)session.createCriteria(Diskusija.class).add(Restrictions.eq("disId",
                diskusionaGrupa)).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
    @Override
    public int getBrojOdobrenihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa){
        return ((Long)session.createCriteria(Diskusija.class).add(Restrictions.eq("disId",
                diskusionaGrupa)).add(Restrictions.eq("odobrena", true))
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
    
    @Override
    public int getBrojNeodobrenihDiskusijaDiskusioneGrupe(DiskusionaGrupa diskusionaGrupa){
        return ((Long)session.createCriteria(Diskusija.class).add(Restrictions.eq("disId",
                diskusionaGrupa)).add(Restrictions.eq("odobrena", false))
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

}
