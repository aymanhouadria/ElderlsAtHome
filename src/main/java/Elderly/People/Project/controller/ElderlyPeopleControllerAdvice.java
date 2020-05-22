package Elderly.People.Project.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ElderlyPeopleControllerAdvice {
    @ExceptionHandler(value = ElderlyPeopleException.class)
    public ModelAndView handleEPException(ElderlyPeopleException ex){

        ModelAndView mav = new ModelAndView("error/exceptionError");
        mav.addObject("message", ex.getMessage());
        mav.addObject("errorName", ex.getErrorName());
        return mav;
    }
}
