/**
 * 
 */
package com.rabbit.receiver;

import com.rabbit.receiver.messages.CustomSink;
import com.rabbit.receiver.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author cbezmen
 *
 */
@SpringBootApplication
@Slf4j
@EnableBinding(CustomSink.class)
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@StreamListener(target = Sink.INPUT)
//	public void loggerSink(SinkTimeInfo sinkTimeInfo) {
//		log.info("Received: " + sinkTimeInfo.toString());
//	}

	@StreamListener(target = CustomSink.INPUT)
	public void loggerSink(Car	car) {
		log.info("Received: {}", car.toString());
	}

}
