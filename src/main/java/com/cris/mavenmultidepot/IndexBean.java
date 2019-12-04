/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author cristiana
 */
@ManagedBean(name="indexBean")            
@RequestScoped
public class IndexBean {
    
    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
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
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int c = rs.getInt(3);
            }
            
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String goToDepots()
    {
        return "depots";
    }
    
    public String goToTrips()
    {
        return "trips";
    }
    
    public String goToSchedule()
    {
        return "schedule";
    }
}

