package com.cris.mavenmultidepot.Models;

import java.io.Serializable;
import java.util.UUID;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cristiana
 */
@ManagedBean
@Entity
@SessionScoped
@Table(name="depots")
public class DepotModel implements Serializable{
    @Id
    @Column(name="id")
    private UUID id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="capacity")
    private int capacity;
    private LocationCoordinates coordinates;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public LocationCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LocationCoordinates coordinates) {
        this.coordinates = coordinates;
    }
    
    /**
     * Creates a new instance of DepotModel
     */
    public DepotModel(UUID id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }
    
    public DepotModel() {
    }
    
    public DepotModel(String name, int capacity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.capacity = capacity;
    }
}
