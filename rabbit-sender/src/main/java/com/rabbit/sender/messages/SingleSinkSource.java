package com.rabbit.sender.messages;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author canbezmen
 */
public interface SingleSinkSource {

    String OUTPUT_ONE = "customOutputOne";

    @Output(value = OUTPUT_ONE)
    MessageChannel myOutputOne();
}
