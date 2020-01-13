package com.cris.mavenmultidepot.Views;

import com.cris.mavenmultidepot.Providers.DepotService;
import com.cris.mavenmultidepot.Models.DepotModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author cristiana
 */
@Named("depotView")
@ManagedBean
@SessionScoped
public class DepotView implements Serializable {
    private List<DepotModel> depots;
    
    private String newDepotName;
    private String newDepotCapacity;

    public String getNewDepotName() {
        return newDepotName;
    }

    public void setNewDepotName(String newDepotName) {
        this.newDepotName = newDepotName;
    }

    public String getNewDepotCapacity() {
        return newDepotCapacity;
    }

    public void setNewDepotCapacity(String newDepotCapacity) {
        this.newDepotCapacity = newDepotCapacity;
    }
    
    @PostConstruct
    public void init() {
        depots = new DepotService().getDepots();
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
    
    public String createNewDepot() {
        DepotModel newDepot = new DepotModel(this.newDepotName, Integer.parseInt(newDepotCapacity));
        depots.add(newDepot);
        return null;
    }
}
