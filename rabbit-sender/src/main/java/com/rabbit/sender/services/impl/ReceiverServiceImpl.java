package com.rabbit.sender.services.impl;

import com.rabbit.sender.messages.ReceiverBindings;
import com.rabbit.sender.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @author canbezmen
 */
@Service
@Slf4j
public class ReceiverServiceImpl {


    @StreamListener(target = ReceiverBindings.INPUT)
    public void loggerSink(Car car) throws Exception {
//        if (car.getId() % 5 == 0) {
//            throw new Exception("Exception for dlq");
//        }
        Thread.sleep(5000);
        log.info("Received: {}", car.toString());

    }
}
