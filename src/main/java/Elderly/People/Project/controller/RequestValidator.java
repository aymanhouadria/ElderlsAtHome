package Elderly.People.Project.controller;

import Elderly.People.Project.model.Request;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;


public class RequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Request.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Request request = (Request) obj;

        if (request.getNumber().trim().equals(null) || request.getNumber().trim().equals("") || request.getNumber().trim().equals(" "))
            errors.rejectValue("number", "nonullobj","El numero de solicitud esta vacío");
        if (request.getNumber().length() > 20)
            errors.rejectValue("number", "tooLongString","Numero demasiado largo");
        if (request.getDNI().length() > 10)
            errors.rejectValue("number", "tooLongString","DNI demasiado largo");


        if (request.getServiceType() != null && request.getServiceType().length() > 20)
            errors.rejectValue("Tiposervicio", "tooLongString","La longitud excede el maximo");
        if (request.getComments() != null && request.getComments().length() > 100)
            errors.rejectValue("comments", "tooLongString","Descripcion demasiado larga");
    }
}