package com.cris.mavenmultidepot.Views;

import com.cris.mavenmultidepot.Models.TripModel;
import com.cris.mavenmultidepot.Providers.TripService;
import java.io.Serializable;
import java.util.List;
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

@Named("tripView")
@ManagedBean
@ViewScoped
public class TripView implements Serializable {
    private List<TripModel> trips;
    
    @PostConstruct
    public void init() {
        trips = new TripService().getTrips();
    }
    
    public List<TripModel> getTrips() {
        return trips;
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Object Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void remove(TripModel trip) {
        trips.remove(trip);
    }
    
    public void saveTrips() {
        addMessage(TripService.saveTripsToDatabase());
    }
 
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
