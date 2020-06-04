package Elderly.People.Project.controller;

import Elderly.People.Project.dao.RequestDao;
import Elderly.People.Project.model.Request;
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
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/request")
public class RequestController {

    private RequestDao requestDao;

    @Autowired
    public void setRequestDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @RequestMapping("/list")
    public String listRequests(Model model) {
        model.addAttribute("requests", requestDao.getRequests());
        return "request/list";
    }

    @RequestMapping("/listCas")
    public String listRequestsCas(Model model) {
        model.addAttribute("requests", requestDao.getRequestsCas());
        return "request/listCas";
    }

    @RequestMapping(value="/add")
    public String addContract(Model model) {
        model.addAttribute("request", requestDao.getRequest());
        return "request/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("request") /*Company company, */Request request, BindingResult bindingResult) {
        RequestValidator requestValidator = new RequestValidator();
        requestValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors())
            return "request/add";

        try {
            requestDao.addRequest(request);
        } catch (DuplicateKeyException ex) {
            throw new ElderlyPeopleException("Clave duplicada, con numero de petición:" + request.getNumber(), "CPDuplicada");
        }

        return "redirect:list";
    }

    @RequestMapping(value="/update/{number}", method = RequestMethod.GET)
    public String editRequest(Model model, @PathVariable String number) {
        model.addAttribute("request", requestDao.getRequest(number));
        return "request/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("request") Request request, BindingResult bindingResult) {
        RequestValidator requestValidator = new RequestValidator();
        requestValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors())
            return "request/update";

        try {
            requestDao.updateRequest(request);
        } catch (DuplicateFormatFlagsException ex) {
            throw new ElderlyPeopleException("Clave duplicada, con numero de solicitud:" + request.getNumber(), "CPDuplicada");
        } catch (DataAccessException ex) {
            throw new ElderlyPeopleException("Error en l'accés a la base de dades", "ErrorAccedintDades");
        }
        return "redirect:../contract/add";
    }

    @RequestMapping(value="/delete/{number}")
    public String processDelete(@PathVariable String number) {
        requestDao.deleteRequest(number);
        return "redirect:../list";
    }
}
