package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitPartDto;
import nl.lnijzink.autogarage.model.*;
import nl.lnijzink.autogarage.reposit.PartRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitPartRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkUnitPartServiceImplTest {

    @Mock
    private WorkUnitRepository mockWorkUnitRepository;
    @Mock
    private PartRepository mockPartRepository;
    @Mock
    private WorkUnitPartRepository mockWorkUnitPartRepository;

    private WorkUnitPartServiceImpl workUnitPartServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        workUnitPartServiceImplUnderTest = new WorkUnitPartServiceImpl(mockWorkUnitRepository, mockPartRepository, mockWorkUnitPartRepository);
    }

    @Test
    void testGetAllWorkUnitParts() {
        final List<WorkUnitPart> workUnitParts = List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0));
        when(mockWorkUnitPartRepository.findAll()).thenReturn(workUnitParts);
        final Collection<WorkUnitPart> result = workUnitPartServiceImplUnderTest.getAllWorkUnitParts();
    }

    @Test
    void testGetAllWorkUnitParts_WorkUnitPartRepositoryReturnsNoItems() {
        when(mockWorkUnitPartRepository.findAll()).thenReturn(Collections.emptyList());
        final Collection<WorkUnitPart> result = workUnitPartServiceImplUnderTest.getAllWorkUnitParts();
    }

    @Test
    void testGetWorkUnitPartsByWorkUnitId() {
        final Collection<WorkUnitPart> workUnitParts = List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0));
        when(mockWorkUnitPartRepository.findAllByWorkUnitId(0L)).thenReturn(workUnitParts);
        final Collection<WorkUnitPart> result = workUnitPartServiceImplUnderTest.getWorkUnitPartsByWorkUnitId(0L);
    }

    @Test
    void testGetWorkUnitPartsByWorkUnitId_WorkUnitPartRepositoryReturnsNoItems() {
        when(mockWorkUnitPartRepository.findAllByWorkUnitId(0L)).thenReturn(Collections.emptyList());
        final Collection<WorkUnitPart> result = workUnitPartServiceImplUnderTest.getWorkUnitPartsByWorkUnitId(0L);
    }

    @Test
    void testAddWorkUnitPart() {
        final WorkUnitPartDto workUnitPart = new WorkUnitPartDto(0L, 0L, 0L);
        when(mockWorkUnitRepository.existsById(0L)).thenReturn(false);
        final Optional<WorkUnit> workUnit = Optional.of(new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)));
        when(mockWorkUnitRepository.findById(0L)).thenReturn(workUnit);
        when(mockPartRepository.existsById(0L)).thenReturn(false);
        final Optional<Part> part = Optional.of(new Part(0L, "name", 0.0, 0L, List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), null, 0L, 0.0))));
        when(mockPartRepository.findById(0L)).thenReturn(part);
        final WorkUnitPart workUnitPart1 = new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0);
        when(mockWorkUnitPartRepository.save(any(WorkUnitPart.class))).thenReturn(workUnitPart1);
        final WorkUnitPart result = workUnitPartServiceImplUnderTest.addWorkUnitPart(workUnitPart);
        verify(mockWorkUnitPartRepository).save(any(WorkUnitPart.class));
    }

    @Test
    void testAddWorkUnitPart_WorkUnitRepositoryFindByIdReturnsAbsent() {
        final WorkUnitPartDto workUnitPart = new WorkUnitPartDto(0L, 0L, 0L);
        when(mockWorkUnitRepository.existsById(0L)).thenReturn(false);
        when(mockWorkUnitRepository.findById(0L)).thenReturn(Optional.empty());
        when(mockPartRepository.existsById(0L)).thenReturn(false);
        final Optional<Part> part = Optional.of(new Part(0L, "name", 0.0, 0L, List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), null, 0L, 0.0))));
        when(mockPartRepository.findById(0L)).thenReturn(part);
        final WorkUnitPart workUnitPart1 = new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0);
        when(mockWorkUnitPartRepository.save(any(WorkUnitPart.class))).thenReturn(workUnitPart1);
        final WorkUnitPart result = workUnitPartServiceImplUnderTest.addWorkUnitPart(workUnitPart);
        verify(mockWorkUnitPartRepository).save(any(WorkUnitPart.class));
    }

    @Test
    void testAddWorkUnitPart_PartRepositoryFindByIdReturnsAbsent() {
        final WorkUnitPartDto workUnitPart = new WorkUnitPartDto(0L, 0L, 0L);
        when(mockWorkUnitRepository.existsById(0L)).thenReturn(false);
        final Optional<WorkUnit> workUnit = Optional.of(new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)));
        when(mockWorkUnitRepository.findById(0L)).thenReturn(workUnit);
        when(mockPartRepository.existsById(0L)).thenReturn(false);
        when(mockPartRepository.findById(0L)).thenReturn(Optional.empty());
        final WorkUnitPart workUnitPart1 = new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0);
        when(mockWorkUnitPartRepository.save(any(WorkUnitPart.class))).thenReturn(workUnitPart1);
        final WorkUnitPart result = workUnitPartServiceImplUnderTest.addWorkUnitPart(workUnitPart);
        verify(mockWorkUnitPartRepository).save(any(WorkUnitPart.class));
    }

}
