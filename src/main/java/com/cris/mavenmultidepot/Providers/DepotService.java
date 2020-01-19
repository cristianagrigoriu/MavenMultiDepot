/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Providers;

import DatabaseObjects.Depot;
import com.cris.mavenmultidepot.Models.DepotModel;
import com.cris.mavenmultidepot.Models.TripModel;
import static com.cris.mavenmultidepot.Providers.TripService.trips;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static java.util.UUID.fromString;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author cristiana
 */
@Named
@ApplicationScoped
@ManagedBean
public class DepotService {
    public static List<DepotModel> depots = new ArrayList<DepotModel>();
    public static List<Depot> newDepots = new ArrayList<Depot>();
    
    public static List<DepotModel> getDepots() {
        if (depots.isEmpty()) {
            depots = getDepotsFromDatabase();
        }
        return depots;
    }
    
    public List<DepotModel> createDepots() {
        depots.add(new DepotModel(UUID.randomUUID(), "White", 500));
        depots.add(new DepotModel(UUID.randomUUID(), "Black", 100));
        
        return depots;
    }
    
    private static List<DepotModel> getDepotsFromDatabase() {
        Connection conn = null;
        try {
            
            ResultSet rs = null;
            PreparedStatement pst = null;
            String stm = "select * from depots";
            
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/vehicles?user=postgres&password=Java1sland";
            conn = DriverManager.getConnection(url);
            
            pst = conn.prepareStatement(stm);
            pst.execute();
            rs = pst.getResultSet();
            
            while(rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                int capacity = rs.getInt(3);
                depots.add(new DepotModel(fromString(id), name, capacity));
            }
            
            return depots;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public static String saveDepotsToDatabase() {
        Connection conn = null;
        try {
            
            ResultSet rs = null;
            PreparedStatement pst = null;
            
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/vehicles?user=postgres&password=Java1sland";
            conn = DriverManager.getConnection(url);
            
            String stm = "delete from depots";
            pst = conn.prepareStatement(stm);
            pst.execute();
            
            for (DepotModel depot : DepotService.getDepots()) {
                stm = "INSERT INTO depots(id, name, capacity) "
                    + "VALUES(?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(stm, Statement.RETURN_GENERATED_KEYS);
                pstmt.setObject(1, depot.getId());
                pstmt.setString(2, depot.getName());
                pstmt.setInt(3, depot.getCapacity());

                int affectedRows = pstmt.executeUpdate();
            }
            
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "All records saved";
    }
    
    public static DepotModel findDepotById(List<DepotModel> depots, UUID depotId) {
        return depots.stream().filter(d -> depotId.equals(d.getId())).findFirst().orElse(null);
    }

    public List<Depot> getNewDepots() {
        if (newDepots.isEmpty()) {
            newDepots = getNewDepotsFromDatabase();
        }
        return newDepots;
    }

    private List<Depot> getNewDepotsFromDatabase() {
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
    
}
