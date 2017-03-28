package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Contains a catalog of courses
 * 
 * @author Claire, Corey
 *
 */
public class CourseCatalog {
	
	private SortedList<Course> catalog;
	/**
	 * Constructs an empty course catalog
	 */
	public CourseCatalog()
	{
		newCourseCatalog();
	}
	/**
	 * Constructs an empty course catalog
	 */
	public void newCourseCatalog()
	{
		catalog = new SortedList<Course>();
	}
	/**
	 * Loads course records into the catalog
	 * @param filename name of file to read courses from
	 * @throws IllegalArgumentException if file cannot be found
	 */
	public void loadCoursesFromFile(String filename){
		try{
			catalog = CourseRecordIO.readCourseRecords(filename);
		}
		catch(FileNotFoundException e){
			throw new IllegalArgumentException("File not found");
		}
		
	}
	/**
	 * Adds a course with the following fields to the catalog
	 * @param name name of course to add
	 * @param title title of course to add
	 * @param section section of course to add
	 * @param credits credits of course to add
	 * @param instructorId instructor ID of course to add
	 * @param enrollmentCap the enrollment cap of the course to add
	 * @param meetingDays meeting days of course to add
	 * @param startTime start time of course to add
	 * @param endTime end time of course to add
	 * @return whether the course can be added or not
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime){
		Course c = null; 
		try{
			if(meetingDays.equals("A")){
				c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
			}
			else{
				c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
			}
			boolean duplicate = false;
			for (int i = 0; i < catalog.size(); i++)
			{
				if (c.getName().equals(catalog.get(i).getName()) && c.getSection().equals(catalog.get(i).getSection()))
				{
					duplicate = true;
				}
			}
			if (!duplicate)
			{
				catalog.add(c);
				return true;
			}
			else{
				return false;
			}
		} catch(IllegalArgumentException e){
			throw new IllegalArgumentException(e.getMessage());	
		}
	}
	/**
	 * Removes a course with the given name and section from the catalog
	 * @param name name of course to remove
	 * @param section section of course to remove
	 * @return whether the course can be removed or not
	 */
	public boolean removeCourseFromCatalog(String name, String section){
		for(int i = 0; i < this.catalog.size(); i++){
			if(this.catalog.get(i).getName().equals(name) && this.catalog.get(i).getSection().equals(section)){
				this.catalog.remove(i);
				return true;
				}
			}
		return false;
		}
	/**
	 * Gets a course with the given name and section from the catalog	
	 * @param name name of course to get
	 * @param section section of course to get
	 * @return a course
	 */
	public Course getCourseFromCatalog(String name, String section){
		for(int i = 0; i < catalog.size(); i++){
			if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)){
				return catalog.get(i);
			}
		}
		return null;	
	}
	/**
	 * Gets every course in the catalog
	 * @return name, section, title and meeting infor of every
	 * course in the catalog
	 */
	public String[][] getCourseCatalog(){
		String[][] scheduledCourses = new String[this.catalog.size()][4];
		for(int i = 0; i < scheduledCourses.length; i++){
			scheduledCourses[i] = this.catalog.get(i).getShortDisplayArray();
		}
		return scheduledCourses;
	}
	/**
	 * Saves the catalog of courses to the given file
	 * @param filename file to save catalog to
	 * @throws IllegalArgumentException if file cannot be written
	 */
	public void saveCourseCatalog(String filename) {
		try{
			CourseRecordIO.writeCourseRecords(filename, catalog);
		}
		catch(IOException e){
			throw new IllegalArgumentException("File cannot be written");
		}
	}
}
