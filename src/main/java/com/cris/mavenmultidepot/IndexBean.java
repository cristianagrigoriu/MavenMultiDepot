package com.cris.mavenmultidepot;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author cristiana
 */
@ManagedBean(name="indexBean")            
@RequestScoped
public class IndexBean {
    
    @PostConstruct
    public void init() {
    }
    
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

