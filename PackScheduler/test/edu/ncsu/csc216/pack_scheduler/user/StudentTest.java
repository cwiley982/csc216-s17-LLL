package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
/**
 * Tests the student class
 * @author kagordo3 ckbrown3 cavandiv
 *
 */
public class StudentTest {

	/**
	 * Test cases in for the constructor with inputs String, String, String,
	 * String, String, int
	 */
	@Test
	public void testStudentConstructor() {
		/**test to construct a valid student**/
		Student s = null;
		//Testing for null first name 
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword", 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for empty first name
		try {
		    s = new Student("", "last", "id", "email@ncsu.edu", "hashedpassword", 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for null last name
		try {
		    s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword", 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for empty last name
		try {
		    s = new Student("first", "", "id", "email@ncsu.edu", "hashedpassword", 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for null id
		try {
		    s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword", 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for empty id
		try {
		    s = new Student("first", "last", "", "email@ncsu.edu", "hashedpassword", 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for null email
		try {
		    s = new Student("first", "last", "id", null, "hashedpassword", 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for empty email
		try {
		    s = new Student("first", "last", "id", "", "hashedpassword", 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Testing for no @ in email
		 try {
			 s = new Student("first", "last", "id", "emailwith.noatsign", "hashedpassword", 17);
			 fail();
		 } catch (IllegalArgumentException e) {
			 assertNull(s);
		 }
		//Testing for no . in email
		try {
			s = new Student("first", "last", "id", "emailwith@noperiod", "hashedpassword", 17);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//Testing for period after at sign in email
		try {
			s = new Student("first", "last", "id", "test.email@com", "hashedpassword", 17);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//Testing for null password
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", null, 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Tests for empty password
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "", 17);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//Tests for credits less than 3
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//Tests for credits greater than 18
		try {
			s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 19);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		//tests valid constructor
		try{
			s = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
			assertEquals("Claire", s.getFirstName());
			assertEquals("Brown", s.getLastName());
			assertEquals("ckbrown3", s.getId());
			assertEquals("ckbrown3@ncsu.edu", s.getEmail());
			assertEquals("password123", s.getPassword());
			assertEquals(17, s.getMaxCredits());
		} catch(IllegalArgumentException e) {
			fail();
		}
	
	}
	/**
	 * Test cases in for the constructor with inputs String, String, String,
	 * String, String.
	 */
	@Test
	public void testStudentConstructorNoCredits() {
		Student s = null;
		try{
			s = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
			assertEquals("Claire", s.getFirstName());
			assertEquals("Brown", s.getLastName());
			assertEquals("ckbrown3", s.getId());
			assertEquals("ckbrown3@ncsu.edu", s.getEmail());
			assertEquals("password123", s.getPassword());
			assertEquals(18, s.getMaxCredits());
		}
		catch(IllegalArgumentException e){
			fail();
		}
	}
	
	/**
	 * Tests setFirstName method in Student class
	 */
	public void testFirstName(){
		//Sets valid student
		User s = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		//tries to set first name to a null
		try {
		    s.setFirstName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("Claire", s.getFirstName());
		}
		//tries to set first name to a blank string
		try {
		    s.setFirstName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("Claire", s.getFirstName());
		}
		//sets first name to a new string
		s.setFirstName("Corey");
		assertEquals("Corey", s.getFirstName());
	}
	/**
	 * Tests setLastName method in student class
	 */
	public void testLastName(){
		//Sets valid student
		User s = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		//tries to set last name to a null
		try {
		    s.setLastName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("Brown", s.getLastName());
		}
		//tries to set last name to a blank string
		try {
		    s.setLastName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("Brown", s.getLastName());
		}
		//sets last name to a new string
		s.setLastName("Vandiver");
		assertEquals("Vandiver", s.getLastName());
	}
	
	/**
	 * Tests setEmail in student class
	 */
	public void testSetEmail(){
		User s = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		//Tries to set email to null
		try{
			s.setEmail(null);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("ckbrown3@ncsu.edu", s.getEmail());
		}
		//Tries to set email to empty string
		try{
			s.setEmail("");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("ckbrown3@ncsu.edu", s.getEmail());
		}
		//Tries to set email without '@'
		try{
			s.setEmail("ckbrown3");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("ckbrown3@ncsu.edu", s.getEmail());
		}
		//Tries to set email without '.'
		try{
			s.setEmail("ckbrown3@ncsu");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("ckbrown3@ncsu.edu", s.getEmail());
		}
		//Tries to set email without '.' after '@'
		try{
			s.setEmail("ka.gordo3@ncsu");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("ckbrown3@ncsu.edu", s.getEmail());
		}
		s.setEmail("kagordo3@ncsu.edu");
		assertEquals("kagordo3@ncsu.edu", s.getEmail());
	}
	/**
	 * Tests setPassword in student class
	 */
	public void testSetPassword(){
		User s = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		//Tries to set password to null
		try{
			s.setPassword(null);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("password123", s.getPassword());
		}
		//Tries to set password to empty string
		try{
			s.setPassword("");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("password123", s.getPassword());
		}
		
		s.setPassword("password");
		assertEquals("password", s.getPassword());
	}
	/**
	 * Tests setMaxCredits in student class
	 */
	public void testSetMaxCredits(){
		Student s = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		//Tries to set maxCredits less than 3
		try{
			s.setMaxCredits(2);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals(14, s.getMaxCredits());
		}
		//Tries to set maxCredits greater than 18
		try{
			s.setMaxCredits(19);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals(14, s.getMaxCredits());
		}
		s.setMaxCredits(14);
		assertEquals(14, s.getMaxCredits());
	}
	/**
	 * Tests equals method in the student class
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		User s2 = new Student("Corey", "Vandiver", "cavandiv", "cavandiv@ncsu.edu", "blah", 16);
		User s3 = new Student("Corey", "Vandiver", "cavandiv", "cavandiv@ncsu.edu", "blah", 16);
		User s4 = new Student("Dean", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		User s5 = new Student("Claire", "Black", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		User s6 = new Student("Claire", "Brown", "rogerroger", "ckbrown3@ncsu.edu", "password123", 17);
		User s7 = new Student("Claire", "Brown", "ckbrown3", "roger@ncsu.edu", "password123", 17);
		User s8 = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "123password", 17);
		User s9 = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 12);
		//test for equality in both directions
		assertTrue(s2.equals(s3));
		assertTrue(s3.equals(s2));
		//test for inequality for each field
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		assertFalse(s1.equals(s9));
	}
	/**
	 * Tests hashCode method in student class
	 */
	@Test
	public void testHashCode(){
		User s1 = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		User s2 = new Student("Corey", "Vandiver", "cavandiv", "cavandiv@ncsu.edu", "blah", 16);
		User s3 = new Student("Corey", "Vandiver", "cavandiv", "cavandiv@ncsu.edu", "blah", 16);
		User s4 = new Student("Dean", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		User s5 = new Student("Claire", "Black", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		User s6 = new Student("Claire", "Brown", "rogerroger", "ckbrown3@ncsu.edu", "password123", 17);
		User s7 = new Student("Claire", "Brown", "ckbrown3", "roger@ncsu.edu", "password123", 17);
		User s8 = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "123password", 17);
		User s9 = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 12);
		//test for same hash code
		assertEquals(s2.hashCode(), s3.hashCode());
		//test for inequality for each field
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
		assertNotEquals(s1.hashCode(), s9.hashCode());
		
	}
	/**
	 * Tests toString method in student class
	 */
	@Test
	public void testToString() 
	{
		User s1 = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123", 17);
		assertEquals("Claire,Brown,ckbrown3,ckbrown3@ncsu.edu,password123,17", s1.toString());
	}
	
	/**
	 * Tests comparison of two students to see where they go
	 * in the list
	 */
	@Test
	public void testCompareTo(){
		Student s1 = new Student("bobby", "plumbus", "baplum", "email@ncsu.edu", "hashedpassword", 17);
		Student s2 = new Student("dobby", "dumbus", "dadumb", "email@ncsu.edu", "hashedpassword", 17);
		Student s3 = new Student("lobby", "dumbus", "ladumb", "email@ncsu.edu", "hashedpassword", 17);
		Student s4 = new Student("lobby", "dumbus", "lbdumb", "email@ncsu.edu", "hashedpassword", 17);
		Student s5 = new Student("bobby", "plumbus", "baplum", "email@ncsu.edu", "hashedpassword", 17);

		//compares people with different last names
		assertEquals(1, s1.compareTo(s2));
		assertEquals(-1, s2.compareTo(s1));
		//compares people with same last name but different first name
		assertEquals(-1, s2.compareTo(s3));
		assertEquals(1, s3.compareTo(s2));
		//compares people with same last and first name, but different id
		assertEquals(-1, s3.compareTo(s4));
		assertEquals(1, s4.compareTo(s3));
		//compare people who are the same
		assertEquals(0, s1.compareTo(s5));
	}
	
	/**
	 * Tests the canAdd method for the student class
	 */
	@Test
	public void testCanAdd(){
		Student s1 = new Student("bobby", "plumbus", "baplum", "email@ncsu.edu", "hashedpassword", 17);
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
		//Should be able to add
		assert s1.canAdd(c); 
		s1.setMaxCredits(3);
		//Should not be able to add
		assertFalse(s1.canAdd(c));
		
	}
}
