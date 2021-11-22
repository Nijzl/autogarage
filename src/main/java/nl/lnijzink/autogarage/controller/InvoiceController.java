package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.InvoiceDto;
import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.dto.WorkUnitPartDto;
import nl.lnijzink.autogarage.model.Invoice;
import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.model.WorkUnit;
import nl.lnijzink.autogarage.model.WorkUnitPart;
import nl.lnijzink.autogarage.reposit.InvoiceRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import nl.lnijzink.autogarage.service.InvoiceService;
import nl.lnijzink.autogarage.service.WorkUnitActionService;
import nl.lnijzink.autogarage.service.WorkUnitPartService;
import nl.lnijzink.autogarage.service.WorkUnitService;
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
    private final WorkUnitService workUnitService;
    private final WorkUnitPartService workUnitPartService;
    private final WorkUnitActionService workUnitActionService;

    protected InvoiceController(InvoiceService invoiceService, InvoiceRepository invoiceRepository,
                                WorkUnitRepository workUnitRepository, WorkUnitService workUnitService,
                                WorkUnitPartService workUnitPartService, WorkUnitActionService workUnitActionService){
        this.invoiceService = invoiceService;
        this.invoiceRepository = invoiceRepository;
        this.workUnitRepository = workUnitRepository;
        this.workUnitService = workUnitService;
        this.workUnitPartService = workUnitPartService;
        this.workUnitActionService = workUnitActionService;
    }


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
    @GetMapping("/create/{id}")
    public String createInvoice(@PathVariable("id") Long id, Model model){
        Invoice invoice = new Invoice();
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        model.addAttribute("invoice", invoice);
        model.addAttribute("workUnit", workUnit);
        model.addAttribute("listOfWorkUnitParts", workUnitPartService.getWorkUnitPartsByWorkUnitId(id));
        model.addAttribute("listOfWorkUnitActions", workUnitActionService.getWorkUnitActionsByWorkUnitId(id));
        return "InvoiceForm";
    }

    @PostMapping("/create/{id}")
    public String createInvoice(@PathVariable("id") Long id, Model model, InvoiceDto invoiceDto,
                                BindingResult bindingResult){
        WorkUnit workUnit = workUnitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work unit Id:" + id));
        model.addAttribute("workUnit", workUnit);
        if (bindingResult.hasErrors()) {
            return "InvoiceForm";
        }
        invoiceService.createInvoice(invoiceDto);
        return "redirect:/invoices/";
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
    @GetMapping("/delete/{invoiceId}")
    public String deleteInvoice(@PathVariable("invoiceId") Long invoiceId, Model model) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid invoice Id:" + invoiceId));
        invoiceRepository.delete(invoice);
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
