package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CarDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {
    public String createCar(CarDto adto);
    public CarDto getCar(String licencePlate);
    public List<CarDto> getCars();
    public ResponseEntity assignCarToCustomer(String email, String licencePlate);

}
