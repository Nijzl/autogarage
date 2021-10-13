package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    protected CustomerController(CustomerService service){this.service = service;}

    @GetMapping("/customer/id")
    public CustomerDto getCustomer(@PathVariable long id){
        return service.getCustomer(id);
    }

    @PostMapping("/customer/create")
    @ResponseStatus(HttpStatus.CREATED)
    public long createCustomer(@RequestBody CustomerDto cdto){return service.createCustomer(cdto);}

    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getCustomers(){
        return service.getCustomers();
    }
}
