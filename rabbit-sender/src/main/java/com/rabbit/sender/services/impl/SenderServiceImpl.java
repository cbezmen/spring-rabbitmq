package com.rabbit.sender.services.impl;

import com.rabbit.sender.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
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
    private StreamBridge streamBridge;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendCar() {
        int id = counter.incrementAndGet();
        Car car = new Car(id, "car-" + id);
        log.info("sending {}", car);
        streamBridge.send("carSender", car);

    }

}


