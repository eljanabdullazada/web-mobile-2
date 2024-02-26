package com.eljan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("Hello");
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name, String email, Integer age
    ){}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@RequestBody NewCustomerRequest request,@PathVariable("customerId") Integer id){
        Customer customer = new Customer();
        customer.setID(id);
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        GreetResponse response = new GreetResponse("hello",
//                List.of("Java", "Js", "Python"),
//                new Person("Eljan", 20, 30_000));
//        return response;
//    }
//
//    record Person(String name, int age, double savings){}
//    record GreetResponse(
//            String greet,
//            List<String> favProgrammingLanguages,
//            Person person
//
//    ){}
}
