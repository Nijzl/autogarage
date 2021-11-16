package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.InvoiceDto;
import nl.lnijzink.autogarage.model.Invoice;
import nl.lnijzink.autogarage.reposit.InvoiceRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
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
        invoiceRepository.findAll().forEach((p) -> pList.add(new InvoiceDto(p.getId()
        )));
        return pList;
    }

    // Get Single Invoice
    @Override
    public InvoiceDto getInvoice(Long id){
        Invoice i = invoiceRepository.findById(id).get();
        return new InvoiceDto(i.getId());
    }

    // Create New Invoice
    @Override
    public Long createInvoice(InvoiceDto invoiceDto){
        Invoice i = new Invoice();
        invoiceRepository.save(i);
        return i.getId();
    }

    // Delete Invoice
    @Override
    public void deleteInvoice(Long id){
        boolean exists = invoiceRepository.existsById(id);
        if(exists){
            invoiceRepository.deleteById(id);
        }
    }

}
