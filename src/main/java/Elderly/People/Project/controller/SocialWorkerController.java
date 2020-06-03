package Elderly.People.Project.controller;

import Elderly.People.Project.dao.SocialWorkerDao;
import Elderly.People.Project.model.SocialWorker;
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
@RequestMapping("/socialworker")
public class SocialWorkerController {

    private SocialWorkerDao socialworkerDao;

    @Autowired
    public void setSocialWorkerDao(SocialWorkerDao socialworkerDao) {
        this.socialworkerDao = socialworkerDao;
    }

    @RequestMapping("/list")
    public String listSocialWorkers(Model model) {
        model.addAttribute("socialworkers", socialworkerDao.getSocialWorkers());
        return "socialworker/list";
    }

    @RequestMapping(value="/add")
    public String addSocialWorker(Model model) {
        model.addAttribute("socialworker", new SocialWorker());
        return "socialworker/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("socialworker") SocialWorker socialworker, BindingResult bindingResult) {
        SocialWorkerValidator socialworkerValidator = new SocialWorkerValidator();
        socialworkerValidator.validate(socialworker, bindingResult);
        if (bindingResult.hasErrors())
            return "socialworker/add";

        try {
            socialworkerDao.addSocialWorker(socialworker);
        } catch (DuplicateKeyException ex) {
            throw new ElderlyPeopleException("Clave duplicada, usercas:" + socialworker.getUsercas() , "CPDuplicada");
        }

        return "redirect:list";
    }

    @RequestMapping(value="/update/{usercas}", method = RequestMethod.GET)
    public String editSocialWorker(Model model, @PathVariable String usercas) {
        model.addAttribute("socialworker", socialworkerDao.getSocialWorker(usercas));

        return "socialworker/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("socialworker") SocialWorker socialWorker, BindingResult bindingResult) {
        SocialWorkerValidator socialworkerValidator = new SocialWorkerValidator();
        socialworkerValidator.validate(socialWorker, bindingResult);
        if (bindingResult.hasErrors())
            return "socialworker/add";

        try {
            socialworkerDao.updateSocialWorker(socialWorker);
        } catch (DuplicateFormatFlagsException ex) {
            throw new ElderlyPeopleException("Clave duplicada, usercas:" + socialWorker.getUsercas() , "CPDuplicada");
        } catch (DataAccessException ex) {
            throw new ElderlyPeopleException("Error en l'accés a la base de dades", "ErrorAccedintDades");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{usercas}")
    public String processDelete(@PathVariable String usercas) {
        socialworkerDao.deleteSocialWorker(usercas);
        return "redirect:../list";
    }
}
