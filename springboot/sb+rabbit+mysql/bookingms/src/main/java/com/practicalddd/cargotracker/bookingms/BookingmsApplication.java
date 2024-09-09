package com.practicalddd.cargotracker.bookingms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.mapper")
public class BookingmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookingmsApplication.class, args);
	}
}
