/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Views;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author cristiana
 */
@Named(value = "language")
@ManagedBean
@Dependent
public class Language implements Serializable {
    
    @PostConstruct
    public void init() {
        this.currentLanguage = this.currentLanguage != null
                ? this.currentLanguage
                : FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().toString();
        
//        this.currentLanguageCode = this.currentLanguageCode != null
//                ? this.currentLanguageCode
//                : FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().toString();
    }

    private static final long serialVersionUID = 1L;

    public static String currentLanguage;
    public static String currentLanguageCode;

    private static final Map<String,Object> languages;

    static{
            languages = new LinkedHashMap<String,Object>();
            languages.put("English", Locale.ENGLISH);
            languages.put("Romanian", new Locale("ro"));
    }

    public Map<String, Object> getLanguages() {
            return languages;
    }


    public String getCurrentLanguage() {
            return currentLanguage;
    }


    public void setCurrentLanguage(String language) {
            this.currentLanguage = language;
    }

    public void currentLanguageChanged(ValueChangeEvent e){

        String newCurrentLanguageCode = e.getNewValue().toString();
        this.setCurrentLanguageCode(newCurrentLanguageCode);
    }
    
    public static void setCurrentLanguageCode(String newCurrentLanguageCode) {
        for (Map.Entry<String, Object> language : languages.entrySet()) {

           if(language.getValue().toString().equals(newCurrentLanguageCode)){
                currentLanguage = language.getKey();
                currentLanguageCode = language.getValue().toString();
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale)language.getValue());

          }
       }
    }
    
}
