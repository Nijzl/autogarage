package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.model.Appointment;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CarRepository;
import nl.lnijzink.autogarage.reposit.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository mockCarRepository;
    @Mock
    private CustomerRepository mockCustomerRepository;

    private CarServiceImpl carServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        carServiceImplUnderTest = new CarServiceImpl(mockCarRepository, mockCustomerRepository);
    }

    @Test
    void testGetCars() {
        final List<Car> cars = List.of(new Car("licencePlate", "brand", "model", 2020));
        when(mockCarRepository.findAll()).thenReturn(cars);
        final List<CarDto> result = carServiceImplUnderTest.getCars();
    }

    @Test
    void testGetCars_CarRepositoryReturnsNoItems() {
        when(mockCarRepository.findAll()).thenReturn(Collections.emptyList());
        final List<CarDto> result = carServiceImplUnderTest.getCars();
    }

    @Test
    void testGetCar() {
        final Optional<Car> car = Optional.of(new Car("licencePlate", "brand", "model", 2020));
        when(mockCarRepository.findById("id")).thenReturn(car);
        final CarDto result = carServiceImplUnderTest.getCar("licencePlate");
    }

    @Test
    void testGetCar_CarRepositoryReturnsAbsent() {
        when(mockCarRepository.findById("id")).thenReturn(Optional.empty());
        final CarDto result = carServiceImplUnderTest.getCar("licencePlate");
    }

    @Test
    void testCreateCar() {
        final CarDto adto = new CarDto("licencePlate", "brand", "model", 2020, new Customer(0L, "fullName", "address", "phoneNumber", "email", List.of(new Car("licencePlate", "brand", "model", 2020))), List.of(new Appointment("date", "time", "type")));
        final Car car = new Car("licencePlate", "brand", "model", 2020);
        when(mockCarRepository.save(any(Car.class))).thenReturn(car);
        final String result = carServiceImplUnderTest.createCar(adto);
        assertThat(result).isEqualTo("result");
        verify(mockCarRepository).save(any(Car.class));
    }

    @Test
    void testDeleteCar() {
        when(mockCarRepository.existsById("id")).thenReturn(false);
        carServiceImplUnderTest.deleteCar("licencePlate");
        verify(mockCarRepository).deleteById("id");
    }

    @Test
    void testAssignCarToCustomer() {
        final Optional<Car> car = Optional.of(new Car("licencePlate", "brand", "model", 2020));
        when(mockCarRepository.findById("id")).thenReturn(car);
        final Optional<Customer> customer = Optional.of(new Customer(0L, "fullName", "address", "phoneNumber", "email", List.of(new Car("licencePlate", "brand", "model", 2020))));
        when(mockCustomerRepository.findCustomerByEmailEquals("email")).thenReturn(customer);
        final Car car1 = new Car("licencePlate", "brand", "model", 2020);
        when(mockCarRepository.save(any(Car.class))).thenReturn(car1);
        final ResponseEntity<Object> result = carServiceImplUnderTest.assignCarToCustomer("licencePlate", "email");
        verify(mockCarRepository).save(any(Car.class));
    }

    @Test
    void testAssignCarToCustomer_CarRepositoryFindByIdReturnsAbsent() {
        when(mockCarRepository.findById("id")).thenReturn(Optional.empty());
        final Optional<Customer> customer = Optional.of(new Customer(0L, "fullName", "address", "phoneNumber", "email", List.of(new Car("licencePlate", "brand", "model", 2020))));
        when(mockCustomerRepository.findCustomerByEmailEquals("email")).thenReturn(customer);
        final Car car = new Car("licencePlate", "brand", "model", 2020);
        when(mockCarRepository.save(any(Car.class))).thenReturn(car);
        final ResponseEntity<Object> result = carServiceImplUnderTest.assignCarToCustomer("licencePlate", "email");
        verify(mockCarRepository).save(any(Car.class));
    }

    @Test
    void testAssignCarToCustomer_CustomerRepositoryReturnsAbsent() {
        final Optional<Car> car = Optional.of(new Car("licencePlate", "brand", "model", 2020));
        when(mockCarRepository.findById("id")).thenReturn(car);
        when(mockCustomerRepository.findCustomerByEmailEquals("email")).thenReturn(Optional.empty());
        final Car car1 = new Car("licencePlate", "brand", "model", 2020);
        when(mockCarRepository.save(any(Car.class))).thenReturn(car1);
        final ResponseEntity<Object> result = carServiceImplUnderTest.assignCarToCustomer("licencePlate", "email");
        verify(mockCarRepository).save(any(Car.class));
    }

}
