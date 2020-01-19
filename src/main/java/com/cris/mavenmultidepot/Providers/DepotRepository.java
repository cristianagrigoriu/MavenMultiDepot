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
 
    Depot getDepotByTitle(String title);
 
    Depot saveDepot(Depot b);
     
    void deleteDepot(Depot b);
}
