package nl.lnijzink.autogarage.data;

import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.CarRepository;
import org.springframework.boot.CommandLineRunner;

public class DataLoaderCustomers implements CommandLineRunner {

    private final CarRepository carRepository;

    public DataLoaderCustomers(CarRepository carRepository){this.carRepository = carRepository;}

    @Override
    public void run(String...args) throws Exception{
        Car car1 = new Car("111-AAA-11", "Audi", "A2", 2020);
        Car car2 = new Car("222-BB-22", "BMW", "X3", 2020);
        Car car3 = new Car("333-CC-33", "Mercedes", "Benz GLC", 2020);


        Customer customer1 = new Customer("Steve Stevenson", "Somewhere 1", "steve@steve.com", "06-12345678");
        Customer customer2 = new Customer("Harry Harrisson", "Somewhere 2", "harry@harry.com", "06-87654321");
        Customer customer3 = new Customer("James Jameson", "Somewhere 3", "james@james.com", "06-23456789");


        car1.setOwner(customer1);
        car1 = carRepository.save(car1);
        car2.setOwner(customer2);
        car2 = carRepository.save(car2);
        car3.setOwner(customer3);
        car3 = carRepository.save(car3);
    }

}
