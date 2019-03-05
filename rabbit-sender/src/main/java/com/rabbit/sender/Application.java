/**
 *
 */
package com.rabbit.sender;

import com.rabbit.sender.messages.ReceiverBindings;
import com.rabbit.sender.messages.SenderBindings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cbezmen
 */
@SpringBootApplication
@EnableScheduling
@EnableBinding(value = {SenderBindings.class, ReceiverBindings.class})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
