/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot;

import java.sql.Connection;
import java.sql.DriverManager;
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

    private final String url = "jdbc:postgresql://localhost:5432/vehicles";
    private final String user = "postgres";
    private final String password = "Java1sland";
    
    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
        Connection conn = null;
        try {
            //getConnection("jdbc:postgresql://localhost:5432/example", "postgres", "postgres")
            //Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
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

