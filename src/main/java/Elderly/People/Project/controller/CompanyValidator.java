package Elderly.People.Project.controller;

import Elderly.People.Project.model.Company;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CompanyValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Company.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Company company = (Company) obj;

        if (company.getCIF().trim().equals(null))
            errors.rejectValue("CIF", "nonullobj","El CIF no ha d'estar buit");
        if (company.getCIF().trim().equals(""))
            errors.rejectValue("CIF", "nonullobj","El CIF no ha d'estar buit");
        if (company.getCIF().trim().equals(" "))
            errors.rejectValue("CIF", "nonullobj","El CIF no ha d'estar buit");
        if (company.getCIF().length() > 20)
            errors.rejectValue("CIF", "tooLongString","La longitud del text introduït supera el límit");
        if (company.getName().length() > 50)
            errors.rejectValue("name", "tooLongString","La longitud del text introduït supera el límit");
        if (company.getAddress().length() > 20)
            errors.rejectValue("address", "tooLongString","La longitud del text introduït supera el límit");
        if (company.getCPName().length() > 20)
            errors.rejectValue("CPName", "tooLongString","La longitud del text introduït supera el límit");
        if (company.getCPPhoneNumber().length() > 25)
            errors.rejectValue("CPPhoneNumber", "tooLongString","La longitud del text introduït supera el límit");
        if (company.getCPPersonEmail().length() > 20)
            errors.rejectValue("CPPersonEmail", "tooLongString","La longitud del text introduït supera el límit");
    }
}
