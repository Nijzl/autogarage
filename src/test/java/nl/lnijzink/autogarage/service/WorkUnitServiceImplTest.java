package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkUnitDto;
import nl.lnijzink.autogarage.model.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkUnitServiceImplTest {

    @Mock
    private WorkUnitRepository mockWorkUnitRepository;

    private WorkUnitServiceImpl workUnitServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        workUnitServiceImplUnderTest = new WorkUnitServiceImpl(mockWorkUnitRepository);
    }

    @Test
    void testGetWorkUnits() {
        final List<WorkUnit> workUnits = List.of(new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)));
        when(mockWorkUnitRepository.findAll()).thenReturn(workUnits);
        final List<WorkUnitDto> result = workUnitServiceImplUnderTest.getWorkUnits();
    }

    @Test
    void testGetWorkUnits_WorkUnitRepositoryReturnsNoItems() {
        when(mockWorkUnitRepository.findAll()).thenReturn(Collections.emptyList());
        final List<WorkUnitDto> result = workUnitServiceImplUnderTest.getWorkUnits();
    }

    @Test
    void testGetWorkUnit() {
        final Optional<WorkUnit> workUnit = Optional.of(new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)));
        when(mockWorkUnitRepository.findById(0L)).thenReturn(workUnit);
        final WorkUnitDto result = workUnitServiceImplUnderTest.getWorkUnit(0L);
    }

    @Test
    void testGetWorkUnit_WorkUnitRepositoryReturnsAbsent() {
        when(mockWorkUnitRepository.findById(0L)).thenReturn(Optional.empty());
        final WorkUnitDto result = workUnitServiceImplUnderTest.getWorkUnit(0L);
    }

    @Test
    void testCreateWorkUnit() {
        final WorkUnitDto dto = new WorkUnitDto(0L, Type.CHECK, new Car("licencePlate", "brand", "model", 2020), "mechanic", CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED);
        final WorkUnit workUnit = new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID));
        when(mockWorkUnitRepository.save(any(WorkUnit.class))).thenReturn(workUnit);
        final Long result = workUnitServiceImplUnderTest.createWorkUnit(dto);
        assertThat(result).isEqualTo(0L);
        verify(mockWorkUnitRepository).save(any(WorkUnit.class));
    }

    @Test
    void testDeleteWorkUnit() {
        when(mockWorkUnitRepository.existsById(0L)).thenReturn(false);
        workUnitServiceImplUnderTest.deleteWorkUnit(0L);
        verify(mockWorkUnitRepository).deleteById(0L);
    }

    @Test
    void testGetAllByRepairStatus() {
        final Collection<WorkUnit> workUnits = List.of(new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), null, new Part(0L, "name", 0.0, 0L, List.of()), 0L, 0.0)), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)));
        when(mockWorkUnitRepository.findAllByRepairStatus(RepairStatus.NOTPERFORMED)).thenReturn(workUnits);
        final Collection<WorkUnit> result = workUnitServiceImplUnderTest.getAllByRepairStatus(RepairStatus.NOTPERFORMED);
    }

    @Test
    void testGetAllByRepairStatus_WorkUnitRepositoryReturnsNoItems() {
        when(mockWorkUnitRepository.findAllByRepairStatus(RepairStatus.NOTPERFORMED)).thenReturn(Collections.emptyList());
        final Collection<WorkUnit> result = workUnitServiceImplUnderTest.getAllByRepairStatus(RepairStatus.NOTPERFORMED);
    }

}
