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

import com.rabbit.sender.Application;

/**
 * @author cbezmen
 *
 */
@Service
public class SenderServiceImpl {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private int dots = 0;
	private int count = 0;

	private static final Logger LOG = LoggerFactory.getLogger(SenderServiceImpl.class);

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		final StringBuilder builder = new StringBuilder("Hello");
		if (dots++ == 3) {
			dots = 1;
		}
		for (int i = 0; i < dots; i++) {
			builder.append('.');
		}
		builder.append(Integer.toString(++count));
		final String message = builder.toString();

		rabbitTemplate.convertAndSend(Application.RABBIT_QUEUE, message);
		LOG.info(String.format("%s queue with message : %s", Application.RABBIT_QUEUE, message));
	}

}
