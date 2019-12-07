/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Models;

import javax.enterprise.inject.Model;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author crist
 */
@Model
@ViewScoped
public class DepotModel {
    private int id;
    private String name;
    private int capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    //public 
    
    /**
     * Creates a new instance of DepotBean
     */
    public DepotModel(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }
    public DepotModel() {
    }
    
    
  
    
}