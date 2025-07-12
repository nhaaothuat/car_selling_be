package com.example.car_selling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarSellingApplication {

	public static void main(String[] args) {

//		System.setProperty("org.apache.tomcat.util.http.fileupload.fileCountMax", "100");
		SpringApplication.run(CarSellingApplication.class, args);
	}

}
