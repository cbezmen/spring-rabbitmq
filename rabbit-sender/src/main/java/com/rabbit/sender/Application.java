/**
 * 
 */
package com.rabbit.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cbezmen
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
