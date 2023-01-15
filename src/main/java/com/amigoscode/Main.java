package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name, String email, Integer age) {    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }


    record CustomerUpdateRequest(String name,
                                 String email,
                                 Integer age) {
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable("customerId") Integer id) {

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty())
            return ResponseEntity.notFound().build();

        customer.setId(id);
        customerRepository.save(customer);

        return ResponseEntity.noContent().build();
    }

}
