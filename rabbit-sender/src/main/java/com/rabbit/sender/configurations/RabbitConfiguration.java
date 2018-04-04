/**
 * 
 */
package com.rabbit.sender.configurations;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbit.sender.enums.QueueSuffixEnumeration;

/**
 * @author cbezmen
 *
 */
//@Configuration
public class RabbitConfiguration {

	public static final String RABBIT_QUEUE = "rabbit.queue";
	public static final String ANOTHER_RABBIT_QUEUE = "another.rabbit.queue";
	public static final String THIRD_RABBIT_QUEUE = "third.rabbit.queue";
	public static final String RABBIT_THIRD_RABBIT_EXCHANGE = "rabbit.third.rabbit.exchange";

	public static final String RABBIT_QUEUE_RK = "exchange_rabbit";
	public static final String ANOTHER_RABBIT_QUEUE_RK = "exchange_another_rabbit";
	public static final String THIRD_RABBIT_QUEUE_RK = "exchange_third_rabbit";
	public static final String FANOUT_RK = "exchange_fanout";

	@Bean
	public List<Queue> createRabbitQueue() {
		return createQueueList(RABBIT_QUEUE);
	}

	@Bean
	public List<Queue> createAnotherRabbitQueue() {
		return createQueueList(ANOTHER_RABBIT_QUEUE);
	}

	@Bean
	public List<Queue> createThirdRabbitQueue() {
		return createQueueList(THIRD_RABBIT_QUEUE);
	}

	@Bean
	public Exchange exchange() {
		return new FanoutExchange(RABBIT_THIRD_RABBIT_EXCHANGE);
	}

	@Bean
	public List<Binding> bind() {
		return Arrays.asList(
				new Binding(THIRD_RABBIT_QUEUE, DestinationType.QUEUE, RABBIT_THIRD_RABBIT_EXCHANGE, FANOUT_RK, null),
				new Binding(ANOTHER_RABBIT_QUEUE, DestinationType.QUEUE, RABBIT_THIRD_RABBIT_EXCHANGE, FANOUT_RK,
						null));
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public AmqpTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	protected List<Queue> createQueueList(final String queueName) {
		return Arrays.asList(createQueue(queueName), createErrorQueue(queueName), createSkipQueue(queueName));
	}

	private Queue createQueue(final String queueName) {
		return new Queue(queueName.concat(QueueSuffixEnumeration.ERROR_SUFFIX.getSuffix()));
	}

	private Queue createErrorQueue(final String queueName) {
		return new Queue(queueName.concat(QueueSuffixEnumeration.ERROR_SUFFIX.getSuffix()));
	}

	private Queue createSkipQueue(final String queueName) {
		return new Queue(queueName.concat(QueueSuffixEnumeration.SKIP_SUFFIX.getSuffix()));
	}
}
