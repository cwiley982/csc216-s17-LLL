package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tester class for CourseRoll
 * @author Sam
 *
 */
public class CourseRollTest {
	
	//Students to use in the tests
	Student a = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student b = new Student("Claire", "Brow", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student c = new Student("Claire", "Bron", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student d = new Student("Claire", "Brwn", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student e = new Student("Claire", "Bown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student f = new Student("Claire", "rown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student g = new Student("Clair", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student h = new Student("Claie", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student i = new Student("Clare", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student j = new Student("Clire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student k = new Student("Caire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student l = new Student("laire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	Student m = new Student("Clair", "Brow", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
	/**
	 * Tests the constructor
	 */
	@Test
	public void testCourseRoll() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = course.getCourseRoll();
		assertEquals(roll.getEnrollmentCap(), 10);
	}

	/**
	 * Tests setting the enrollment cap
	 */
	@Test
	public void testSetEnrollmentCap() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll r = course.getCourseRoll();
		try {
			r.setEnrollmentCap(300);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(r.getEnrollmentCap(), 10);
		}
		
		try {
			r.setEnrollmentCap(3);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(r.getEnrollmentCap(), 10);
		}
		
		try {
			r.setEnrollmentCap(90);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(r.getEnrollmentCap(), 90);
		//Adding students to test setting enrollment bellow the number of students already enrolled
		r.enroll(a);
		r.enroll(b);
		r.enroll(c);
		r.enroll(d);
		r.enroll(e);
		r.enroll(f);
		r.enroll(g);
		r.enroll(h);
		r.enroll(i);
		r.enroll(j);
		r.enroll(k);
		
		
		try {
			r.setEnrollmentCap(10);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(r.getEnrollmentCap(), 90);
			assertEquals(r.getOpenSeats(), 79);
		}
		
		
	}

	/**
	 * Tests the get open seats method
	 */
	@Test
	public void testGetOpenSeats() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll r = course.getCourseRoll();
		r.enroll(a);
		r.enroll(b);
		r.enroll(c);
		r.enroll(d);
		r.enroll(e);
		r.enroll(f);
		r.enroll(g);
		r.enroll(h);
		r.enroll(i);
		r.enroll(j);
		assertEquals(r.getOpenSeats(), 0);
	}
	/**
	 * Test the enroll method
	 */
	@Test
	public void testEnroll() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll r = course.getCourseRoll();
		try {
			r.enroll(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(r.getOpenSeats(), 10);
		}
		
		r.enroll(a);
		assertEquals(r.getOpenSeats(), 9);
		Student z = new Student("Claire", "Brown", "ckbrown3", "ckbrown3@ncsu.edu", "password123");
		
		try {
			r.enroll(z);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(r.getOpenSeats(), 9);
		}
		
		r.enroll(b);
		r.enroll(c);
		r.enroll(d);
		r.enroll(e);
		r.enroll(f);
		r.enroll(g);
		r.enroll(h);
		r.enroll(i);
		r.enroll(j);
		assertEquals(r.getOpenSeats(), 0);
		assertEquals(r.getNumberOnWaitlist(), 0);
		try {
			r.enroll(k);
			assertEquals(1, r.getNumberOnWaitlist());
		} catch (IllegalArgumentException e) {
			fail();
		}

		
	}
	
	/**
	 * test the drop method
	 */
	@Test
	public void testDrop() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll r = course.getCourseRoll();
		try {
			r.drop(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(r.getOpenSeats(), 10);
		}
		
		r.enroll(a);
		r.enroll(b);
		r.enroll(c);
		assertEquals(0, r.getNumberOnWaitlist());
		r.drop(d);
		assertEquals(r.getOpenSeats(), 7);
		
		r.drop(b);
		assertEquals(r.getOpenSeats(), 8);
		
	}

	/**
	 * test the can enroll method
	 */
	@Test
	public void testCanEnroll() {
		Course course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll r = course.getCourseRoll();
		try {
			r.canEnroll(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(r.getOpenSeats(), 10);
		}
		
		assertTrue(r.canEnroll(a));
		r.enroll(a);
		r.enroll(b);
		r.enroll(c);
		assertFalse(r.canEnroll(a));
		assertFalse(r.canEnroll(b));
		assertFalse(r.canEnroll(c));
		r.enroll(d);
		r.enroll(e);
		r.enroll(f);
		r.enroll(g);
		r.enroll(h);
		r.enroll(i);
		assertTrue(r.canEnroll(l));
		r.enroll(j);
		assertFalse(r.canEnroll(l));
		
	}

}
