package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @GetMapping("/greet")
    public GreetResponde greet() {
        GreetResponde responde =  new GreetResponde("Hello",
                List.of("Java", "Golang", "JavaScript"),
                new Person("Alex", 20, 30_000));
        return responde;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    record Person(String name, Integer age, double save){}
    record GreetResponde(String greet,
                         List<String> favProgramingLanguages,
                         Person person) {}

//    class GreetResponde {
//        private final String greet;
//
//
//        GreetResponde(String greet) {
//            this.greet = greet;
//        }
//
//        public String getGreet() {
//            return greet;
//        }
//
//        @Override
//        public String toString() {
//            return "GreetResponde{" +
//                    "greet='" + greet + '\'' +
//                    '}';
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetResponde that = (GreetResponde) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(greet);
//        }
//    }
}
