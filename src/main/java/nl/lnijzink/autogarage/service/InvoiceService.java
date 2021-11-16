package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.InvoiceDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvoiceService {

    public Long createInvoice(InvoiceDto invoiceDto);
    public InvoiceDto getInvoice(Long id);
    public void deleteInvoice(Long id);
    public List<InvoiceDto> getInvoices();
    public ResponseEntity assignInvoiceToWorkUnit(Long id, Long invoiceId);

}
