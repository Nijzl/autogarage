package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService service;

    protected CarController(CarService service){this.service = service;}

    @GetMapping("/car/id")
    public CarDto getCar(@PathVariable long id){
        return service.getCar(id);
    }

    @PostMapping("/car/create")
    @ResponseStatus(HttpStatus.CREATED)
    public long createCar(@RequestBody CarDto adto){return service.createCar(adto);}

    @GetMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarDto> getCars(){
        return service.getCars();
    }
}





/*
@RestController
public class CarController {
    @Autowired
    CarRepository carrepo;

    @GetMapping("/car")
    public String hello(){
        return "this is the car page";
    }
    @PostMapping("/add/car")
    public void addCustomer(@RequestParam String licencePlate){
        Car car1 = new Car();
        car1.setLicencePlate(licencePlate);
        carrepo.save(car1);

    }
}
*/