package nl.lnijzink.autogarage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Invoice")
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long invoiceId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private WorkUnit workUnit;

}
