package com.cris.mavenmultidepot;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    static{
            depotFilterOptions = new LinkedHashMap<>();
            depotFilterOptions.put("Name", "Name");
            depotFilterOptions.put("Capacity", "Capacity");
    }

    public Map<String, Object> getDepotFilterOptions() {
        return depotFilterOptions;
    } 
}
