package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    public long createCustomer(CustomerDto cdto);
    public CustomerDto getCustomer(long id);
    public List<CustomerDto> getCustomers();
    public ResponseEntity assignCarToCustomer(String email, String licencePlate);
    public List<Car> getListCarsByCustomerId(Long customerId);
    public Customer getCustomerByCar(String licencePlate);
}
