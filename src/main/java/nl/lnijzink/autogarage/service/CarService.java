package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.model.Customer;

import java.util.List;

public interface CarService {
    public String createCar(CarDto adto);
    public CarDto getCar(String licencePlate);
    public List<CarDto> getCars();

}
