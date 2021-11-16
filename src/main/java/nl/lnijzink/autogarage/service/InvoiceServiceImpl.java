package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.InvoiceDto;
import nl.lnijzink.autogarage.model.Invoice;
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
        this.workUnitRepository = workUnitRepository;}


    // Get List of Invoices
    @Override
    public List<InvoiceDto> getInvoices(){
        ArrayList<InvoiceDto> pList = new ArrayList<>();
        invoiceRepository.findAll().forEach((p) -> pList.add(new InvoiceDto(p.getInvoiceId()
        )));
        return pList;
    }

    // Get Single Invoice
    @Override
    public InvoiceDto getInvoice(Long invoiceId){
        Invoice i = invoiceRepository.findById(invoiceId).get();
        return new InvoiceDto(i.getInvoiceId());
    }

    // Create New Invoice
    @Override
    public Long createInvoice(InvoiceDto invoiceDto){
        Invoice i = new Invoice();
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
