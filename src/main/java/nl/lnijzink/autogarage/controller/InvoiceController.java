package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.InvoiceDto;
import nl.lnijzink.autogarage.model.Invoice;
import nl.lnijzink.autogarage.reposit.InvoiceRepository;
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
    private final InvoiceRepository invoiceRepository;

    protected InvoiceController(InvoiceService invoiceService, InvoiceRepository invoiceRepository){
        this.invoiceService = invoiceService;
        this.invoiceRepository = invoiceRepository;}


    // Get List of Invoices
    @GetMapping("/")
    public String getInvoices(Model model) {
        var invoices = invoiceService.getInvoices();
        model.addAttribute("listOfInvoices", invoices);
        return "InvoicesList";
    }

    // Get Single Invoice
    @GetMapping("/view/{id}")
    public String getInvoice(@PathVariable("id") Long id, Model model) {
        InvoiceDto invoice = invoiceService.getInvoice(id);
        model.addAttribute("invoice", invoice);
        return "InvoiceGet";
    }

    // Create New Invoice
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

    // Update Invoice
    @GetMapping("/update/{id}")
    public String updateInvoice(@PathVariable("id") Long id, Model model) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid invoice Id:" + id));
        model.addAttribute("invoice", invoice);
        return "InvoiceUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateInvoice(@PathVariable("id") Long id, @Valid Invoice invoice,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            invoice.setId(id);
            return "InvoiceUpdate";
        }
        invoiceRepository.save(invoice);
        return "redirect:/invoices/";
    }

    // Delete Invoice
    @DeleteMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable("invoiceId") Long id) {
        invoiceService.deleteInvoice(id);
        return "redirect:/invoices/";
    }

}
