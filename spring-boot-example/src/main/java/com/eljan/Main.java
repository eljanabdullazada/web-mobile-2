package com.eljan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/greet")
    public GreetResponse greet(){
        GreetResponse response = new GreetResponse("hello",
                List.of("Java", "Js", "Python"),
                new Person("Eljan", 20, 30_000));
        return response;
    }

    record Person(String name, int age, double savings){}
    record GreetResponse(
            String greet,
            List<String> favProgrammingLanguages,
            Person person

    ){}
}
