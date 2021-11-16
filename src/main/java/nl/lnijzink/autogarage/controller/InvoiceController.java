package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.InvoiceDto;
import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.Invoice;
import nl.lnijzink.autogarage.reposit.InvoiceRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
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
    private final WorkUnitRepository workUnitRepository;

    protected InvoiceController(InvoiceService invoiceService, InvoiceRepository invoiceRepository,
                                WorkUnitRepository workUnitRepository){
        this.invoiceService = invoiceService;
        this.invoiceRepository = invoiceRepository;
        this.workUnitRepository = workUnitRepository;}


    // Get List of Invoices
    @GetMapping("/")
    public String getInvoices(Model model) {
        var invoices = invoiceService.getInvoices();
        model.addAttribute("listOfInvoices", invoices);
        return "InvoicesList";
    }

    // Get Single Invoice
    @GetMapping("/view/{invoiceId}")
    public String getInvoice(@PathVariable("invoiceId") Long invoiceId, Model model) {
        InvoiceDto invoice = invoiceService.getInvoice(invoiceId);
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
    @GetMapping("/update/{invoiceId}")
    public String updateInvoice(@PathVariable("invoiceId") Long invoiceId, Model model) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid invoice Id:" + invoiceId));
        model.addAttribute("invoice", invoice);
        return "InvoiceUpdate";
    }

    @PostMapping("/update/{invoiceId}")
    public String updateInvoice(@PathVariable("invoiceId") Long invoiceId, @Valid Invoice invoice,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            invoice.setInvoiceId(invoiceId);
            return "InvoiceUpdate";
        }
        invoiceRepository.save(invoice);
        return "redirect:/invoices/";
    }

    // Delete Invoice
    @DeleteMapping("/delete/{invoiceId}")
    public String deleteInvoice(@PathVariable("invoiceId") Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return "redirect:/invoices/";
    }

    // Assign Invoice to Work Unit
    @GetMapping("/workUnit")
    public String assignInvoiceToWorkUnit(Model model){
        model.addAttribute("WorkUnit", new WorkUnitDto());
        model.addAttribute("Invoice", new InvoiceDto());
        model.addAttribute("listOfWorkUnits", workUnitRepository.findAll());
        model.addAttribute("listOfInvoices", invoiceRepository.findAll());
        return "LinkWorkUnitAndInvoice";
    }

    @PostMapping("/workUnit")
    public String assignInvoiceToWorkUnit(@RequestParam Long id,
                                         @RequestParam Long invoiceId) {
        invoiceService.assignInvoiceToWorkUnit(id, invoiceId);
        return "LinkWorkUnitAndInvoiceSuccessful";
    }

}
