package Elderly.People.Project.controller;

import Elderly.People.Project.model.ElderlyPeople;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;


public class ElderlyValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return ElderlyPeople.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ElderlyPeople elderlyPeople = (ElderlyPeople) obj;

        if (elderlyPeople.getDNI().trim().equals(null) || elderlyPeople.getDNI().trim().equals("") || elderlyPeople.getDNI().trim().equals(" "))
            errors.rejectValue("dni", "nonullobj","No se ha introducido el DNI");
        if (elderlyPeople.getDNI().length() > 10)
            errors.rejectValue("dni", "tooLongString","La longitud del text introduït supera el límit");

        if (elderlyPeople.getName().trim().equals(null) || elderlyPeople.getName().trim().equals("") || elderlyPeople.getName().trim().equals(" "))
            errors.rejectValue("name", "nonullobj","No se ha introducido nombre");
        if (elderlyPeople.getName().length() > 50)
            errors.rejectValue("name", "nonullobj","No mbre demasiado largo");


        if (elderlyPeople.getSurname().trim().equals(null) || elderlyPeople.getSurname().trim().equals("") || elderlyPeople.getSurname().trim().equals(" "))
            errors.rejectValue("surname", "nonullobj","No se ha introducido apellido");
        if (elderlyPeople.getSurname().length() > 20)
            errors.rejectValue("surname", "nonullobj","Apellido demasiado largo");


        if (elderlyPeople.getAddress().trim().equals(null) || elderlyPeople.getAddress().trim().equals("") || elderlyPeople.getAddress().trim().equals(" "))
            errors.rejectValue("address", "nonullobj","No se ha introducido dirección");
        if (elderlyPeople.getAddress().length() > 50)
            errors.rejectValue("address", "nonullobj","Dirección muy larga");


        if (elderlyPeople.getPhoneNumber().trim().equals(null) || elderlyPeople.getPhoneNumber().trim().equals("") || elderlyPeople.getPhoneNumber().trim().equals(" "))
        errors.rejectValue("phonenumber", "nonullobj","No se ha introducido numero de teléfono");
        if (elderlyPeople.getPhoneNumber().length() > 15)
            errors.rejectValue("phonenumber", "nonullobj","Teléfono muy largo");

        if (elderlyPeople.getBankAccountNummber().trim().equals(null) || elderlyPeople.getBankAccountNummber().trim().equals("") || elderlyPeople.getBankAccountNummber().trim().equals(" "))
            errors.rejectValue("bankaccountnumber", "nonullobj","No se ha introducido numero de cuenta");
        if (elderlyPeople.getBankAccountNummber().length() > 20)
            errors.rejectValue("bankaccountnumber", "nonullobj","Cuenta bancaria muy larga");

        if (elderlyPeople.getEmail().trim().equals(null) || elderlyPeople.getEmail().trim().equals("") || elderlyPeople.getEmail().trim().equals(" "))
            errors.rejectValue("email", "nonullobj","No se ha introducido ningún correo");
        if (elderlyPeople.getEmail().length() > 20)
            errors.rejectValue("email", "nonullobj","Correo demasiado largo");


    }
}
