package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tester class for the CourseNameValidatpr class.
 * 
 * @author Sam
 * @author Claire
 * @author Caitlyn
 */
public class CourseNameValidatorTest {

	/**
	 * Tests Valid name paths for isValid
	 * 
	 */
	@Test
	public void testValidPaths() {
		CourseNameValidator fsm = new CourseNameValidator();

		/*test valid input*/
		try {
			assert fsm.isValid("CSC216");
		}
		catch(InvalidTransitionException e)
		{
			fail();
		}

		/*test start with letter*/
		try {
			assert fsm.isValid("T167");
		}
		catch(InvalidTransitionException e)
		{
			fail();
		}


		/*tests start with 2 letters*/
		try {
			assert fsm.isValid("Cl516");
		}
		catch(InvalidTransitionException e)
		{
			fail();
		}

		/*test start with 3 letters*/
		try {
			assert fsm.isValid("Clr516");
		}
		catch(InvalidTransitionException e)
		{
			fail();
		}

		/*test start with 4 letters*/
		try {
			assert fsm.isValid("Clre516");
		}
		catch(InvalidTransitionException e)
		{
			fail();
		}

		/*test with valid suffix*/
		try {
			assert fsm.isValid("Clre516A");
		}
		catch(InvalidTransitionException e)
		{
			fail();
		}


	}
	
	/**
	 * Tests invalid name paths in isValid
	 */
	@Test
	public void testInvalidPaths() {
		CourseNameValidator fsm2 = new CourseNameValidator();
		
		/*test start with number*/
		try {
			fsm2.isValid("216CSC");
			fail();
		}
		catch(InvalidTransitionException e)
		{
			//skip
		}
		/*test start with non letter or number*/
		try {
			fsm2.isValid("!16CSC");
			fail();
		}
		catch(InvalidTransitionException e)
		{
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}

		/*test start with more than four letters*/
		try {
			fsm2.isValid("Claire216");
			fail();
		}
		catch(InvalidTransitionException e)
		{
			//skip
		}
		/*test end with multiple letters*/
		try {
			fsm2.isValid("CSC216Awesome");
			fail();
		}
		catch(InvalidTransitionException e)
		{
			//skip
		}

		/*test only 1 digit*/
		try {
			assert !fsm2.isValid("Cl5");
		}
		catch(InvalidTransitionException e)
		{
			//skip
		}

		/*test only 2 digit*/
		try {
			assert !fsm2.isValid("Clr51");
		}
		catch(InvalidTransitionException e)
		{
			//skip
		}

		/*test 4 digit*/
		try {
			assert !fsm2.isValid("Clre5165");
		}
		catch(InvalidTransitionException e)
		{
			//skip
		}

		/* test letters, 1 number, letter */
		try {
			fsm2.isValid("CSC2C") ;
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

		/* test letters, 2 numbers, letter */
		try {
			fsm2.isValid("CSC21C");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

		/* test number after suffix */
		try {
			fsm2.isValid("CSC216C2");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}

		/* test punctuation after suffix */
		try {
			fsm2.isValid("CSC216C!");
			fail();
		} catch (InvalidTransitionException e) {
			// skip
		}
	}


}
