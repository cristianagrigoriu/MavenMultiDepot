package com.cris.mavenmultidepot.Providers;

import com.cris.mavenmultidepot.DatabaseObjects.Depot;
import java.util.List;

/**
 *
 * @author cristiana
 */
public interface DepotRepository {
    List<Depot> getAllDepots();
    
    Depot getDepotById(int id);
 
    List<Depot> getDepotByName(String name);
    
    List<Depot> getDepotByCapacity(int capacity);
 
    Depot saveDepot(Depot b);
     
    void deleteDepotById(int id);
}