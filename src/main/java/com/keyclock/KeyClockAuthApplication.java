package com.keyclock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeyClockAuthApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(KeyClockAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean hasEntries = customerRepository.findAll().iterator().hasNext();
		if (!hasEntries) {
			Customer customer1 = new Customer();
			customer1.setAddress("1111 foo blvd");
			customer1.setName("Foo Industries");
			customer1.setServiceRendered("Important services");
			customerRepository.save(customer1);

			Customer customer2 = new Customer();
			customer2.setAddress("2222 bar street");
			customer2.setName("Bar LLP");
			customer2.setServiceRendered("Important services");
			customerRepository.save(customer2);

			Customer customer3 = new Customer();
			customer3.setAddress("33 main street");
			customer3.setName("Big LLC");
			customer3.setServiceRendered("Important services");
			customerRepository.save(customer3);
		}
		System.out.println("Customers added ---------------------------------------------------------------");

	}

}
