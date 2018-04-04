/**
 * 
 */
package com.rabbit.sender.services.impl;

import com.rabbit.sender.messages.SingleSinkSource;
import com.rabbit.sender.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cbezmen
 *
 */
@Service
@Slf4j
//@EnableBinding(value = SingleSinkSource.class)
public class SenderServiceImpl {


	@Autowired
	private SingleSinkSource singleSinkSource;

	AtomicInteger counter = new AtomicInteger();

//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//
//	private int dots = 0;
//	private int count = 0;
//
//	private static final Logger LOG = LoggerFactory.getLogger(SenderServiceImpl.class);
//
//	@Scheduled(fixedDelay = 1000, initialDelay = 500)
//	public void send() {
//		final StringBuilder builder = new StringBuilder("Hello");
//		if (dots++ == 3) {
//			dots = 1;
//		}
//		for (int i = 0; i < dots; i++) {
//			builder.append('.');
//		}
//		builder.append(Integer.toString(++count));
//		final String message = builder.toString();
//
//		rabbitTemplate.convertAndSend(RabbitConfiguration.RABBIT_QUEUE, message);
//		LOG.info("{} queue with message : {} ", RabbitConfiguration.RABBIT_QUEUE, message);
//	}

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void sendCar(){
		int id = counter.incrementAndGet();
		Car car = new Car(id, "car-" + id);
		log.info("sending {}", car.toString());

		singleSinkSource.myOutputOne().send(MessageBuilder.withPayload(car).build());

	}



	public class TimeInfo{

		private String time;
		private String label;

		public TimeInfo(String time, String label) {
			super();
			this.time = time;
			this.label = label;
		}

		public String getTime() {
			return time;
		}

		public String getLabel() {
			return label;
		}

	}

}


