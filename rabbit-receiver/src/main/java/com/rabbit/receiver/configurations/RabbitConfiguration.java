/**
 * 
 */
package com.rabbit.receiver.configurations;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cbezmen
 *
 */
@Configuration
public class RabbitConfiguration {

	public static final String RABBIT_QUEUE = "rabbit.queue";
	public static final String ANOTHER_RABBIT_QUEUE = "another.rabbit.queue";
	public static final String THIRD_RABBIT_QUEUE = "third.rabbit.queue";

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public AmqpTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
