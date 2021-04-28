/**
 *
 */
package com.rabbit.receiver.services.impl;

import com.rabbit.receiver.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * @author cbezmen
 */
@Service
@Slf4j
public class ReceiverServiceImpl {

    @Bean
    public Consumer<Car> carReceiver() {
        return car -> {
            if (car.getId() % 5 == 0) {
                log.info("Error for : {}", car);
                throw new RuntimeException("Exception for dlq");
            }
            log.info("Received: {}", car);
        };
    }
}
