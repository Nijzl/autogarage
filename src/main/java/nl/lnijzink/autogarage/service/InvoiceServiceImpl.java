package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.InvoiceDto;
import nl.lnijzink.autogarage.model.Invoice;
import nl.lnijzink.autogarage.model.WorkUnitAction;
import nl.lnijzink.autogarage.model.WorkUnitPart;
import nl.lnijzink.autogarage.reposit.InvoiceRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final WorkUnitRepository workUnitRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, WorkUnitRepository workUnitRepository){
        this.invoiceRepository = invoiceRepository;
        this.workUnitRepository = workUnitRepository;
    }


    // Get List of Invoices
    @Override
    public List<InvoiceDto> getInvoices(){
        ArrayList<InvoiceDto> pList = new ArrayList<>();
        invoiceRepository.findAll().forEach((p) -> pList.add(new InvoiceDto(p.getInvoiceId(), p.getWorkUnit(),
                p.getSubTotalCheck(), p.getSubTotalParts(), p.getSubTotalActions(), p.getTax(), p.getTotal(),
                p.getPaymentStatus()
        )));
        return pList;
    }

    // Get Single Invoice
    @Override
    public InvoiceDto getInvoice(Long invoiceId){
        Invoice i = invoiceRepository.findById(invoiceId).get();
        return new InvoiceDto(i.getInvoiceId(),i.getWorkUnit(),i.getSubTotalCheck(),i.getSubTotalParts(),
                i.getSubTotalActions(),i.getTax(),i.getTotal(),i.getPaymentStatus());
    }

    // Create New Invoice
    @Override
    public Long createInvoice(InvoiceDto invoiceDto){
        Invoice i = new Invoice();
        var totalParts = new ArrayList<Double>();
        var resultParts = new Double(0);
        var totalActions = new ArrayList<Double>();
        var resultActions = new Double(0);
        for( WorkUnitPart workUnitPart: invoiceDto.getWorkUnit().getWorkUnitParts()) {
            totalParts.add(workUnitPart.getTotalPartCost());
        }
        for( WorkUnitAction workUnitAction: invoiceDto.getWorkUnit().getWorkUnitActions()) {
            totalActions.add(workUnitAction.getTotalActionCost());
        }
        for( Double totalPart: totalParts){
            resultParts += totalPart;
        }
        for( Double totalAction: totalActions){
            resultActions += totalAction;
        }
        i.setSubTotalParts((double)Math.round(resultParts*100)/100);
        i.setSubTotalActions((double)Math.round(resultActions*100)/100);
        i.setWorkUnit(invoiceDto.getWorkUnit());
        i.setPaymentStatus(invoiceDto.getPaymentStatus());
        i.setSubTotalCheck((double)45);
        i.setTax((double)0.21);
        var totalExcl = (i.getSubTotalParts()+i.getSubTotalCheck()+i.getSubTotalActions());
        var tax = (totalExcl * i.getTax());
        i.setTotal((double) Math.round( (totalExcl + tax) *100)/100);
        invoiceRepository.save(i);
        return i.getInvoiceId();
    }

    // Delete Invoice
    @Override
    public void deleteInvoice(Long invoiceId){
        boolean exists = invoiceRepository.existsById(invoiceId);
        if(exists){
            invoiceRepository.deleteById(invoiceId);
        }
    }

    // Assign Invoice to Work Unit
    @Override
    public ResponseEntity<Object> assignInvoiceToWorkUnit(Long id, Long invoiceId) {
        var optionalWorkUnit = workUnitRepository.findById(id);
        var optionalInvoice = invoiceRepository.findById(invoiceId);

        if (optionalWorkUnit.isPresent() && optionalInvoice.isPresent()) {
            var workUnit = optionalWorkUnit.get();
            var invoice = optionalInvoice.get();
            invoice.setWorkUnit(workUnit);
            invoiceRepository.save(invoice);
        }
        return ResponseEntity.ok("Success");
    }

}
