package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitActionDto;
import nl.lnijzink.autogarage.model.*;
import nl.lnijzink.autogarage.reposit.ActionRepository;
import nl.lnijzink.autogarage.reposit.WorkUnitActionRepository;
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
class WorkUnitActionServiceImplTest {

    @Mock
    private WorkUnitRepository mockWorkUnitRepository;
    @Mock
    private ActionRepository mockActionRepository;
    @Mock
    private WorkUnitActionRepository mockWorkUnitActionRepository;

    private WorkUnitActionServiceImpl workUnitActionServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        workUnitActionServiceImplUnderTest = new WorkUnitActionServiceImpl(mockWorkUnitRepository, mockActionRepository, mockWorkUnitActionRepository);
    }

    @Test
    void testGetAllWorkUnitActions() {
        final List<WorkUnitAction> workUnitActions = List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Action(0L, "name", "description", 0.0), 0L, 0.0));
        when(mockWorkUnitActionRepository.findAll()).thenReturn(workUnitActions);
        final Collection<WorkUnitAction> result = workUnitActionServiceImplUnderTest.getAllWorkUnitActions();
    }

    @Test
    void testGetAllWorkUnitActions_WorkUnitActionRepositoryReturnsNoItems() {
        when(mockWorkUnitActionRepository.findAll()).thenReturn(Collections.emptyList());
        final Collection<WorkUnitAction> result = workUnitActionServiceImplUnderTest.getAllWorkUnitActions();
    }

    @Test
    void testGetWorkUnitActionsByWorkUnitId() {
        final Collection<WorkUnitAction> workUnitActions = List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Action(0L, "name", "description", 0.0), 0L, 0.0));
        when(mockWorkUnitActionRepository.findAllByWorkUnitId(0L)).thenReturn(workUnitActions);
        final Collection<WorkUnitAction> result = workUnitActionServiceImplUnderTest.getWorkUnitActionsByWorkUnitId(0L);
    }

    @Test
    void testGetWorkUnitActionsByWorkUnitId_WorkUnitActionRepositoryReturnsNoItems() {
        when(mockWorkUnitActionRepository.findAllByWorkUnitId(0L)).thenReturn(Collections.emptyList());
        final Collection<WorkUnitAction> result = workUnitActionServiceImplUnderTest.getWorkUnitActionsByWorkUnitId(0L);
    }

    @Test
    void testAddWorkUnitAction() {
        final WorkUnitActionDto workUnitAction = new WorkUnitActionDto(0L, 0L, 0L);
        when(mockWorkUnitRepository.existsById(0L)).thenReturn(false);
        final Optional<WorkUnit> workUnit = Optional.of(new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)));
        when(mockWorkUnitRepository.findById(0L)).thenReturn(workUnit);
        when(mockActionRepository.existsById(0L)).thenReturn(false);
        final Optional<Action> action = Optional.of(new Action(0L, "name", "description", 0.0));
        when(mockActionRepository.findById(0L)).thenReturn(action);
        final WorkUnitAction workUnitAction1 = new WorkUnitAction(new WorkUnitActionKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Action(0L, "name", "description", 0.0), 0L, 0.0);
        when(mockWorkUnitActionRepository.save(any(WorkUnitAction.class))).thenReturn(workUnitAction1);
        final WorkUnitAction result = workUnitActionServiceImplUnderTest.addWorkUnitAction(workUnitAction);
        verify(mockWorkUnitActionRepository).save(any(WorkUnitAction.class));
    }

    @Test
    void testAddWorkUnitAction_WorkUnitRepositoryFindByIdReturnsAbsent() {
        final WorkUnitActionDto workUnitAction = new WorkUnitActionDto(0L, 0L, 0L);
        when(mockWorkUnitRepository.existsById(0L)).thenReturn(false);
        when(mockWorkUnitRepository.findById(0L)).thenReturn(Optional.empty());
        when(mockActionRepository.existsById(0L)).thenReturn(false);
        final Optional<Action> action = Optional.of(new Action(0L, "name", "description", 0.0));
        when(mockActionRepository.findById(0L)).thenReturn(action);
        final WorkUnitAction workUnitAction1 = new WorkUnitAction(new WorkUnitActionKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Action(0L, "name", "description", 0.0), 0L, 0.0);
        when(mockWorkUnitActionRepository.save(any(WorkUnitAction.class))).thenReturn(workUnitAction1);
        final WorkUnitAction result = workUnitActionServiceImplUnderTest.addWorkUnitAction(workUnitAction);
        verify(mockWorkUnitActionRepository).save(any(WorkUnitAction.class));
    }

    @Test
    void testAddWorkUnitAction_ActionRepositoryFindByIdReturnsAbsent() {
        final WorkUnitActionDto workUnitAction = new WorkUnitActionDto(0L, 0L, 0L);
        when(mockWorkUnitRepository.existsById(0L)).thenReturn(false);
        final Optional<WorkUnit> workUnit = Optional.of(new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)));
        when(mockWorkUnitRepository.findById(0L)).thenReturn(workUnit);
        when(mockActionRepository.existsById(0L)).thenReturn(false);
        when(mockActionRepository.findById(0L)).thenReturn(Optional.empty());
        final WorkUnitAction workUnitAction1 = new WorkUnitAction(new WorkUnitActionKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), new Action(0L, "name", "description", 0.0), 0L, 0.0);
        when(mockWorkUnitActionRepository.save(any(WorkUnitAction.class))).thenReturn(workUnitAction1);
        final WorkUnitAction result = workUnitActionServiceImplUnderTest.addWorkUnitAction(workUnitAction);
        verify(mockWorkUnitActionRepository).save(any(WorkUnitAction.class));
    }

}
