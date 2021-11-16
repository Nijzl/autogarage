package nl.lnijzink.autogarage.data;

import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CarRepository;
import nl.lnijzink.autogarage.reposit.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderCustomers implements CommandLineRunner {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public DataLoaderCustomers(CarRepository carRepository, CustomerRepository customerRepository){this.carRepository = carRepository; this.customerRepository = customerRepository;}

    @Override
    public void run(String...args) {

        Customer customer1 = new Customer("Mark Fishbach", "Somewhere 1", "06-12345678", "markefishbach@gmail.com");
        Customer customer2 = new Customer("Mia Ang", "Somewhere 2", "06-87654321", "miaang@gmail.com");
        Customer customer3 = new Customer("Sean McLoughlin", "Somewhere 3", "06-23456789", "seanwmcloughlin@gmail.com");

        customer1 = customerRepository.save(customer1);
        customer2 = customerRepository.save(customer2);
        customer3 = customerRepository.save(customer3);

        Car car1 = new Car("49-STJ-4", "Audi", "A2", 2020);
        Car car2 = new Car("DP-FL-21", "BMW", "X3", 2020);
        Car car3 = new Car("15-HK-GG", "Mercedes", "Benz GLC", 2020);

        car1.setOwner(customer1);
        car1 = carRepository.save(car1);
        car2.setOwner(customer2);
        car2 = carRepository.save(car2);
        car3.setOwner(customer3);
        car3 = carRepository.save(car3);
    }

}
