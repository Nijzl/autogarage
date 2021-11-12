package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    protected CustomerController(CustomerService service){this.customerService = service;}

    @GetMapping("/")
    public String getCustomers(Model model) {
        var customers = customerService.getCustomers();
        model.addAttribute("listOfCustomers", customers);
        return "CustomersList";
}

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

    @GetMapping("/create")
    public String createCustomer(Model model){
        model.addAttribute("Customer", new CustomerDto());
        return "CustomerForm";
    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute("Customer") CustomerDto cdto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "CustomerForm";
        }
        customerService.createCustomer(cdto);
        return "CustomerDisplay";
    }

/*    @GetMapping("/delete")
    public String deleteCustomer(){
        return "CustomerDeleteDisplay";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("customerId") Long id){
        customerService.deleteCustomer(id);
        return "CustomerDeleteDisplay";
    }*/


    @GetMapping("/{id}/cars")
    public String getListCarsByCustomerId(@PathVariable("id") Long customerId){
        customerService.getListCarsByCustomerId(customerId);
        return "CarsByCustomerId";
    }

    @GetMapping("/car/{licencePlate}/customer")
    public String getCustomerByCar(@PathVariable("licencePlate") String lincencePlate, Model model){
        var customer = customerService.getCustomerByCar(lincencePlate);
        model.addAttribute("customer", customer);
        return "CustomerByCarId";
    }

}
