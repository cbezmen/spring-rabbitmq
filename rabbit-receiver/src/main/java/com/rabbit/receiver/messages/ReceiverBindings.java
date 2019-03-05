package com.rabbit.receiver.messages;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author canbezmen
 */
public interface ReceiverBindings {

    String INPUT = "carReceiver";

    @Input(value = INPUT)
    SubscribableChannel customInput();

}
