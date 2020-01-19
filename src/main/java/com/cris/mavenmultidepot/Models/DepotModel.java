package com.cris.mavenmultidepot.Models;

import java.util.UUID;
import javax.enterprise.inject.Model;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cristiana
 */
@Model
@ViewScoped
public class DepotModel {
    private int intId;
    private UUID id;
    private String name;
    private int capacity;

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

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
