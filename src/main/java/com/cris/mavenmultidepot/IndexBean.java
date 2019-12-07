/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot;

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

