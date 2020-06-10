package Elderly.People.Project.controller;

import Elderly.People.Project.dao.ContractDao;
import Elderly.People.Project.model.Company;
import Elderly.People.Project.model.Contract;
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
@RequestMapping("/contract")
public class ContractController {

    private ContractDao contractDao;


    @Autowired
    public void setContractDao(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    @RequestMapping("/list")
    public String listCompanies(Model model) {
        model.addAttribute("contracts", contractDao.getContracts());
        return "contract/list";
    }

    @RequestMapping(value="/add")
    public String addContract(Model model) {
        model.addAttribute("contract", contractDao.getContractData());
        return "contract/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("contract") Contract contract, BindingResult bindingResult) {
        ContractValidator contractValidator = new ContractValidator();
        contractValidator.validate(contract, bindingResult);
        if (bindingResult.hasErrors())
            return "contract/add";

        try {
            contractDao.addContract(/*company, */contract);
        } catch (DuplicateKeyException ex) {
            throw new ElderlyPeopleException("Clave duplicada, con numero de contrato:" + contract.getNumber(), "CPDuplicada");
        }

        catch (Exception ex) {
            throw new ElderlyPeopleException("El CIF  "+ contract.getCif()+ "  NO PERTENECE A NINNGUNA COMPAÑÍA, PRIMERO SE DEBE REGISTRAR ", "Compañía no encontrada");
        }

        return "redirect:list";
    }

    @RequestMapping(value="/update/{number}", method = RequestMethod.GET)
    public String editContract(Model model, @PathVariable String number) {
        model.addAttribute("contract", contractDao.getContract(number));
        return "contract/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("contract") Contract contract, BindingResult bindingResult) {
        ContractValidator contractValidator = new ContractValidator();
        contractValidator.validate(contract, bindingResult);
        if (bindingResult.hasErrors())
            return "contract/update";

        try {
            contractDao.updateContract(contract);
        } catch (DuplicateFormatFlagsException ex) {
            throw new ElderlyPeopleException("Clave duplicada, con numero de contrato:" + contract.getNumber(), "CPDuplicada");
        } catch (DataAccessException ex) {
            throw new ElderlyPeopleException("Error en l'accés a la base de dades", "ErrorAccedintDades");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{number}")
    public String processDelete(@PathVariable String number) {
        contractDao.deleteContract(number);
        return "redirect:../list";
    }
}
