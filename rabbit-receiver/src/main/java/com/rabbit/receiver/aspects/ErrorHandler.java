/**
 * 
 */
package com.rabbit.receiver.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbit.receiver.Application;
import com.rabbit.receiver.services.impl.ReceiverServiceImpl;

/**
 * @author cbezmen
 *
 */
@Aspect
@Component
public class ErrorHandler implements ThrowsAdvice {

	@Autowired
	private RabbitTemplate template;

	private static final Logger LOG = LoggerFactory.getLogger(ReceiverServiceImpl.class);

	@AfterThrowing(pointcut = "execution(* com.rabbit.receiver.services.impl.ReceiverServiceImpl.receiveString(..))", throwing = "ex")
	public void can(final JoinPoint joinPoint, final Exception ex) {

		LOG.error(ex.getMessage());
		LOG.error("Sending to error queue");
		LOG.info("Sending args " + joinPoint.getArgs()[0]);
		template.convertAndSend(Application.RABBIT_ERROR_QUEUE, joinPoint.getArgs()[0]);

	}

	// @Around(value = "@annotation(canErrorHandler)")
	// public void erro(final ProceedingJoinPoint proceedingJoinPoint, final
	// CanErrorHandler canErrorHandler) {
	// System.out.println(canErrorHandler);
	//
	// }
}
