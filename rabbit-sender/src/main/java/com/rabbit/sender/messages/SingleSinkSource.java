package com.rabbit.sender.messages;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author canbezmen
 */
public interface SingleSinkSource {

    String INPUT = "customInput";

    @Input(value = INPUT)
    SubscribableChannel customInput();


    String OUTPUT_ONE = "customOutputOne";


    @Output(value =  OUTPUT_ONE)
    MessageChannel myOutputOne();
}
