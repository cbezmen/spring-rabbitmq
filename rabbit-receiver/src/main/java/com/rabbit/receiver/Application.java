/**
 * 
 */
package com.rabbit.receiver;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author cbezmen
 *
 */
@SpringBootApplication
public class Application {

	public static final String RABBIT_QUEUE = "rabbit.queue";
	public static final String ANOTHER_RABBIT_QUEUE = "another.rabbit.queue";
	public static final String RABBIT_ERROR_QUEUE = RABBIT_QUEUE + ".error";

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Queue createQueue() {
		return new Queue(RABBIT_QUEUE);
	}

	@Bean
	public Queue createErrorQueue() {
		return new Queue(RABBIT_ERROR_QUEUE);
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

}
