package com.keyclock;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping(path = "/")
	public String index() {
		return "external";
	}

	@PreAuthorize("hasRole('user')")
	// @Secured({ "ROLE_USER" })
	@GetMapping(path = "/customers")
	public Iterable<Customer> customers(Principal principal) {
		Iterable<Customer> customers = customerRepository.findAll();
		System.out.println("principal.getName():" + principal.getName());
		return customers;
	}

	@PreAuthorize("hasRole('special_user') or hasRole('admin')")
	// @Secured({ "ROLE_SPECIAL_USER", "ROLE_ADMIN" })
	@GetMapping(path = "/customers/{id}")
	public Customer getCustomerById(Principal principal, @PathVariable("id") long id) {
		Optional<Customer> optional = customerRepository.findById(id);
		// System.out.println("principal.getName():" + principal.getName());
		if (optional.isPresent()) {
			return optional.get();
		}
		return new Customer();
	}

	@PreAuthorize("hasRole('admin')")
	// @Secured({ "ROLE_ADMIN" })
	@GetMapping(path = "/customers/getbyids/{ids}")
	public Iterable<Customer> getCustomerByIds(Principal principal, @PathVariable("ids") List<Long> ids) {
		Iterable<Customer> iterable = customerRepository.findAllById(ids);
		return iterable;
	}

}
