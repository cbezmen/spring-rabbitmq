/**
 * 
 */
package com.rabbit.receiver.exceptions;

/**
 * @author cbezmen
 *
 */
public class QueueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QueueException(final String message) {
		super(message);
	}

	public QueueException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
