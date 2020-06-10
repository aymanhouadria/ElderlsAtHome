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




    }
}