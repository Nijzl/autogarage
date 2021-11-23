package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.EmployeeDto;
import nl.lnijzink.autogarage.model.Employee;
import nl.lnijzink.autogarage.reposit.EmployeeRepository;
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
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository mockEmployeeRepository;

    private EmployeeServiceImpl employeeServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        employeeServiceImplUnderTest = new EmployeeServiceImpl(mockEmployeeRepository);
    }

    @Test
    void testGetEmployees() {
        final List<Employee> employees = List.of(new Employee(0L, "name", "role"));
        when(mockEmployeeRepository.findAll()).thenReturn(employees);
        final List<EmployeeDto> result = employeeServiceImplUnderTest.getEmployees();
    }

    @Test
    void testGetEmployees_EmployeeRepositoryReturnsNoItems() {
        when(mockEmployeeRepository.findAll()).thenReturn(Collections.emptyList());
        final List<EmployeeDto> result = employeeServiceImplUnderTest.getEmployees();
    }

    @Test
    void testGetEmployee() {
        final Optional<Employee> employee = Optional.of(new Employee(0L, "name", "role"));
        when(mockEmployeeRepository.findById(0L)).thenReturn(employee);
        final EmployeeDto result = employeeServiceImplUnderTest.getEmployee(0L);
    }

    @Test
    void testGetEmployee_EmployeeRepositoryReturnsAbsent() {
        when(mockEmployeeRepository.findById(0L)).thenReturn(Optional.empty());
        final EmployeeDto result = employeeServiceImplUnderTest.getEmployee(0L);
    }

    @Test
    void testCreateEmployee() {
        final EmployeeDto edto = new EmployeeDto(0L, "name", "role");
        when(mockEmployeeRepository.save(any(Employee.class))).thenReturn(new Employee(0L, "name", "role"));
        final Long result = employeeServiceImplUnderTest.createEmployee(edto);
        assertThat(result).isEqualTo(0L);
        verify(mockEmployeeRepository).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        when(mockEmployeeRepository.existsById(0L)).thenReturn(false);
        employeeServiceImplUnderTest.deleteEmployee(0L);
        verify(mockEmployeeRepository).deleteById(0L);
    }

}
