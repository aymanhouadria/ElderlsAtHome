package Elderly.People.Project.controller;

import Elderly.People.Project.dao.AvailabilityDao;
import Elderly.People.Project.model.availability;
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
@RequestMapping("/availability")
public class AvailabilityController {

    private AvailabilityDao availabilityDao;

    @Autowired
    public void setAvailabilityDao(AvailabilityDao availabilityDao) {
        this.availabilityDao = availabilityDao;
    }

    @RequestMapping("/list")
    public String listAvailability(Model model) {
        model.addAttribute("availabilities", availabilityDao.getAvailabilities());
        return "availability/list";
    }


    @RequestMapping("/listComplet")
    public String listComplet(Model model) {
        model.addAttribute("listcomplet", availabilityDao.getAvailabilities());
        return "availability/listComplet";
    }


    @RequestMapping(value="/add")
    public String addAvailability(Model model) {
        model.addAttribute("availability", availabilityDao.getAvailability());
        return "availability/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("availability") availability availability, BindingResult bindingResult) {

            availabilityDao.addAvailability(availability);


        return "redirect:../";
    }

    @RequestMapping(value="/update/{userv}", method = RequestMethod.GET)
    public String editAvailability(Model model, @PathVariable String userv) {
        model.addAttribute("availability", availabilityDao.getAvailability(userv));
        return "availability/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("availability")availability availability, BindingResult bindingResult) {


        availabilityDao.updateAvailability(availability);

        return "redirect:list";
    }

    @RequestMapping(value="/delete/{userv}")
    public String processDelete(@PathVariable String userv) {
        availabilityDao.deleteAvailability(userv);
        return "redirect:../list";
    }
}