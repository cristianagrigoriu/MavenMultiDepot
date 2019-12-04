/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author crist
 */
public class App {
    private final String url = "jdbc:postgresql://5432/vehicles";
    private final String user = "postgres";
    private final String password = "Java1sland";
 
    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app = new App();
        app.connect();
    }
}
