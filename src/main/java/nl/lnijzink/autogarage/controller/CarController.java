package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.reposit.CarRepository;
import nl.lnijzink.autogarage.reposit.CustomerRepository;
import nl.lnijzink.autogarage.service.CarService;
import nl.lnijzink.autogarage.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CustomerService customerService;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    protected CarController(CarService carService, CustomerService customerService, CarRepository carRepository,
                            CustomerRepository customerRepository) {
        this.carService = carService;
        this.customerService = customerService;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }


    // Get List of Cars
    @GetMapping("/")
    public String getCars(Model model) {
        var cars = carService.getCars();
        model.addAttribute("listOfCars", cars);
        return "CarsList";
    }

    // Get Single Car
    @GetMapping("/view/{licencePlate}")
    public String getCar(@PathVariable("licencePlate") String licencePlate, Model model) {
        CarDto car = carService.getCar(licencePlate);
        model.addAttribute("car", car);
        return "CarGet";
    }

    // Create New Car
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

    // Update Car
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
        return "redirect:/cars/";
    }

    // Delete Car
    @GetMapping("/delete/{licencePlate}")
    public String deleteCar(@PathVariable("licencePlate") String licencePlate, Model model) {
        Car car = carRepository.findById(licencePlate)
                .orElseThrow(() -> new IllegalArgumentException("Invalid licence plate:" + licencePlate));
        carRepository.delete(car);
        return "redirect:/cars/";
    }

    // Assign Car to Customer
    @GetMapping("/customer")
    public String assignCarToCustomer(Model model){
        model.addAttribute("Car", new CarDto());
        model.addAttribute("Customer", new CustomerDto());
        model.addAttribute("listOfCars", carRepository.findAll());
        model.addAttribute("listOfCustomers", customerRepository.findAll());
        return "LinkCustomerAndCar";
    }

    @PostMapping("/customer")
    public String assignCarToCustomer(@RequestParam String licencePlate,
                                      @RequestParam String email) {
        carService.assignCarToCustomer(licencePlate, email);
        return "LinkCustomerAndCarSuccessful";
    }

}