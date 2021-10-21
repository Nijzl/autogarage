package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.model.Customer;

import java.util.List;

public interface CarService {
    public long createCar(CarDto adto);
    public CarDto getCar(long id);
    public List<CarDto> getCars();

}
