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
	
	/**
	 * tests equals and hashCode methods
	 */
	@Test
	public void testEquals() {
		User s1 = new Faculty("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 1);
		User s2 = new Faculty("Corey", "Vandiver", "cavandiv", "cavandiv@ncsu.edu", "blah", 1);
		User s3 = new Faculty("Corey", "Vandiver", "cavandiv", "cavandiv@ncsu.edu", "blah", 1);
		User s4 = new Faculty("Dean", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 1);
		User s5 = new Faculty("Claire", "Black", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 1);
		User s6 = new Faculty("Claire", "Brown", "rogerroger", "ckbrown3@ncsu.edu", "password123", 1);
		User s7 = new Faculty("Claire", "Brown", "ckbrown3", "roger@ncsu.edu", "password123", 1);
		User s8 = new Faculty("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "123password", 1);
		//test for equality in both directions
		assertTrue(s2.equals(s3));
		assertTrue(s3.equals(s2));
		//test for inequality for each field
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		
		assertEquals(s2.hashCode(), s3.hashCode());
		//test for inequality for each field
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

}
