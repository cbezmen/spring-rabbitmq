/**
 * 
 */
package com.rabbit.sender;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.rabbit.sender.enums.QueueSuffixEnumeration;


/**
 * @author cbezmen
 *
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	public static final String RABBIT_QUEUE = "rabbit.queue";
	public static final String RABBIT_QUEUE_ERROR = RABBIT_QUEUE.concat(QueueSuffixEnumeration.ERROR_SUFFIX.getSuffix());
	public static final String RABBIT_QUEUE_SKIP = RABBIT_QUEUE.concat(QueueSuffixEnumeration.SKIP_SUFFIX.getSuffix());
	
	public static final String ANOTHER_RABBIT_QUEUE = "another.rabbit.queue";
	public static final String ANOTHER_RABBIT_QUEUE_ERROR = ANOTHER_RABBIT_QUEUE.concat(QueueSuffixEnumeration.ERROR_SUFFIX.getSuffix());
	public static final String ANOTHER_RABBIT_QUEUE_SKIP = ANOTHER_RABBIT_QUEUE.concat(QueueSuffixEnumeration.SKIP_SUFFIX.getSuffix());

	public static final String THIRD_RABBIT_QUEUE = "third.rabbit.queue";
	public static final String THIRD_RABBIT_QUEUE_ERROR = THIRD_RABBIT_QUEUE.concat(QueueSuffixEnumeration.ERROR_SUFFIX.getSuffix());
	public static final String THIRD_RABBIT_QUEUE_SKIP = THIRD_RABBIT_QUEUE.concat(QueueSuffixEnumeration.SKIP_SUFFIX.getSuffix());

	
	public static final String RABBIT_THIRD_RABBIT_EXCHANGE = "rabbit.third.rabbit.exchange";
	
	public static final String RABBIT_QUEUE_RK = "exchange_rabbit";
	public static final String ANOTHER_RABBIT_QUEUE_RK = "exchange_another_rabbit";
	public static final String THIRD_RABBIT_QUEUE_RK = "exchange_third_rabbit";
	public static final String FANOUT_RK = "exchange_fanout";
	
	
	public static void main(final String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public List<Queue> canQueue() {
		return Arrays.asList(
				new Queue(RABBIT_QUEUE),
				new Queue(RABBIT_QUEUE_ERROR),
				new Queue(RABBIT_QUEUE_SKIP)
				);
	}
	
	@Bean
	public List<Queue> cemQueue() {
		return Arrays.asList(
				new Queue(ANOTHER_RABBIT_QUEUE),
				new Queue(ANOTHER_RABBIT_QUEUE_ERROR),
				new Queue(ANOTHER_RABBIT_QUEUE_SKIP)
				);
	}
	
	@Bean
	public List<Queue> thirQueue() {
		return Arrays.asList(
				new Queue(THIRD_RABBIT_QUEUE),
				new Queue(THIRD_RABBIT_QUEUE_ERROR),
				new Queue(THIRD_RABBIT_QUEUE_SKIP)
				);
	}
	
	@Bean
    public Exchange exchange() {
		return new FanoutExchange(RABBIT_THIRD_RABBIT_EXCHANGE);
    }
	
	 @Bean
	 public List<Binding> bind() {
		return Arrays.asList(
				new Binding(THIRD_RABBIT_QUEUE, DestinationType.QUEUE, RABBIT_THIRD_RABBIT_EXCHANGE, FANOUT_RK, null),
				new Binding(ANOTHER_RABBIT_QUEUE, DestinationType.QUEUE, RABBIT_THIRD_RABBIT_EXCHANGE, FANOUT_RK, null)
	    );
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
