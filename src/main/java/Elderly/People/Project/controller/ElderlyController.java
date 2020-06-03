package Elderly.People.Project.controller;

import Elderly.People.Project.dao.ElderlyPeopleDao;
import Elderly.People.Project.model.ElderlyPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.DuplicateFormatFlagsException;


@Controller
@RequestMapping("/elderly")
public class ElderlyController {

    private ElderlyPeopleDao elderlyDao;

    @Autowired
    public void setElderlyPeopleDao(ElderlyPeopleDao elderlyDao) {
        this.elderlyDao = elderlyDao;
    }

    @RequestMapping("/list")
    public String listElderlies(Model model) {
        model.addAttribute("elderlies", elderlyDao.getElderlyPeople());
        return "elderly/list";
    }

    @RequestMapping(value="/add")
    public String addElderly(Model model) {
        model.addAttribute("elderly", new ElderlyPeople());
        return "elderly/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("elderly") ElderlyPeople elderlyPeople, BindingResult bindingResult) {
        ElderlyValidator elderlyValidator = new ElderlyValidator();
        elderlyValidator.validate(elderlyPeople, bindingResult);
        if (bindingResult.hasErrors())
            return "elderly/add";

        try {
            elderlyDao.addElderlyPeople(elderlyPeople);
        } catch (DuplicateKeyException ex) {
            throw new ElderlyPeopleException("Clave duplicada, dni:" + elderlyPeople.getDNI() , "CPDuplicada");
        }

        return "redirect:list";
    }

    @RequestMapping(value="/update/{dni}", method = RequestMethod.GET)
    public String editElderly(Model model, @PathVariable String dni) {
        model.addAttribute("elderly", elderlyDao.getElderlyPeople(dni));

        return "elderly/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("elderly") ElderlyPeople elderlyPeople, BindingResult bindingResult) {
        ElderlyValidator elderlyValidator = new ElderlyValidator();
        elderlyValidator.validate(elderlyPeople, bindingResult);
        if (bindingResult.hasErrors())
            return "elderly/add";

        try {
            elderlyDao.updateElderlyPeople(elderlyPeople);
        } catch (DuplicateFormatFlagsException ex) {
            throw new ElderlyPeopleException("Clave duplicada, DNI:" + elderlyPeople.getDNI() , "CPDuplicada");
        } catch (DataAccessException ex) {
            throw new ElderlyPeopleException("Error en l'accés a la base de dades", "ErrorAccedintDades");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{dni}")
    public String processDelete(@PathVariable String dni) {
        elderlyDao.deleteElderlyPeople(dni);
        return "redirect:../list";
    }
}
