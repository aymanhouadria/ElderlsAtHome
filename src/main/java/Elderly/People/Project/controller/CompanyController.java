package Elderly.People.Project.controller;

import Elderly.People.Project.dao.CompanyDao;
import Elderly.People.Project.model.Company;
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
@RequestMapping("/company")
public class CompanyController {

    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @RequestMapping("/list")
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyDao.getCompanies());
        return "company/list";
    }

    @RequestMapping(value="/add")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "company/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("company") Company company, BindingResult bindingResult) {
        CompanyValidator companyValidator = new CompanyValidator();
        companyValidator.validate(company, bindingResult);
        if (bindingResult.hasErrors())
            return "company/add";

        try {
            companyDao.addCompany(company);
        } catch (DuplicateKeyException ex) {
            throw new ElderlyPeopleException("Clave duplicada, con CIF:" + company.getName() + ", y nombre: " + company.getName(), "CPDuplicada");
        }

        return "redirect:list";
    }

    @RequestMapping(value="/update/{cif}", method = RequestMethod.GET)
    public String editCompany(Model model, @PathVariable String cif) {
        model.addAttribute("company", companyDao.getCompany(cif));
        return "company/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("company") Company company, BindingResult bindingResult) {
        CompanyValidator companyValidator = new CompanyValidator();
        companyValidator.validate(company, bindingResult);
        if (bindingResult.hasErrors())
            return "company/update";

        try {
            companyDao.updateCompany(company);
        } catch (DuplicateFormatFlagsException ex) {
            throw new ElderlyPeopleException("Clave duplicada, con CIF:" + company.getName() + ", y nombre: " + company.getName(), "CPDuplicada");
        } catch (DataAccessException ex) {
            throw new ElderlyPeopleException("Error en l'accés a la base de dades", "ErrorAccedintDades");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{cif}")
    public String processDelete(@PathVariable String cif) {
        companyDao.deleteCompany(cif);
        return "redirect:../list";
    }
}
