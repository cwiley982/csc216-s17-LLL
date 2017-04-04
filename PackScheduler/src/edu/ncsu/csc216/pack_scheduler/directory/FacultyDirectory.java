/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of faculty in the system
 * @author Lauren
 * 
 */
public class FacultyDirectory {
	
	/** linked list of faculty in the system */
	private LinkedList facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * constructs a FacultyDirectory
	 */
	public FacultyDirectory(){
		newFacultyDirectory();
	}
	
	/**
	 * creates a new empty FacultyDirectory
	 */
	public void newFacultyDirectory() {
		this.facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * loads a FacultyDirectory from a file
	 * @param filename file name
	 */
	public void loadFacultyFromFile(String filename) {
		
	}
	
	/**
	 * saves the FacultyDirectory to the designated file
	 * @param filename file name
	 */
	public void saveFacultyDirectory(String filename) {
		
	}
	
	/**
	 * adds a Faculty to the FacultyDirectory
	 * @param firstName first name
	 * @param lastName last name
	 * @param id ID
	 * @param email email
	 * @param password password
	 * @param repeatPassword repeated password
	 * @param maxCourses max courses to teach
	 * @return boolean true if added, false if not
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
		Faculty faculty = new Faculty(firstName, lastName, id, email, password, maxCourses);
		return false;
	}
	
	/**
	 * removes a Faculty from the directory
	 * @param id faculty ID
	 * @return true if removed, false if not
	 */
	public boolean removeFaculty(String id) {
		return false;
	}
	
	/**
	 * returns the Faculty specified by the ID
	 * @param id Faculty ID
	 * @return Faculty object
	 */
	public Faculty getFacultyById(String id) {
		return null;
	}
	
	/**
	 * gets all the Faculty in the directory and returns as array
	 * @return String array of Faculty in directory
	 */
	public String[][] getFacultyDirectory() {
		return null;
	}
}
