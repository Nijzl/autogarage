package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CarRepository;
import nl.lnijzink.autogarage.reposit.CustomerRepository;
import nl.lnijzink.autogarage.storage.StorageException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CarRepository carRepository){this.customerRepository = customerRepository; this.carRepository = carRepository;}

    @Override
    public List<CustomerDto> getCustomers(){
        ArrayList<CustomerDto> pList = new ArrayList<>();
        customerRepository.findAll().forEach((p) -> pList.add(new CustomerDto(p.getId(), p.getFullName(),
                p.getAddress(), p.getPhoneNumber(), p.getEmail()
        )));
        return pList;
    }

    @Override
    public CustomerDto getCustomer(Long id){
        Customer p = customerRepository.findById(id).get();
        return new CustomerDto(p.getId(), p.getFullName(), p.getAddress(), p.getPhoneNumber(), p.getEmail());
    }

    @Override
    public Long createCustomer(CustomerDto cdto){
        Customer c = new Customer();
        c.setFullName(cdto.getFullName());
        c.setAddress(cdto.getAddress());
        c.setPhoneNumber(cdto.getPhoneNumber());
        c.setEmail(cdto.getEmail());
        customerRepository.save(c);
        return c.getId();
    }

    @Override
    public void deleteCustomer(Long id){
        boolean exists = customerRepository.existsById(id);
        if(exists){
            customerRepository.deleteById(id);
        }
    }

    @Override
    public Customer getCustomerByCar(String licencePlate) {
        var optionalCar = carRepository.findById(licencePlate);

        if(optionalCar.isPresent() && optionalCar.get().getOwner() != null) {
            return optionalCar.get().getOwner();
        }
        throw new StorageException("TODO");
    }

    @Override
    public List<Car> getListCarsByCustomerId(Long customerId) {
        return null; //?
    }

}
