package Elderly.People.Project.controller;

import Elderly.People.Project.model.SocialWorker;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;


public class SocialWorkerValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return SocialWorker.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        SocialWorker socialworker = (SocialWorker) obj;

        if (socialworker.getUsercas().trim().equals(null) || socialworker.getUsercas().trim().equals("") || socialworker.getUsercas().trim().equals(" "))
            errors.rejectValue("usercas", "nonullobj","El numero del SocialCas está vacío");
        if (socialworker.getUsercas().length() > 10)
            errors.rejectValue("usercas", "nonullobj","Demasiado largo");

        if (socialworker.getName().trim().equals(null) || socialworker.getName().trim().equals("") || socialworker.getName().trim().equals(" "))
            errors.rejectValue("name", "nonullobj","El nombre está vacío");
        if (socialworker.getName().length() >50)
            errors.rejectValue("name", "nonullobj","El nombre és demasiado largo");

        if (socialworker.getPhonenumber().trim().equals(null) || socialworker.getPhonenumber().trim().equals("") || socialworker.getPhonenumber().trim().equals(" "))
            errors.rejectValue("phonenumber", "nonullobj","El numero de telefono esta vacío");
        if (socialworker.getPhonenumber().length() > 20)
            errors.rejectValue("phonenumber", "nonullobj","El numero de telefono sobrepasa el límite");

        if (socialworker.getEmail().trim().equals(null) || socialworker.getEmail().trim().equals("") || socialworker.getEmail().trim().equals(" "))
            errors.rejectValue("email", "nonullobj","El email vacío");
        if (socialworker.getEmail().length() >15)
            errors.rejectValue("email", "nonullobj","El email es demasiado largo");





    }
}