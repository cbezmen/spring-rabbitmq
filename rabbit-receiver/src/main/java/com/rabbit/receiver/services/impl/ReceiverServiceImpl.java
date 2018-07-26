/**
 *
 */
package com.rabbit.receiver.services.impl;

import com.rabbit.receiver.messages.CustomSink;
import com.rabbit.receiver.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @author cbezmen
 */
@Service
@Slf4j
public class ReceiverServiceImpl {


    @StreamListener(target = CustomSink.INPUT)
    public void loggerSink(Car car) throws Exception {
//        if (car.getId() % 5 == 0) {
//            throw new Exception("Exception for dlq");
//        }
        log.info("Received: {}", car.toString());

    }
}
