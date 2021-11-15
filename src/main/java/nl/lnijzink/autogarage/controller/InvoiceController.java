package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.InvoiceDto;
import nl.lnijzink.autogarage.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    protected InvoiceController(InvoiceService invoiceService){this.invoiceService = invoiceService;}

    @GetMapping("/")
    public String getInvoices(Model model) {
        var invoices = invoiceService.getInvoices();
        model.addAttribute("listOfInvoices", invoices);
        return "InvoicesList";
    }

    @GetMapping("/{id}")
    public InvoiceDto getInvoice(@PathVariable Long id){
        return invoiceService.getInvoice(id);
    }

    @GetMapping("/create")
    public String createInvoice(Model model){
        model.addAttribute("Invoice", new InvoiceDto());
        return "InvoiceForm";
    }

    @PostMapping("/create")
    public String createInvoice(@Valid @ModelAttribute("Invoice") InvoiceDto invoiceDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "InvoiceForm";
        }
        invoiceService.createInvoice(invoiceDto);
        return "InvoiceDisplay";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable("invoiceId") Long id) {
        invoiceService.deleteInvoice(id);
        return "InvoiceDeleteDisplay";
    }

}
