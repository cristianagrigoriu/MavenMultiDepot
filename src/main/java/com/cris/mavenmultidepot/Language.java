package com.cris.mavenmultidepot;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author cristiana
 */
@SessionScoped
@ManagedBean
@Named("language")
public class Language implements Serializable {
    
    @PostConstruct
    public void init() {
        FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    private static final long serialVersionUID = 1L;

    public static String currentLanguage;
    public static String currentLanguageCode;
    public static Locale locale;

    private static final Map<String,Object> languages;

    static{
            languages = new LinkedHashMap<>();
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

    public Locale getLocale() {
        return locale;
    }

    public void currentLanguageChanged(ValueChangeEvent e){

        String newCurrentLanguageCode = e.getNewValue().toString();
        for (Map.Entry<String, Object> language : languages.entrySet()) {
            if(language.getValue().toString().equals(newCurrentLanguageCode)){
                currentLanguage = language.getKey();
                locale = (Locale)language.getValue();
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale(locale);
            }
        }
    }
    
    public void setCurrentLocale() {
        locale = locale != null
                ? locale
                : FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
