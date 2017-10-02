/**
 * 
 */
package com.rabbit.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cbezmen
 *
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
