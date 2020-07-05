package Elderly.People.Project.controller;

import Elderly.People.Project.dao.VolunteerDao;
import Elderly.People.Project.model.Volunteer;
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
@RequestMapping("/volunteer")
public class VolunteerController {

    private VolunteerDao volunteerDao;

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }

    @RequestMapping("/list")
    public String listVolunteer(Model model) {
        model.addAttribute("volunteer", volunteerDao.getVolunteers());
        return "volunteer/list";
    }

    @RequestMapping("/ListaVoluntarios")
    public String listVolunteerElderly(Model model) {
        model.addAttribute("volunteer", volunteerDao.getVolunteersElderly());
        return "volunteer/ListaVolunarios";

    }


    @RequestMapping(value="/add")
    public String addVolunteer(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        return "volunteer/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("volunteer") Volunteer volunteer, BindingResult bindingResult) {
        VolunteerValidator volunteerValidator = new VolunteerValidator();
        volunteerValidator.validate(volunteer, bindingResult);
        if (bindingResult.hasErrors())
            return "volunteer/add";

        try {
            volunteerDao.addVolunteer(volunteer);
        } catch (DuplicateKeyException ex) {
            throw new ElderlyPeopleException("Clave duplicada con numero de usuario:" + volunteer.getUser() , "CPDuplicada");
        }

        return "redirect:../availability/add";
    }

    @RequestMapping(value="/update/{user}", method = RequestMethod.GET)
    public String editVolunteer(Model model, @PathVariable String user) {
        model.addAttribute("volunteer", volunteerDao.getVolunteer(user));
        return "volunteer/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("volunteer") Volunteer volunteer, BindingResult bindingResult) {
        VolunteerValidator volunteerValidator = new VolunteerValidator();
        volunteerValidator.validate(volunteer, bindingResult);
        if (bindingResult.hasErrors())
            return "volunteer/update";

        try {
            volunteerDao.updateVolunteer(volunteer);
        } catch (DuplicateFormatFlagsException ex) {
            throw new ElderlyPeopleException("Clave duplicada, numero de usuario:" + volunteer.getUser() , "CPDuplicada");
        } catch (DataAccessException ex) {
            throw new ElderlyPeopleException("Error en l'accés a la base de dades", "ErrorAccedintDades");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{userv}")
    public String processDelete(@PathVariable String userv) {
        volunteerDao.deleteVolunteer(userv);
        return "redirect:../list";
    }



    @RequestMapping(value="/resolve/{userv}")
    public String processResolve(@PathVariable String userv) {
        volunteerDao.resolveVolunteer(userv);
        return "redirect:../list";
    }
}
