package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;

import java.util.List;

public interface CustomerService {

    public List<CustomerDto> getCustomers();
    public CustomerDto getCustomer(Long id);
    public Long createCustomer(CustomerDto cdto);
    public void deleteCustomer(Long id);
    public List<Car> getListCarsByCustomerId(Long customerId);
    public Customer getCustomerByCar(String licencePlate);

}
