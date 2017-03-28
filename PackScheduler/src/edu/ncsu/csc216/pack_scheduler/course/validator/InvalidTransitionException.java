package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Exception class for invalid transitions made in an FSM
 * 
 * @author Sam
 * @author Caitlyn
 * @author Claire
 */
public class InvalidTransitionException extends Exception {
	/** The message that displays during a transition exception **/
	private String message;
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new transition exception with a custom message
	 * 
	 * @param message
	 *            the custom exception message.
	 */
	public InvalidTransitionException(String message) {
		this.message = message;
	}

	/**
	 * Creates a new transition exception with the default exception message.
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}

	/**
	 * Returns the transition conflict message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
