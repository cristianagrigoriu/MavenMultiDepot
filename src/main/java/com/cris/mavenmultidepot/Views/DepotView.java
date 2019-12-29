/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Views;

import com.cris.mavenmultidepot.Providers.DepotService;
import com.cris.mavenmultidepot.Models.DepotModel;
import com.cris.mavenmultidepot.Providers.TripService;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author cristiana
 */
@Named("depotView")
@ManagedBean
@ViewScoped
public class DepotView implements Serializable {
    private List<DepotModel> depots;
    
    @PostConstruct
    public void init() {
        depots = new DepotService().getDepots();
        Language.setCurrentLanguageCode(Language.currentLanguageCode);
    }
    
    public List<DepotModel> getDepots() {
        return depots;
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Object Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void remove(DepotModel depot) {
        depots.remove(depot);
    }
    
    public void saveDepots() {
        addMessage(DepotService.saveDepotsToDatabase());
    }
 
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
