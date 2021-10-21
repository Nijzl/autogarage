package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CarRepository;
import nl.lnijzink.autogarage.reposit.CustomerRepository;
import nl.lnijzink.autogarage.storage.StorageFileNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public CarServiceImpl(CarRepository carRepository, CustomerRepository customerRepository){this.carRepository = carRepository; this.customerRepository = customerRepository;}

    @Override
    public String createCar(CarDto adto){
        Car a = new Car(adto.getLicencePlate(), adto.getModel(), adto.getYear());
        carRepository.save(a);
        return a.getLicencePlate();
    }

    @Override
    public CarDto getCar(String licencePlate){
        Car p = carRepository.findById(licencePlate).get();
        return new CarDto(p.getLicencePlate(), p.getModel(), p.getYear());
    }

    @Override
    public List<CarDto> getCars(){
        ArrayList<CarDto> pList = new ArrayList<>();
        carRepository.findAll().forEach((p) -> pList.add(new CarDto(p.getLicencePlate(), p.getModel(), p.getYear()
        )));
        return pList;
    }


}
