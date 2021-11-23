package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.PartDto;
import nl.lnijzink.autogarage.model.*;
import nl.lnijzink.autogarage.reposit.PartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PartServiceImplTest {

    @Mock
    private PartRepository mockPartRepository;

    private PartServiceImpl partServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        partServiceImplUnderTest = new PartServiceImpl(mockPartRepository);
    }

    @Test
    void testGetParts() {
        final List<Part> parts = List.of(new Part(0L, "name", 0.0, 0L, List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), null, 0L, 0.0))));
        when(mockPartRepository.findAll()).thenReturn(parts);
        final List<PartDto> result = partServiceImplUnderTest.getParts();
    }

    @Test
    void testGetParts_PartRepositoryReturnsNoItems() {
        when(mockPartRepository.findAll()).thenReturn(Collections.emptyList());
        final List<PartDto> result = partServiceImplUnderTest.getParts();
    }

    @Test
    void testGetPart() {
        final Optional<Part> part = Optional.of(new Part(0L, "name", 0.0, 0L, List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), null, 0L, 0.0))));
        when(mockPartRepository.findById(0L)).thenReturn(part);
        final PartDto result = partServiceImplUnderTest.getPart(0L);
    }

    @Test
    void testGetPart_PartRepositoryReturnsAbsent() {
        when(mockPartRepository.findById(0L)).thenReturn(Optional.empty());
        final PartDto result = partServiceImplUnderTest.getPart(0L);
    }

    @Test
    void testCreatePart() {
        final PartDto odto = new PartDto(0L, "name", 0.0, 0L);
        final Part part = new Part(0L, "name", 0.0, 0L, List.of(new WorkUnitPart(new WorkUnitPartKey(0L, 0L), new WorkUnit(0L, Type.CHECK, "mechanic", new Car("licencePlate", "brand", "model", 2020), List.of(new WorkUnitAction(new WorkUnitActionKey(0L, 0L), null, new Action(0L, "name", "description", 0.0), 0L, 0.0)), List.of(), CustomerStatus.DISAGREE, RepairStatus.NOTPERFORMED, new Invoice(0L, null, 0.0, 0.0, 0.0, 0.0, 0.0, PaymentStatus.PAID)), null, 0L, 0.0)));
        when(mockPartRepository.save(any(Part.class))).thenReturn(part);
        final Long result = partServiceImplUnderTest.createPart(odto);
        assertThat(result).isEqualTo(0L);
        verify(mockPartRepository).save(any(Part.class));
    }

    @Test
    void testDeletePart() {
        when(mockPartRepository.existsById(0L)).thenReturn(false);
        partServiceImplUnderTest.deletePart(0L);
        verify(mockPartRepository).deleteById(0L);
    }

}
