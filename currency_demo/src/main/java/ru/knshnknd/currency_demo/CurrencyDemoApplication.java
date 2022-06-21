package ru.knshnknd.currency_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.knshnknd.currency_demo.service.CurrencyService;

@SpringBootApplication
public class CurrencyDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyDemoApplication.class, args);
	}
}
