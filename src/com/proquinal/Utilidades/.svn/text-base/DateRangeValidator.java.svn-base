package com.proquinal.Utilidades;

import java.util.Date;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DateRangeValidator implements Validator {

	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		try{
			String valor = value.toString().trim();
		Date myDate = (Date) value;
		System.out.print("myDate: " + myDate);
		Date today = new Date();
		if (myDate.equals(false)) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Date is in the past");
            message.setSummary("Date is in the past");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
		}
		}catch(Exception ex){
			System.out.print("error");
		}
		
		/*System.out.print("valor: " + valor);
		if(valor.equals(null)){
			FacesMessage message = new FacesMessage();
            message.setDetail("Seleccione la fecha inicial por favor.");
            message.setSummary("Seleccione la fecha inicial por favor.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
		}*/
	}

}
