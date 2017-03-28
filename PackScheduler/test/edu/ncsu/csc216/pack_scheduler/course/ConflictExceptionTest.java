/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**Tests the ConflictException class (specifically its constructors)
 * @author Claire
 *
 **/
public class ConflictExceptionTest {

	/**
	 * Tests how the ConflictException class handles an exception with a custom
	 * message.
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Tests how the ConflictException class handles an exception with the default
	 * message.
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
