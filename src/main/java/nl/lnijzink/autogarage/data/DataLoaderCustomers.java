package nl.lnijzink.autogarage.data;

import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CarRepository;
import org.springframework.boot.CommandLineRunner;

public class DataLoaderCustomers implements CommandLineRunner {

    private final CarRepository carRepository;

    public DataLoaderCustomers(CarRepository carRepository){this.carRepository = carRepository;}

    @Override
    public void run(String...args) {
        Car car1 = new Car("49-STJ-4", "Audi", "A2", 2020);
        Car car2 = new Car("DP-FL-21", "BMW", "X3", 2020);
        Car car3 = new Car("15-HK-GG", "Mercedes", "Benz GLC", 2020);


        Customer customer1 = new Customer("Mark Fishbach", "Somewhere 1", "markefishbach@gmail.com", "06-12345678");
        Customer customer2 = new Customer("Mia Ang", "Somewhere 2", "miaang@gmail.com", "06-87654321");
        Customer customer3 = new Customer("Sean McLoughlin", "Somewhere 3", "seanwmcloughlin@gmail.com", "06-23456789");


        car1.setOwner(customer1);
        car1 = carRepository.save(car1);
        car2.setOwner(customer2);
        car2 = carRepository.save(car2);
        car3.setOwner(customer3);
        car3 = carRepository.save(car3);
    }

}
