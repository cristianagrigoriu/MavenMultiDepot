/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Views;

import com.cris.mavenmultidepot.Providers.DepotService;
import com.cris.mavenmultidepot.Models.DepotModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author crist
 */
@Named("depotView")
@ManagedBean
@ViewScoped
public class DepotView implements Serializable {
    private List<DepotModel> depots;
    
    @PostConstruct
    public void init() {
        depots = new DepotService().getDepotsFromDatabase();
    }
    
    public List<DepotModel> getDepots() {
        return depots;
    }
}
