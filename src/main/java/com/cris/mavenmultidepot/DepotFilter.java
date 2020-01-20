package com.cris.mavenmultidepot;

import com.cris.mavenmultidepot.DatabaseObjects.Depot;
import static com.cris.mavenmultidepot.Language.currentLanguage;
import static com.cris.mavenmultidepot.Language.locale;
import com.cris.mavenmultidepot.Providers.DepotRepository;
import com.cris.mavenmultidepot.Providers.HibernateDepotDepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 *
 * @author cristiana
 */
@SessionScoped
@ManagedBean
@Named("depotFilter")
public class DepotFilter implements Serializable {
    private static final Map<String,Object> depotFilterOptions;
    private String criterion;
    private static DepotRepository depotRepository;
    private List<Depot> foundDepots;
    
    private String currentDepotFilterOption = "Name";

    public DepotFilter() {
        this.depotRepository = new HibernateDepotDepository();
        this.criterion = "Blue Depot";
        this.foundDepots = new ArrayList<>();
    }
    
    static{
            depotFilterOptions = new LinkedHashMap<>();
            depotFilterOptions.put("Name", "Name");
            depotFilterOptions.put("Capacity", "Capacity");
    }

    public Map<String, Object> getDepotFilterOptions() {
        return depotFilterOptions;
    } 

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }

    public List<Depot> getFoundDepots() {
        return foundDepots;
    }

    public void setFoundDepots(List<Depot> foundDepots) {
        this.foundDepots = foundDepots;
    }
    
    public void currentFilterOptionChanged(ValueChangeEvent e){

        this.currentDepotFilterOption = e.getNewValue().toString();
    }
    
    public void filterDepots() {
        if (this.currentDepotFilterOption == "Name") {
            this.foundDepots = depotRepository.getDepotByName(this.criterion);
        }
        
        if (this.currentDepotFilterOption == "Capacity") {
            this.foundDepots = depotRepository.getDepotByCapacity(Integer.parseInt(this.criterion));
        }
    }
}
