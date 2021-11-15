package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CarRepository;
import nl.lnijzink.autogarage.service.CarService;
import nl.lnijzink.autogarage.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CustomerService customerService;
    private final CarRepository carRepository;

    protected CarController(CarService carService, CustomerService customerService, CarRepository carRepository) {
        this.carService = carService;
        this.customerService = customerService;
        this.carRepository = carRepository;}

    @GetMapping("/")
    public String getCars(Model model) {
        var cars = carService.getCars();
        model.addAttribute("listOfCars", cars);
        return "CarsList";
    }

    @GetMapping("/create")
    public String createCar(Model model) {
        model.addAttribute("Car", new CarDto());
        return "CarForm";
    }

    @PostMapping("/create")
    public String createCar(@Valid @ModelAttribute("Car") CarDto adto,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "CarForm";
        }
        carService.createCar(adto);
        return "CarDisplay";
    }

    @GetMapping("/update/{licencePlate}")
    public String updateCar(@PathVariable("licencePlate") String licencePlate, Model model) {
        Car car = carRepository.findById(licencePlate)
                .orElseThrow(() -> new IllegalArgumentException("Invalid licence plate:" + licencePlate));
        model.addAttribute("car", car);
        return "CarUpdate";
    }

    @PostMapping("/update/{licencePlate}")
    public String updateCar(@PathVariable("licencePlate") String licencePlate, @Valid Car car,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            car.setLicencePlate(licencePlate);
            return "CarUpdate";
        }
        carRepository.save(car);
        return "redirect:/index";
    }

    @GetMapping("/delete/{licencePlate}")
    public String deleteCar(@PathVariable("licencePlate") String licencePlate, Model model) {
        Car car = carRepository.findById(licencePlate)
                .orElseThrow(() -> new IllegalArgumentException("Invalid licence plate:" + licencePlate));
        carRepository.delete(car);
        return "redirect:/home";
    }

//    @GetMapping("/{id}/customer")
//    public String getCustomerByCar(@PathVariable("id") Long id, Model model){
//        var customer = carService.getCustomerByCar(id);
//        model.addAttribute("customer", customer);
//        return "CustomerByCarId";
//    }

/*    @GetMapping("/customer")
    public String assignCarToCustomer(Model model){
        model.addAttribute("Customer", new CustomerDto());
        model.addAttribute("Car", new CarDto());
        return "LinkCustomerAndCar";
    }

    @PostMapping("/customer")
    public String assignCarToCustomer(@RequestParam String email,
                                                      @RequestParam String licencePlate){
        carService.assignCarToCustomer(email, licencePlate);
        return "LinkCustomerAndCarSuccessful";
    }*/

    @GetMapping("/customer")
    public String assignCarToCustomer(Model model){
        model.addAttribute("Customer", new CustomerDto());
        model.addAttribute("Car", new CarDto());
        return "LinkCustomerAndCar";
    }

    @PostMapping("/customer")
    public String assignCarToCustomer(@RequestParam String email,
                                      @RequestParam String licencePlate) {
        carService.assignCarToCustomer(email, licencePlate);
        return "LinkCustomerAndCarSuccessful";
    }

}