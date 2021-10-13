package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    public long createCustomer(CustomerDto cdto);
    public CustomerDto getCustomer(long id);
    public List<CustomerDto> getCustomers();
}
