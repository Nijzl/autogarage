package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.lnijzink.autogarage.model.PaymentStatus;
import nl.lnijzink.autogarage.model.WorkUnit;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceDto {

    @NotNull
    private Long invoiceId;

    private WorkUnit workUnit;

    private Double subTotalCheck;

    private Double subTotalParts;

    private Double subTotalActions;

    private Double tax;

    private Double total;

    private PaymentStatus paymentStatus;

    public InvoiceDto(Long invoiceId) {
    }

}
