package com.cris.mavenmultidepot.Providers;

import com.cris.mavenmultidepot.DatabaseObjects.Depot;
import java.util.ArrayList;
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

    private EntityManager em;
    
    public HibernateDepotDepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.em = emf.createEntityManager();
    }
    
    @Override
    public List<Depot> getAllDepots() {
        em.getTransaction().begin();
        
        Query query = em.createNativeQuery("SELECT * FROM new_depots;");
        List<Object[]> newDepotObjects = query.getResultList();
        
        List<Depot> allDepots = new ArrayList<Depot>();
        
        for (Object[] newDepotObject : newDepotObjects) {
            Depot newDepot = new Depot((int) newDepotObject[0], newDepotObject[1].toString(), (int) newDepotObject[2]);
            allDepots.add(newDepot);
        }
        
        em.getTransaction().commit();
        em.close();
        
        return allDepots;
    }

    @Override
    public Depot getDepotById(int id) {
        em.getTransaction().begin();
        
        Depot foundDepot = em.find(Depot.class, id);
        
        em.getTransaction().commit();
        em.close();
        
        return foundDepot;
    }
    
    @Override
    public List<Depot> getDepotByName(String name) {
        em.getTransaction().begin();
        
        Query q = em.createNativeQuery("SELECT * FROM new_depots WHERE name = ?");
        q.setParameter(1, name);
        List<Object[]> depotsByName =  q.getResultList();

        List<Depot> foundDepots = new ArrayList<>();
        
        for (Object[] newDepotObject : depotsByName) {
            Depot newDepot = new Depot((int) newDepotObject[0], newDepotObject[1].toString(), (int) newDepotObject[2]);
            foundDepots.add(newDepot);
        }
        
        em.getTransaction().commit();
        em.close();
        
        return foundDepots;
    }
    
    @Override
    public List<Depot> getDepotByCapacity(int capacity) {
        em.getTransaction().begin();
        
        Query q = em.createNativeQuery("SELECT * FROM new_depots WHERE capacity = ?");
        q.setParameter(1, capacity);
        List<Object[]> depotsByCapacity =  q.getResultList();

        List<Depot> foundDepots = new ArrayList<>();
        
        for (Object[] newDepotObject : depotsByCapacity) {
            Depot newDepot = new Depot((int) newDepotObject[0], newDepotObject[1].toString(), (int) newDepotObject[2]);
            foundDepots.add(newDepot);
        }
        
        em.getTransaction().commit();
        em.close();
        
        return foundDepots;
    }

    @Override
    public Depot saveDepot(Depot toBeSaved) {
        em.getTransaction().begin();
        
        em.persist(toBeSaved);
        
        em.getTransaction().commit();
        em.close();
        
        return toBeSaved;
    }

    @Override
    public void deleteDepotById(int id) {
        em.getTransaction().begin();
        
        Depot toBeDeleted = em.find(Depot.class, id);
        em.remove(toBeDeleted);
        
        em.getTransaction().commit();
        em.close();
    }
}
