package com.rabbit.sender.services.impl;

import com.rabbit.sender.messages.SingleSinkSource;
import com.rabbit.sender.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author canbezmen
 */
@Slf4j
@EnableBinding(value = SingleSinkSource.class)
public class ReceiverServiceImpl {

    @StreamListener(target = SingleSinkSource.INPUT)
    public void loggerSink(Car car) {
        log.info("Received: {}", car.toString());
    }

}
