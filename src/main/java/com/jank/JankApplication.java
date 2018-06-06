package com.jank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jank")
public class JankApplication {

	public static void main(String[] args) {
		SpringApplication.run(JankApplication.class, args);
	}
}
