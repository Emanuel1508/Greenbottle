package com.example.greenbottle;

import com.example.greenbottle.customer.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication

public class GreenbottleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenbottleApplication.class, args);
	}

}
