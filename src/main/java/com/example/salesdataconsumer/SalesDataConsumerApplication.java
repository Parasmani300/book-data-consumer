package com.example.salesdataconsumer;

import com.example.salesdataconsumer.config.RootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RootConfig.class})
public class SalesDataConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesDataConsumerApplication.class, args);
	}

}
