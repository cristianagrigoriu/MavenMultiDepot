package com.cris.mavenmultidepot.Views;

import com.cris.mavenmultidepot.Models.TripModel;
import com.cris.mavenmultidepot.Providers.ScheduleGenerator;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author cristiana
 */
@Named("scheduleView")
@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable{
    private List<TripModel> trips;
    
    @PostConstruct
    public void init() {
        trips = new ScheduleGenerator().getMostEfficientTripSchedule();
    }
    
    public List<TripModel> getTrips() {
        return trips;
    }
}
