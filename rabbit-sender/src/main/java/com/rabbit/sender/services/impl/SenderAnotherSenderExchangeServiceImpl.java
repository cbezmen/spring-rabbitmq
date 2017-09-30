package com.rabbit.sender.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rabbit.sender.Application;
import com.rabbit.sender.models.Car;

@Service
public class SenderAnotherSenderExchangeServiceImpl {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	private Integer exchangeId = 9000;
	private static final Logger LOG = LoggerFactory.getLogger(AnotherSenderServiceImpl.class);
	
	@Scheduled(fixedDelay = 500, initialDelay = 500)
	public void sendExchange() {
		rabbitTemplate.convertAndSend(Application.RABBIT_THIRD_RABBIT_EXCHANGE,Application.FANOUT_RK, new Car(exchangeId, "Car Name"));

		LOG.info(String.format("%s exhange with car id %d", Application.RABBIT_THIRD_RABBIT_EXCHANGE, exchangeId));
		exchangeId++;
	}
	
}
