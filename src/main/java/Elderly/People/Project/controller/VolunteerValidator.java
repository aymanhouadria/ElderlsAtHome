package Elderly.People.Project.controller;

import Elderly.People.Project.model.Volunteer;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;


public class VolunteerValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Volunteer.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Volunteer volunteer = (Volunteer) obj;

        if (volunteer.getUser().trim().equals(null) || volunteer.getUser().trim().equals("") || volunteer.getUser().trim().equals(" "))
            errors.rejectValue("userv", "nonullobj","El numero de voluntario esta vacío");
        if (volunteer.getUser().length() > 20)
            errors.rejectValue("userv", "tooLongString","Numero demasiado largo");

        if (volunteer.getAddress().trim().equals(null) || volunteer.getAddress().trim().equals("") || volunteer.getAddress().trim().equals(" "))
            errors.rejectValue("address", "nonullobj","La dirección esta vacía");
        if (volunteer.getAddress().length() > 20)
            errors.rejectValue("address", "tooLongString","Dirección demasiado largo");


        if (volunteer.getName().trim().equals(null) || volunteer.getName().trim().equals("") || volunteer.getName().trim().equals(" "))
            errors.rejectValue("name", "nonullobj","El nombre está vacío");
        if (volunteer.getName().length() > 20)
            errors.rejectValue("name", "nonullobj","El nombre supera el límite");

        if (volunteer.getPhoneNumber().trim().equals(null) || volunteer.getPhoneNumber().trim().equals("") || volunteer.getPhoneNumber().trim().equals(" "))
            errors.rejectValue("phonenumber", "nonullobj","El nombre de teléfono está vacío");
        if (volunteer.getPhoneNumber().length() >20)
            errors.rejectValue("phonenumber", "nonullobj","El nombre de teléfono es muy largo");

        if (volunteer.getEmail().trim().equals(null) || volunteer.getEmail().trim().equals("") || volunteer.getEmail().trim().equals(" "))
            errors.rejectValue("email", "nonullobj","El email está vacío");
        if (volunteer.getEmail().length() > 20)
            errors.rejectValue("email", "nonullobj","El email es muy largo");

        if (volunteer.getHobbies().trim().equals(null) || volunteer.getHobbies().trim().equals("") || volunteer.getHobbies().trim().equals(" "))
            errors.rejectValue("hobbies", "nonullobj","El campo de hobbies está vacío");
        if (volunteer.getHobbies().length() > 20)
            errors.rejectValue("hobbies", "nonullobj","Hobbies superan limite");

        if (volunteer.getAcceptationDate() != null && volunteer.getApplicationDate() != null) {
            if (volunteer.getApplicationDate().after(volunteer.getAcceptationDate()))
                errors.rejectValue("applicationdate", "dateError", "La dfecha de aplicación no puede ser posterior a la fecha de creación");
        }


    }
}