package Elderly.People.Project.controller;

import Elderly.People.Project.model.Contract;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;

public class ContractValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Contract.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Contract contract = (Contract) obj;

        if (contract.getNumber().trim().equals(null) || contract.getNumber().trim().equals("") || contract.getNumber().trim().equals(" "))
            errors.rejectValue("number", "nonullobj","El nombre del contracte no ha d'estar buit");
        if (contract.getNumber().length() > 20)
            errors.rejectValue("number", "tooLongString","La longitud del text introduït supera el límit");
        if (contract.getDateEnding() != null && contract.getDateBeginning() != null) {
            if (contract.getDateBeginning().after(contract.getDateEnding()))
                errors.rejectValue("dateBeginning", "dateError", "La data inicial no pot ser posterior a la final");
            if (contract.getDateBeginning().after(contract.getDateEnding()))
                errors.rejectValue("dateEnding", "dateError", "La data final no pot ser anterior a la inicial");

            /*

            COMPROBAR SI EL FORMATO DE LA FECHA ES CORRECTO Y SI LAS FECHAS SON CORRECTAS

            SimpleDateFormat example = new SimpleDateFormat("MM/dd/yyyy");

            if (!contract.getDateEnding().equals(example)) {
                errors.rejectValue("dateEnding", "dateFormatError", "El format de la data no es correcte");
                System.out.println("777777777777777777777777777777777777777777777777");
            }
            if (!contract.getDateBeginning().equals(example)) {
                errors.rejectValue("dateBeginning", "dateFormatError", "El format de la data no es correcte");
                System.out.println("888888888888888888888888888888888888888888888");
            }
             */
        }


        if (contract.getDescription().length() > 50)
            errors.rejectValue("description", "tooLongString","La longitud del text introduït supera el límit");
        if (contract.getQuantityServices().length() > 20)
            errors.rejectValue("quantityServices", "tooLongString","La longitud del text introduït supera el límit");
        if (contract.getUnitsOfMeasure().length() > 20)
            errors.rejectValue("unitsOfMeasure", "tooLongString","La longitud del text introduït supera el límit");
        if (contract.getPriceUnit().length() > 10)
            errors.rejectValue("priceUnit", "tooLongString","La longitud del text introduït supera el límit");
    }
}
