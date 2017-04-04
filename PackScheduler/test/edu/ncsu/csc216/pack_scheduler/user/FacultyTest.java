/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Faculty class
 * @author Lauren
 *
 */
public class FacultyTest {
	
	/**
	 * tests the Faculty constructor
	 */
	@Test
	public void testConstructor() {
		Faculty s = null;
		//Testing for null first name 
		try {
		    s = new Faculty(null, "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for empty first name
		try {
		    s = new Faculty("", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for null last name
		try {
		    s = new Faculty("first", null, "id", "email@ncsu.edu", "hashedpassword", 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for empty last name
		try {
		    s = new Faculty("first", "", "id", "email@ncsu.edu", "hashedpassword", 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for null id
		try {
		    s = new Faculty("first", "last", null, "email@ncsu.edu", "hashedpassword", 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for empty id
		try {
		    s = new Faculty("first", "last", "", "email@ncsu.edu", "hashedpassword", 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for null email
		try {
		    s = new Faculty("first", "last", "id", null, "hashedpassword", 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for empty email
		try {
		    s = new Faculty("first", "last", "id", "", "hashedpassword", 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for no @ in email
		 try {
			 s = new Faculty("first", "last", "id", "emailwith.noatsign", "hashedpassword", 2);
			 fail();
		 } catch (IllegalArgumentException e) {
			 assertNull(s);
		 }
		//Testing for no . in email
		try {
			s = new Faculty("first", "last", "id", "emailwith@noperiod", "hashedpassword", 2);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//Testing for period after at sign in email
		try {
			s = new Faculty("first", "last", "id", "test.email@com", "hashedpassword", 2);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//Testing for null password
		try {
		    s = new Faculty("first", "last", "id", "email@ncsu.edu", null, 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Tests for empty password
		try {
		    s = new Faculty("first", "last", "id", "email@ncsu.edu", "", 2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Tests for courses less than 1
		try {
			s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//Tests for credits greater than 3
		try {
			s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 4);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//tests valid constructor
		try{
			s = new Faculty("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 2);
			assertEquals("Claire", s.getFirstName());
			assertEquals("Brown", s.getLastName());
			assertEquals("ckbrown3", s.getId());
			assertEquals("ckbrown3@ncsu.edu", s.getEmail());
			assertEquals("password123", s.getPassword());
			assertEquals(2, s.getMaxCourses());
		} catch(IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Tests toString method in Faculty class
	 */
	@Test
	public void testToString() 
	{
		User s1 = new Faculty("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 3);
		assertEquals("Claire,Brown,ckbrown3,ckbrown3@ncsu.edu,password123,3", s1.toString());
	}

}
