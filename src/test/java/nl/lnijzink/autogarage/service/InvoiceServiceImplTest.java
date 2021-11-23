package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.InvoiceDto;
import nl.lnijzink.autogarage.model.*;
import nl.lnijzink.autogarage.reposit.InvoiceRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository mockInvoiceRepository;
    @Mock
    private WorkUnitRepository mockWorkUnitRepository;

    private InvoiceServiceImpl invoiceServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        invoiceServiceImplUnderTest = new InvoiceServiceImpl(mockInvoiceRepository, mockWorkUnitRepository);
    }

    @Test
    void testGetInvoices() {
        final List<Invoice> invoices = List.of(new Invoice(0L, new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, null), 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID));
        when(mockInvoiceRepository.findAll()).thenReturn(invoices);
        final List<InvoiceDto> result = invoiceServiceImplUnderTest.getInvoices();
    }

    @Test
    void testGetInvoices_InvoiceRepositoryReturnsNoItems() {
        when(mockInvoiceRepository.findAll()).thenReturn(Collections.emptyList());
        final List<InvoiceDto> result = invoiceServiceImplUnderTest.getInvoices();
    }

    @Test
    void testGetInvoice() {
        final Optional<Invoice> invoice = Optional.of(new Invoice(0L, new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, null), 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID));
        when(mockInvoiceRepository.findById(0L)).thenReturn(invoice);
        final InvoiceDto result = invoiceServiceImplUnderTest.getInvoice(0L);
    }

    @Test
    void testGetInvoice_InvoiceRepositoryReturnsAbsent() {
        when(mockInvoiceRepository.findById(0L)).thenReturn(Optional.empty());
        final InvoiceDto result = invoiceServiceImplUnderTest.getInvoice(0L);
    }

    @Test
    void testCreateInvoice() {
        final InvoiceDto invoiceDto = new InvoiceDto(0L);
        final Invoice invoice = new Invoice(0L, new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, null), 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID);
        when(mockInvoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        final Long result = invoiceServiceImplUnderTest.createInvoice(invoiceDto);
        assertThat(result).isEqualTo(0L);
        verify(mockInvoiceRepository).save(any(Invoice.class));
    }

    @Test
    void testDeleteInvoice() {
        when(mockInvoiceRepository.existsById(0L)).thenReturn(false);
        invoiceServiceImplUnderTest.deleteInvoice(0L);
        verify(mockInvoiceRepository).deleteById(0L);
    }

    @Test
    void testAssignInvoiceToWorkUnit() {
        final Optional<WorkUnit> workUnit = Optional.of(new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)));
        when(mockWorkUnitRepository.findById(0L)).thenReturn(workUnit);
        final Optional<Invoice> invoice = Optional.of(new Invoice(0L, new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, null), 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID));
        when(mockInvoiceRepository.findById(0L)).thenReturn(invoice);
        final Invoice invoice1 = new Invoice(0L, new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, null), 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID);
        when(mockInvoiceRepository.save(any(Invoice.class))).thenReturn(invoice1);
        final ResponseEntity<Object> result = invoiceServiceImplUnderTest.assignInvoiceToWorkUnit(0L, 0L);
        verify(mockInvoiceRepository).save(any(Invoice.class));
    }

    @Test
    void testAssignInvoiceToWorkUnit_WorkUnitRepositoryReturnsAbsent() {
        when(mockWorkUnitRepository.findById(0L)).thenReturn(Optional.empty());
        final Optional<Invoice> invoice = Optional.of(new Invoice(0L, new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, null), 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID));
        when(mockInvoiceRepository.findById(0L)).thenReturn(invoice);
        final Invoice invoice1 = new Invoice(0L, new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, null), 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID);
        when(mockInvoiceRepository.save(any(Invoice.class))).thenReturn(invoice1);
        final ResponseEntity<Object> result = invoiceServiceImplUnderTest.assignInvoiceToWorkUnit(0L, 0L);
        verify(mockInvoiceRepository).save(any(Invoice.class));
    }

    @Test
    void testAssignInvoiceToWorkUnit_InvoiceRepositoryFindByIdReturnsAbsent() {
        final Optional<WorkUnit> workUnit = Optional.of(new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)));
        when(mockWorkUnitRepository.findById(0L)).thenReturn(workUnit);
        when(mockInvoiceRepository.findById(0L)).thenReturn(Optional.empty());
        final Invoice invoice = new Invoice(0L, new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, null), 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID);
        when(mockInvoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        final ResponseEntity<Object> result = invoiceServiceImplUnderTest.assignInvoiceToWorkUnit(0L, 0L);
        verify(mockInvoiceRepository).save(any(Invoice.class));
    }

}
