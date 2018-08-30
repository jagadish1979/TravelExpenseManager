package com.expenses.TravelExpenseManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Jagadish Anala
 * 
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@EnableJpaRepositories(basePackages = "com.expenses.repository") 
@EntityScan(basePackages="com.expenses.domain")
public class TravelExpenseManager {

	public static void main(String[] args) {
		SpringApplication.run(TravelExpenseManager.class, args);
	}
}
