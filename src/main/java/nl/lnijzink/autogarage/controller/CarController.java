package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.service.CarService;
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

    protected CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String getCars(Model model) {
        var cars = carService.getCars();
        model.addAttribute("listOfCars", cars);
        return "CarsList";
    }

    @GetMapping("/{licencePlate}")
    public CarDto getCar(@PathVariable String licencePlate) {
        return carService.getCar(licencePlate);
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

    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable("carId") String licencePlate){
        carService.deleteCar(licencePlate);
        return "CarDeleteDisplay";
    }

//    @GetMapping("/{id}/customer")
//    public String getCustomerByCar(@PathVariable("id") Long id, Model model){
//        var customer = carService.getCustomerByCar(id);
//        model.addAttribute("customer", customer);
//        return "CustomerByCarId";
//    }

    @GetMapping("/customer")
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
    }


}