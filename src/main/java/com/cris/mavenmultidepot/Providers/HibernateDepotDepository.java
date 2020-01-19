package com.cris.mavenmultidepot.Providers;

import DatabaseObjects.Depot;
import static com.cris.mavenmultidepot.Providers.DepotService.newDepots;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author cristiana
 */
public class HibernateDepotDepository implements DepotRepository {

    @Override
    public List<Depot> getAllDepots() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Query query = em.createNativeQuery("SELECT * FROM new_depots;");
        List<Object[]> newDepotObjects = query.getResultList();
        
        for (Object[] newDepotObject : newDepotObjects) {
            Depot newDepot = new Depot((int) newDepotObject[0], newDepotObject[1].toString(), (int) newDepotObject[2]);
            newDepots.add(newDepot);
        }
        
        em.getTransaction().commit();
        em.close();
        
        return newDepots;
    }

    @Override
    public Depot getDepotById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Depot foundDepot = em.find(Depot.class, id);
        
        em.getTransaction().commit();
        em.close();
        
        return foundDepot;
    }
    
    @Override
    public Depot getDepotByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Depot saveDepot(Depot b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDepot(Depot b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
