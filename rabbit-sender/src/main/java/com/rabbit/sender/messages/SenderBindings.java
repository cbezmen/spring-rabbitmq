package com.rabbit.sender.messages;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author canbezmen
 */
public interface SenderBindings {

    String OUTPUT_ONE = "carSender";

    @Output(value = OUTPUT_ONE)
    MessageChannel myOutputOne();
}
