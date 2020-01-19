package com.cris.mavenmultidepot.Providers;

import DatabaseObjects.Depot;
import java.util.List;

/**
 *
 * @author cristiana
 */
public interface DepotRepository {
    List<Depot> getAllDepots();
    
    Depot getDepotById(int id);
 
    List<Depot> getDepotByName(String name);
 
    Depot saveDepot(Depot b);
     
    void deleteDepotById(int id);
}
