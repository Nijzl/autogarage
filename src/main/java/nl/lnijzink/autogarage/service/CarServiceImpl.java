package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.reposit.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repos;

    public CarServiceImpl(CarRepository repos){this.repos = repos;}

    @Override
    public long createCar(CarDto adto){
        Car a = new Car(adto.getLicencePlate(), adto.getModel(), adto.getYear());
        repos.save(a);
        return a.getId();
    }

    @Override
    public CarDto getCar(long id){
        Car p = repos.findById(id).get();
        return new CarDto(p.getId(), p.getLicencePlate(), p.getModel(), p.getYear());
    }

    @Override
    public List<CarDto> getCars(){
        ArrayList<CarDto> pList = new ArrayList<>();
        repos.findAll().forEach((p) -> pList.add(new CarDto(p.getId(), p.getLicencePlate(), p.getModel(), p.getYear()
        )));
        return pList;
    }
}
