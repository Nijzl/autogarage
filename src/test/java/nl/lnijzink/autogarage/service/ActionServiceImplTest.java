package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.ActionDto;
import nl.lnijzink.autogarage.model.Action;
import nl.lnijzink.autogarage.reposit.ActionRepository;
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
class ActionServiceImplTest {

    @Mock
    private ActionRepository mockActionRepository;

    private ActionServiceImpl actionServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        actionServiceImplUnderTest = new ActionServiceImpl(mockActionRepository);
    }

    @Test
    void testGetActions() {
        final List<Action> actions = List.of(new Action(0L, "name", "description", 0.0));
        when(mockActionRepository.findAll()).thenReturn(actions);
        final List<ActionDto> result = actionServiceImplUnderTest.getActions();
    }

    @Test
    void testGetActions_ActionRepositoryReturnsNoItems() {
        when(mockActionRepository.findAll()).thenReturn(Collections.emptyList());
        final List<ActionDto> result = actionServiceImplUnderTest.getActions();
    }

    @Test
    void testGetAction() {
        final Optional<Action> action = Optional.of(new Action(0L, "name", "description", 0.0));
        when(mockActionRepository.findById(0L)).thenReturn(action);
        final ActionDto result = actionServiceImplUnderTest.getAction(0L);
    }

    @Test
    void testGetAction_ActionRepositoryReturnsAbsent() {
        when(mockActionRepository.findById(0L)).thenReturn(Optional.empty());
        final ActionDto result = actionServiceImplUnderTest.getAction(0L);
    }

    @Test
    void testCreateAction() {
        final ActionDto gdto = new ActionDto(0L, "name", "description", 0.0);
        final Action action = new Action(0L, "name", "description", 0.0);
        when(mockActionRepository.save(any(Action.class))).thenReturn(action);
        final Long result = actionServiceImplUnderTest.createAction(gdto);
        assertThat(result).isEqualTo(0L);
        verify(mockActionRepository).save(any(Action.class));
    }

    @Test
    void testDeleteAction() {
        when(mockActionRepository.existsById(0L)).thenReturn(false);
        actionServiceImplUnderTest.deleteAction(0L);
        verify(mockActionRepository).deleteById(0L);
    }

}
