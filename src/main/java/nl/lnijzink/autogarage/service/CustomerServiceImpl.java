package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repos;

    public CustomerServiceImpl(CustomerRepository repos){this.repos = repos;}

    @Override
    public long createCustomer(CustomerDto cdto){
        Customer c = new Customer(cdto.getFirstName(), cdto.getLastName(), cdto.getAddress(), cdto.getEmail());
        repos.save(c);
        return c.getId();
    }

    @Override
    public CustomerDto getCustomer(long id){
        Customer p = repos.findById(id).get();
        return new CustomerDto(p.getId(), p.getFirstName(), p.getLastName(), p.getAddress(), p.getEmail());
    }

    @Override
    public List<CustomerDto> getCustomers(){
        ArrayList<CustomerDto> pList = new ArrayList<>();
        repos.findAll().forEach((p) -> pList.add(new CustomerDto(p.getId(), p.getFirstName(),
                p.getLastName(), p.getAddress(), p.getEmail()
               )));
        return pList;
    }
}
