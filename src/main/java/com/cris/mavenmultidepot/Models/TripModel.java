/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;
import javax.enterprise.inject.Model;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author crist
 */

@Model
@ViewScoped
public class TripModel {

    
    private UUID id;
    private Timestamp startingTime;
    private int duration;
    
    private DepotModel sourceDepot;
    private DepotModel destinationDepot;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Timestamp startingTime) {
        this.startingTime = startingTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public DepotModel getSourceDepot() {
        return sourceDepot;
    }

    public void setSourceDepot(DepotModel sourceDepot) {
        this.sourceDepot = sourceDepot;
    }

    public DepotModel getDestinationDepot() {
        return destinationDepot;
    }

    public void setDestinationDepot(DepotModel destinationDepot) {
        this.destinationDepot = destinationDepot;
    }
    
    //TODO implement logic for computing trip schedule with coordinates
    private static boolean canExecuteTripsOneAfterTheOther(TripModel firstTrip, TripModel secondTrip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static double getCostBetweenTrips(TripModel firstTrip, TripModel secondTrip) {
        if (canExecuteTripsOneAfterTheOther(firstTrip, secondTrip)) {
            return LocationCoordinates.getEuclideanDistance(
                    firstTrip.destinationDepot.getCoordinates(), 
                    secondTrip.sourceDepot.getCoordinates());
        }
        return -1;
    }
    
    /**
     * Creates a new instance of TripModel
     */
    public TripModel(UUID id, Timestamp startingTime, int duration,
            DepotModel sourceDepot, DepotModel destinationDepot) {
        this.id = id;
        this.startingTime = startingTime;
        this.duration = duration;
        this.sourceDepot = sourceDepot;
        this.destinationDepot = destinationDepot;
    }
    public TripModel() {
    }
}
