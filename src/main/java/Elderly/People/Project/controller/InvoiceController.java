package Elderly.People.Project.controller;

import Elderly.People.Project.dao.InvoiceDao;
import Elderly.People.Project.model.Invoice;
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
@RequestMapping("/invoice")
public class InvoiceController {

    private InvoiceDao invoiceDao;

    @Autowired
    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @RequestMapping("/list")
    public String listInvoices(Model model) {
        model.addAttribute("invoices", invoiceDao.getInvoices());
        return "invoice/list";
    }

    @RequestMapping(value="/add")
    public String addInvoice(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoice/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("invoice") Invoice invoice, BindingResult bindingResult) {
        InvoiceValidator invoiceValidator = new InvoiceValidator();
        invoiceValidator.validate(invoice, bindingResult);
        if (bindingResult.hasErrors())
            return "invoice/add";

        try {
            invoiceDao.addInvoice(invoice);
        } catch (DuplicateKeyException ex) {
            throw new ElderlyPeopleException("Clave duplicada, con NUMERO DE  FACTURA :" + invoice.getNumber(), "CPDuplicada");
        }

        return "redirect:list";
    }

    @RequestMapping(value="/update/{number}", method = RequestMethod.GET)
    public String editInvoice(Model model, @PathVariable String number) {
        model.addAttribute("invoice", invoiceDao.getInvoice(number));
        return "invoice/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("invoice") Invoice invoice, BindingResult bindingResult) {
        InvoiceValidator invoiceValidator = new InvoiceValidator();
        invoiceValidator.validate(invoice, bindingResult);
        if (bindingResult.hasErrors())
            return "invoice/update";

        try {
            invoiceDao.updateInvoice(invoice);
        } catch (DuplicateFormatFlagsException ex) {
            throw new ElderlyPeopleException("Clave duplicada, con NUMERO DE  FACTURA :" + invoice.getNumber(), "CPDuplicada");
        } catch (DataAccessException ex) {
            throw new ElderlyPeopleException("Error en l'accés a la base de dades", "ErrorAccedintDades");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{number}")
    public String processDelete(@PathVariable String number) {
        invoiceDao.deleteInvoice(number);
        return "redirect:../list";
    }
}