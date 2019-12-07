/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Providers;

import com.cris.mavenmultidepot.Models.DepotModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author crist
 */
@Named
@ApplicationScoped
@ManagedBean
public class DepotService {
    public List<DepotModel> createDepots() {
        List<DepotModel> depots = new ArrayList<DepotModel>(); 
        
        depots.add(new DepotModel(1, "White", 500));
        depots.add(new DepotModel(2, "Black", 100));
        
        return depots;
    }
    
    public List<DepotModel> getDepotsFromDatabase() {
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
            
            List<DepotModel> depots = new ArrayList<DepotModel>();
            
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int capacity = rs.getInt(3);
                depots.add(new DepotModel(id, name, capacity));
            }
            
            return depots;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
}
