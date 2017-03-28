package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests the Activity method's implemented from Conflict
 * @author Claire
 *
 */
public class ActivityTest {
	
	/**
	 * Tests the check conflict method Activity implements from the Conflict interface.
	 */
	@Test
	public void testCheckConflict() 
	{
		/**Tests if two non-conflicting activities are shown as non-conflicting**/
	    Activity a1 = new Course("CSC226", "Discrete Math", "002", 4, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2); 
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	    /**Tests if two non-conflicting activities are shown as non-conflicting, even if they call the methods 
	     * in the opposite order**/
	    try {
	        a2.checkConflict(a1);
	        assertEquals("Incorrect meeting string for the possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	        assertEquals("Incorrect meeting string for this Activity", "MW 1:30PM-2:45PM", a1.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared. In "
	        		+ "this case, the second activity was used as the 'this.'");
	    }
	    
	    /**Tests what occurs if the activity starts when the possibleConflicting Activity ends**/
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    try 
	    {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    /**Tests what occurs if the activity ends when the possibleConflicting Activity starts**/
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1215, 1330);
	    try 
	    {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 12:15PM-1:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    /**Tests that an activity conflicts with the possibleConflictingActivity if it starts before the 
	     * the possibleConflictingActivity ends**/
	    a1.setActivityTime(1430, 1530);
	    try 
	    {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:30PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    /**Tests that an activity conflicts with the possibleConflictingActivity if the possibleConflictingActivity 
	     * starts before the scheduled activity ends**/
	    a1.setActivityTime(1300, 1430);
	    try 
	    {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 1:00PM-2:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    /**Tests two activities that occur at the same time**/
	    a1.setActivityTime(1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        fail();
	    }
	    catch(ConflictException e)
	    {
	    	assertEquals("TH 1:30PM-2:45PM", a1.getMeetingString());
	    	assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    	
	    }
	    /**Tests two activities that occur on the same time on one of their meeting days**/
	    a1.setMeetingDays("MTH");
	    try {
	        a1.checkConflict(a2);
	        fail();
	    }
	    catch(ConflictException e)
	    {
	    	assertEquals("MTH 1:30PM-2:45PM", a1.getMeetingString());
	    	assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    	
	    }
	}
}
