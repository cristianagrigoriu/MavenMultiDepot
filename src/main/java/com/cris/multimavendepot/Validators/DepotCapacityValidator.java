/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.multimavendepot.Validators;

import com.cris.mavenmultidepot.Models.DepotModel;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author cristiana
 */
@FacesValidator("depotCapacityValidator")
public class DepotCapacityValidator implements javax.faces.validator.Validator 
{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException {
        if ((int)value < 0) {
            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Capacity should be positive.", "Error!");
            FacesContext.getCurrentInstance().addMessage("growl", errorMessage);
            throw new ValidatorException(errorMessage);
        }
    }
}
