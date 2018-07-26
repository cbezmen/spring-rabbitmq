/**
 *
 */
package com.rabbit.receiver;

import com.rabbit.receiver.messages.CustomSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author cbezmen
 */
@SpringBootApplication
@Slf4j
@EnableBinding(CustomSink.class)
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
