package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CarRepository;
import nl.lnijzink.autogarage.reposit.CustomerRepository;
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
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository mockCustomerRepository;
    @Mock
    private CarRepository mockCarRepository;

    private CustomerServiceImpl customerServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        customerServiceImplUnderTest = new CustomerServiceImpl(mockCustomerRepository, mockCarRepository);
    }

    @Test
    void testGetCustomers() {
        final List<Customer> customers = List.of(new Customer(0L, "fullName", "address", "phoneNumber", "email", List.of(new Car("licencePlate", "brand", "model", 2020))));
        when(mockCustomerRepository.findAll()).thenReturn(customers);
        final List<CustomerDto> result = customerServiceImplUnderTest.getCustomers();
    }

    @Test
    void testGetCustomers_CustomerRepositoryReturnsNoItems() {
        when(mockCustomerRepository.findAll()).thenReturn(Collections.emptyList());
        final List<CustomerDto> result = customerServiceImplUnderTest.getCustomers();
    }

    @Test
    void testGetCustomer() {
        final Optional<Customer> customer = Optional.of(new Customer(0L, "fullName", "address", "phoneNumber", "email", List.of(new Car("licencePlate", "brand", "model", 2020))));
        when(mockCustomerRepository.findById(0L)).thenReturn(customer);
        final CustomerDto result = customerServiceImplUnderTest.getCustomer(0L);
    }

    @Test
    void testGetCustomer_CustomerRepositoryReturnsAbsent() {
        when(mockCustomerRepository.findById(0L)).thenReturn(Optional.empty());
        final CustomerDto result = customerServiceImplUnderTest.getCustomer(0L);
    }

    @Test
    void testCreateCustomer() {
        final CustomerDto cdto = new CustomerDto(0L, "fullName", "address", "phoneNumber", "email");
        final Customer customer = new Customer(0L, "fullName", "address", "phoneNumber", "email", List.of(new Car("licencePlate", "brand", "model", 2020)));
        when(mockCustomerRepository.save(any(Customer.class))).thenReturn(customer);
        final Long result = customerServiceImplUnderTest.createCustomer(cdto);
        assertThat(result).isEqualTo(0L);
        verify(mockCustomerRepository).save(any(Customer.class));
    }

    @Test
    void testDeleteCustomer() {
        when(mockCustomerRepository.existsById(0L)).thenReturn(false);
        customerServiceImplUnderTest.deleteCustomer(0L);
        verify(mockCustomerRepository).deleteById(0L);
    }

}
