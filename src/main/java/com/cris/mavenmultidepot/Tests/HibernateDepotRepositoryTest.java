package com.cris.mavenmultidepot.Tests;

import com.cris.mavenmultidepot.DatabaseObjects.Depot;
import com.cris.mavenmultidepot.Providers.DepotRepository;
import com.cris.mavenmultidepot.Providers.HibernateDepotDepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.extensions.TestSetup;
import static junit.framework.Assert.assertEquals;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 *
 * @author cristiana
 */
public class HibernateDepotRepositoryTest {
    
    DepotRepository depotRepository = new HibernateDepotDepository();
    
    private static EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public static Test suite(  ) {
        TestSetup setup = new TestSetup(new TestSuite(HibernateDepotDepository.class)) {
            protected void setUp(  ) throws Exception {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        HibernateDepotRepositoryTest.em = emf.createEntityManager();
            }
            protected void tearDown(  ) throws Exception {
                // do your one-time tear down here!
            }
        };
        return setup;
    }  
    
    @Test
    public void testRetrievesDepot_WhenOneDepotAdded() {
        Depot tobeSaved = new Depot(17, "Black Depot", 17);
        depotRepository.saveDepot(tobeSaved);
        assertEquals(tobeSaved, depotRepository.getDepotById(17));
    }
    
    @Test
    public void testRetrievesTwoDepots_WhenTwoDepotsAreAdded() {
        Depot tobeSaved1 = new Depot(17, "Black Depot", 17);
        Depot tobeSaved2 = new Depot(18, "White Depot", 18);
        depotRepository.saveDepot(tobeSaved1);
        depotRepository.saveDepot(tobeSaved2);
        assertEquals(tobeSaved1, depotRepository.getDepotById(17));
        assertEquals(tobeSaved2, depotRepository.getDepotById(12));
    }
    
    @Test
    public void testRetrievesAllDepotsWithGivenName() {
        Depot tobeSaved1 = new Depot(17, "Black Depot", 17);
        Depot tobeSaved2 = new Depot(18, "Black Depot", 18);
        depotRepository.saveDepot(tobeSaved1);
        depotRepository.saveDepot(tobeSaved2);
        assertEquals(2, depotRepository.getDepotByName("BlackDepot").size());
    }
    
    @Test
    public void testRetrievesAllDepotsWithGivenCapacity() {
        Depot tobeSaved1 = new Depot(17, "Black Depot", 17);
        Depot tobeSaved2 = new Depot(18, "White Depot", 17);
        depotRepository.saveDepot(tobeSaved1);
        depotRepository.saveDepot(tobeSaved2);
        assertEquals(2, depotRepository.getDepotByCapacity(17).size());
    }
    
    @Test
    public void testRetrievesAllDepots_WhenMoreAreAdded() {
        Depot tobeSaved1 = new Depot(17, "Light Gray Depot", 17);
        Depot tobeSaved2 = new Depot(18, "Gray Depot", 18);
        Depot tobeSaved3 = new Depot(19, "Dark Gray Depot", 19);
        depotRepository.saveDepot(tobeSaved1);
        depotRepository.saveDepot(tobeSaved2);
        depotRepository.saveDepot(tobeSaved3);
        assertEquals(3, depotRepository.getAllDepots().size());
    }
    
    @Test
    public void testDoesNotRetrievesDepot_WhenOneDepotIsDeleted() {
        Depot tobeSaved = new Depot(17, "Black Depot", 17);
        depotRepository.saveDepot(tobeSaved);
        depotRepository.deleteDepotById(17);
        assertEquals(null, depotRepository.getDepotById(17));
    }
}
