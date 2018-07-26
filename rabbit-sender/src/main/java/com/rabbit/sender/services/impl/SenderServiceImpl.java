/**
 *
 */
package com.rabbit.sender.services.impl;

import com.rabbit.sender.messages.SingleSinkSource;
import com.rabbit.sender.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cbezmen
 */
@Service
@Slf4j
public class SenderServiceImpl {


    AtomicInteger counter = new AtomicInteger();

    @Autowired
    private SingleSinkSource singleSinkSource;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendCar() {
        int id = counter.incrementAndGet();
        Car car = new Car(id, "car-" + id);
        log.info("sending {}", car.toString());

        singleSinkSource.myOutputOne().send(MessageBuilder.withPayload(car).build());

    }


}


