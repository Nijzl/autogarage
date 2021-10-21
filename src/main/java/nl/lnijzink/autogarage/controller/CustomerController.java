package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    protected CustomerController(CustomerService service){this.customerService = service;}

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable long id){
        return customerService.getCustomer(id);
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
        customerService.createCustomer(cdto);
        return "CustomerDisplay";
    }

    @GetMapping("/list")
    public List<CustomerDto> getCustomers() {
        var customers = customerService.getCustomers();
        return customers; //form maken
    }
    @GetMapping("/{id}/cars")
    public String getListCarsByCustomerId(@PathVariable("id") Long customerId){
        customerService.getListCarsByCustomerId(customerId);
        return "CarsByCustomerId";
    }

    @GetMapping("/car/{id}/customer")
    public String getCustomerByCar(@PathVariable("id") Long id, Model model){
        var customer = customerService.getCustomerByCar(id);
        model.addAttribute("customer", customer);
        return "CustomerByCarId";
    }

    @PostMapping("/{id}/car")
    public ResponseEntity<String> assignCarToCustomer(@PathVariable("id") Long carId,@RequestBody Long customerId){
        customerService.assignCarToCustomer(customerId, carId);
        return ResponseEntity.ok("Auto toegeschreven aan klant");
    }
}
