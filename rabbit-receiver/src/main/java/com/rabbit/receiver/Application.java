package com.rabbit.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cbezmen
 */
@SpringBootApplication
@Slf4j
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
