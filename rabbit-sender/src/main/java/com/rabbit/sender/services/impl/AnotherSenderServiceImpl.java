/**
 * 
 */
package com.rabbit.sender.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rabbit.sender.configurations.RabbitConfiguration;
import com.rabbit.sender.models.Car;

/**
 * @author cbezmen
 *
 */
//@Service
public class AnotherSenderServiceImpl {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private Integer carid = 1000;

	private static final Logger LOG = LoggerFactory.getLogger(AnotherSenderServiceImpl.class);

	@Scheduled(fixedDelay = 500, initialDelay = 500)
	public void sendAnother() {
		rabbitTemplate.convertAndSend(RabbitConfiguration.ANOTHER_RABBIT_QUEUE, new Car(carid, "Car Name"));

		LOG.info("{} queue with car id {} ", RabbitConfiguration.ANOTHER_RABBIT_QUEUE, carid);
		carid++;
	}
}
