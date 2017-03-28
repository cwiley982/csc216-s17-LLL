package edu.ncsu.csc216.pack_scheduler.course;
/**
 * If there is a conflict between the activity the user wants to add 
 * and an activity already in their schedule, this class is used to 
 * determine the exception message.
 * @author Claire
 *
 */
public class ConflictException extends Exception 
{
	/**The message that displays during a conflict exception**/
	private String message;
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new conflict exception with a custom message
	 * @param message the custom exception message.
	 */
	public ConflictException(String message)
	{
		this.message = message;
	}
	/**
	 * Creates a new conflict exception with the default exception
	 * message.
	 */
	public ConflictException()
	{
		this("Schedule conflict.");
	}

	/**Returns the conflict message
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	

}
