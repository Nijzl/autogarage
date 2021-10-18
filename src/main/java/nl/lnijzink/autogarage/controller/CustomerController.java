package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    protected CustomerController(CustomerService service){this.service = service;}

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable long id){
        return service.getCustomer(id);

    }

    @GetMapping("/create")
    public String createCustomer(Model model){
        model.addAttribute("Customer", new CustomerDto());
        return "CustomerForm";
    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute("Customer") CustomerDto cdto,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "CustomerForm";
        }
        service.createCustomer(cdto);
        return "CustomerDisplay";
    }

    @PostMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getCustomers(){
        return service.getCustomers();
    }
}
