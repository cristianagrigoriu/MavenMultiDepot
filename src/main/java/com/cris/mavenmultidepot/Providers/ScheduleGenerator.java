/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Providers;

import com.cris.mavenmultidepot.Models.DepotModel;
import com.cris.mavenmultidepot.Models.TripModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author cristiana
 */
public class ScheduleGenerator {
//    private List<TripModel> trips;
//    private List<DepotModel> depots;
    
    public static Map<UUID, Integer> depotCapacities = new HashMap<UUID, Integer>();
    
    public List<TripModel> getMostEfficientTripSchedule() {
        List<TripModel> orderedTrips = new ArrayList<>();
        
        new DepotService().getDepots().stream().forEach((depot) -> {
            depotCapacities.put(depot.getId(), depot.getCapacity());
        });
        
        orderedTrips = TripService.getTrips(); 
        
        orderedTrips.sort((t1, t2) -> {
            return t1.getStartingTime().compareTo(t2.getStartingTime());
        });
        
       for (TripModel trip : orderedTrips) {
           if (sourceAndDestinationDepotsHaveCapacity(trip.getSourceDepot(), trip.getDestinationDepot())) {
               makeTripFromSourceToDestination(trip.getSourceDepot(), trip.getDestinationDepot());
           }
       }
        
        return orderedTrips;
    }
    
    private Boolean sourceAndDestinationDepotsHaveCapacity(DepotModel source, DepotModel destination) {
        return depotCapacities.get(source.getId()) >= 1 &&
                depotCapacities.get(destination.getId()) < destination.getCapacity();
    }
    
    private void makeTripFromSourceToDestination(DepotModel source, DepotModel destination) {
        depotCapacities.put(source.getId(), depotCapacities.get(source.getId()) - 1);
        depotCapacities.put(destination.getId(), depotCapacities.get(destination.getId()) + 1);
    }
}
