/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Tests the CourseCatalog class
 * @author Claire, Kayleigh, Corey
 */
public class CourseCatalogTest {
	/**
	 * Attempts to construct a new courseCatalog
	 */
	@Test
	public void testConstructor(){
		CourseCatalog c = new CourseCatalog();
		assertEquals(0, c.getCourseCatalog().length);
	}
	/**
	 * Attempts to create a new empty courseCatalog after classes have been added to the catalog.
	 */
	@Test
	public void testEmptyCatalog(){
		CourseCatalog c = new CourseCatalog();
		c.addCourseToCatalog("ZPD181", "Zoo Penguin Dance", "001", 4, "cavandiv", 10, "MTWHF", 100, 1600);
		c.newCourseCatalog();
		assertEquals(0, c.getCourseCatalog().length);
	}
	/**
	 * Attempts to read in courses from a file into the catalog. Tests a valid and invalid course file.
	 */
	@Test
	public void testLoadCoursesFromFile(){
		/**tries to load an invalid file into catalog**/
		CourseCatalog c = new CourseCatalog();
		try {
			c = new CourseCatalog();
			c.loadCoursesFromFile("test-files/invalid_course_records.txt");
			assertEquals(0, c.getCourseCatalog().length);
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading file");
		}
		/**tries to load a valid file with courses into catalog**/
		try {
			c = new CourseCatalog();
			c.loadCoursesFromFile("test-files/course_records.txt");
			assertEquals(8, c.getCourseCatalog().length);
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading file");
		}
		/**tries to load a non-existent file into the catalog */
		try{
			c = new CourseCatalog();
			c.loadCoursesFromFile("test-files/fake_file.txt");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals(0, c.getCourseCatalog().length);
		}
	}
	/**
	 * Attempts to add Courses to the CourseCatalog
	 */
	@Test
	public void testAddCoursesToCatalog(){
		CourseCatalog c = new CourseCatalog();
		/**attempts to add an invalid course*/
		try{
			c.addCourseToCatalog("q", "q", "1", 19, "dfwo", 10, "z", 0, 0);
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(0, c.getCourseCatalog().length);
		}
		
		/**attempts to add a valid course*/
		c.addCourseToCatalog("ZP181", "Zoo Penguin Dance", "001", 4, "cavandiv", 10, "MTWHF", 100, 1600);
		assertEquals(1, c.getCourseCatalog().length);
	}
	/**
	 * Attempts to remove Courses from the Course Catalog
	 */
	@Test
	public void testRemoveCourse(){
		CourseCatalog c = new CourseCatalog();
		/**attempts to remove a course within the CourseCatalog*/
		c.addCourseToCatalog("ZPD181", "Zoo Penguin Dance", "001", 4, "cavandiv", 10, "MTWHF", 100, 1600);
		c.removeCourseFromCatalog("ZPD181", "001");
		assertEquals(0, c.getCourseCatalog().length);
		
		/**attempts to remove a course not within the CourseCatalog*/
		c = new CourseCatalog();
		c.removeCourseFromCatalog("ZPD181", "001");	
		assertEquals(0, c.getCourseCatalog().length);
	}
	/**
	 * Tests that getCourseFromCatalog returns the correct values
	 */
	@Test
	public void testGetCourseFromCatalog(){
		CourseCatalog c = new CourseCatalog();
		/**Attempts to get a course not within the CourseCatalog*/
		assertNull(c.getCourseFromCatalog("CSC216", "001"));
		
		/**Attempts to get a course within the CourseCatalog*/
		c.addCourseToCatalog("ZPD181", "Zoo Penguin Dance", "001", 4, "cavandiv", 10, "MTWHF", 100, 1600);
		Course course = new Course("ZPD181", "Zoo Penguin Dance", "001", 4, "cavandiv", 10, "MTWHF", 100, 1600);
		assertEquals(course, c.getCourseFromCatalog("ZPD181", "001"));	
	}
	/**
	 * Tests the equality of the String returned from getCourseCatalog()
	 */
	@Test
	public void testGetCourseCatalog(){
		CourseCatalog c = new CourseCatalog();
		c.addCourseToCatalog("ZPD181", "Zoo Penguin Dance", "001", 4, "cavandivd", 10, "MTWHF", 100, 1600);
		c.addCourseToCatalog("CC222", "COOKIES!", "001", 3, "ckbrown3", 10, "MTF", 1300, 1800);
		assertEquals("CC222", c.getCourseCatalog()[0][0]);
		assertEquals("ZPD181", c.getCourseCatalog()[1][0]);
		
	}
	
	/**
	 * Attempts to save the CourseCatalog to a file
	 */
	@Test
	public void testSaveCourseCatalog(){
		CourseCatalog c = new CourseCatalog();
		c.addCourseToCatalog("ZPD181", "Zoo Penguin Dance", "001", 4, "cavandiv", 10, "MTWHF", 100, 1600);
		c.addCourseToCatalog("CC222", "COOKIES!", "001", 3, "ckbrown3", 10, "MTF", 1300, 1800);
		 //tries to save CourseCatalog to a valid file
		c.saveCourseCatalog("test-files/Empty_File.txt");
		try{
			CourseRecordIO.readCourseRecords("test-files/Empty_File.txt");
		} catch(FileNotFoundException e){
			fail("Unexpected error reading file.");
		}
	}
	
}
