package nl.lnijzink.autogarage.reposit;

import nl.lnijzink.autogarage.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
