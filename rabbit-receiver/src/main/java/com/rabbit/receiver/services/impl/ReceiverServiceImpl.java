/**
 * 
 */
package com.rabbit.receiver.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.rabbit.receiver.configurations.RabbitConfiguration;
import com.rabbit.receiver.exceptions.QueueException;
import com.rabbit.receiver.models.Car;

/**
 * @author cbezmen
 *
 */
//@Service
public class ReceiverServiceImpl {

	private static final Logger LOG = LoggerFactory.getLogger(ReceiverServiceImpl.class);

	int i = 0;

	@RabbitListener(queues = RabbitConfiguration.RABBIT_QUEUE)
	public void receiveString(final String data) {
		final StopWatch watch = new StopWatch();
		watch.start();
		LOG.info("Received data is: {}", data);
		i++;

		if (i % 5 == 0) {
			throw new QueueException("Error in " + data);
		}

		doWork(data);
		watch.stop();
		LOG.info("Done in " + watch.getTotalTimeSeconds() + "s");
	}

	@RabbitListener(queues = RabbitConfiguration.ANOTHER_RABBIT_QUEUE)
	public void receiveObject(final Car car) {
		printLog("Another", car);
	}

	@RabbitListener(queues = RabbitConfiguration.THIRD_RABBIT_QUEUE)
	public void receiveThirdQueueObject(final Car car) {
		printLog("Third", car);
	}

	private void printLog(final String queueName, final Car car) {
		LOG.info("{} Queue, car with name {} id: {}", queueName, car.getName(), car.getId());
	}

	private void doWork(final String in) {
		for (final char ch : in.toCharArray()) {
			if (ch == '.') {
				try {
					Thread.sleep(1000);
				} catch (final InterruptedException e) {
					LOG.error(e.getMessage());
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
