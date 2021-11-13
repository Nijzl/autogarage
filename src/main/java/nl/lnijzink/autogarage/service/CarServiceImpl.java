package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.reposit.CarRepository;
import nl.lnijzink.autogarage.reposit.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public CarServiceImpl(CarRepository carRepository, CustomerRepository customerRepository){this.carRepository = carRepository; this.customerRepository = customerRepository;}

    @Override
    public List<CarDto> getCars(){
        ArrayList<CarDto> pList = new ArrayList<>();
        carRepository.findAll().forEach((p) -> pList.add(new CarDto(p.getLicencePlate(), p.getBrand(), p.getModel(),
                p.getYear()
        )));
        return pList;
    }

    @Override
    public CarDto getCar(String licencePlate){
        Car p = carRepository.findById(licencePlate).get();
        return new CarDto(p.getLicencePlate(), p.getBrand(), p.getModel(), p.getYear());
    }

    @Override
    public String createCar(CarDto adto){
        Car a = new Car(adto.getLicencePlate(), adto.getBrand(), adto.getModel(), adto.getYear());
        carRepository.save(a);
        return a.getLicencePlate();
    }

    @Override
    public void deleteCar(String licencePlate){
        boolean exists = carRepository.existsById(licencePlate);
        if(exists){
            carRepository.deleteById(licencePlate);
        }
    }

    @Override
    public ResponseEntity<Object> assignCarToCustomer(String email, String licencePlate) {
        var optionalCustomer = customerRepository.findCustomerByEmailEquals(email);
        var optionalCar = carRepository.findById(licencePlate);

        if ( optionalCustomer.isPresent() && optionalCar.isPresent()) {
            var customer = optionalCustomer.get();
            var car = optionalCar.get();
            car.setOwner(customer);
            carRepository.save(car);
        }
        //exception op later moment toevoegen
        return ResponseEntity.ok("Car added to customer.");
    }



}
