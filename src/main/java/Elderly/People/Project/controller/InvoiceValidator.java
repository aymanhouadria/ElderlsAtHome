package Elderly.People.Project.controller;

import Elderly.People.Project.model.Invoice;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;


public class InvoiceValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Invoice.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Invoice invoice = (Invoice) obj;

        if (invoice.getNumber().trim().equals(null) || invoice.getNumber().trim().equals("") || invoice.getNumber().trim().equals(" "))
            errors.rejectValue("number", "nonullobj","El numero de factura está vacío");
        if (invoice.getNumber().length() > 20)
            errors.rejectValue("number", "tooLongString","La longitud del text introduït supera el límit");
        if (invoice.getNumberr().trim().equals(null) || invoice.getNumberr().trim().equals("") || invoice.getNumberr().trim().equals(" "))
            errors.rejectValue("numberr", "nonullobj","El numero de solicitud está vacío");
        if (invoice.getNumberr().length() > 20)
            errors.rejectValue("numberr", "tooLongString","La longitud del text introduït supera el límit");
        if (invoice.getDNI().trim().equals(null) || invoice.getDNI().trim().equals("") || invoice.getDNI().trim().equals(" "))
            errors.rejectValue("dni", "nonullobj","El numero de DNI está vacío");
        if (invoice.getDNI().length() > 20)
            errors.rejectValue("dni", "tooLongString","La longitud del text introduït supera el límit");
            errors.rejectValue("unitsOfMeasure", "tooLongString","La longitud del text introduït supera el límit");
        if (invoice.getConcept().trim().equals(null) || invoice.getConcept().trim().equals("") || invoice.getConcept().trim().equals(" "))
            errors.rejectValue("concept", "nonullobj","Elconcepto está vacío");
        if (invoice.getConcept().length() > 20)
            errors.rejectValue("concept", "tooLongString","La longitud del text introduït supera el límit");
    }
}
