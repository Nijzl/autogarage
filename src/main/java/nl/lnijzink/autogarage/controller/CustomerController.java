package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CustomerRepository;
import nl.lnijzink.autogarage.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    protected CustomerController(CustomerService customerService, CustomerRepository customerRepository){
        this.customerService = customerService;
        this.customerRepository = customerRepository;}

    @GetMapping("/")
    public String getCustomers(Model model) {
        var customers = customerService.getCustomers();
        model.addAttribute("listOfCustomers", customers);
        return "CustomersList";
}

    @GetMapping("/view/{id}")
    public String getCustomer(@PathVariable("id") Long id, Model model) {
        CustomerDto customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "CustomerGet";
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

    @GetMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("customer", customer);
        return "CustomerUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @Valid Customer customer,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "CustomerUpdate";
        }
        customerRepository.save(customer);
        return "redirect:/customers/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        customerRepository.delete(customer);
        return "redirect:/customers/";
    }

    @GetMapping("/{id}/cars")
    public String getListCarsByCustomerId(@PathVariable("id") Long customerId){
        customerService.getListCarsByCustomerId(customerId);
        return "CarsByCustomerId";
    }

    @GetMapping("/car/{licencePlate}/customer")
    public String getCustomerByCar(@PathVariable("licencePlate") String licencePlate, Model model){
        var customer = customerService.getCustomerByCar(licencePlate);
        model.addAttribute("customer", customer);
        return "CustomerByCarId";
    }

}
