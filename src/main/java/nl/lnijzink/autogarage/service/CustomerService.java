package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    public List<CustomerDto> getCustomers();
    public CustomerDto getCustomer(Long id);
    public Long createCustomer(CustomerDto cdto);
    public void deleteCustomer(Long id);

}
