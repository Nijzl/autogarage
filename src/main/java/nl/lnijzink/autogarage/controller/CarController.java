package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    protected CarController(CarService service){this.carService = service;}

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable long id){
        return carService.getCar(id);
    }

    @GetMapping("/create")
    public String createPart(Model model){
        model.addAttribute("Car", new CarDto());
        return "CarForm";
    }

    @PostMapping("/create")
    public String createCar(@Valid @ModelAttribute("Car") CarDto adto,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "CarForm";
        }
        carService.createCar(adto);
        return "CarDisplay";
    }

//    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<CarDto> getCars(){
//        return service.getCars();
//    }

    @GetMapping("/list")
    public List<CarDto> getCars(Model model) {
    var cars = carService.getCars();
        model.addAttribute("cars", cars);
        return cars; //form maken
}
//    @GetMapping("/{id}/customer")
//    public String getCustomerByCar(@PathVariable("id") Long id, Model model){
//        var customer = carService.getCustomerByCar(id);
//        model.addAttribute("customer", customer);
//        return "CustomerByCarId";
//    }
}