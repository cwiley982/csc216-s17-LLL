/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of faculty in the system
 * @author Lauren
 * 
 */
public class FacultyDirectory {
	
	/** linked list of faculty in the system */
	private LinkedList<Faculty> facultyDirectory;
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
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * loads a FacultyDirectory from a file
	 * @param filename file name
	 */
	public void loadFacultyFromFile(String filename) {
		try {
			this.facultyDirectory = FacultyRecordIO.readFacultyRecords(filename);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + filename);
		}
	}
	
	/**
	 * saves the FacultyDirectory to the designated file
	 * @param filename file name
	 */
	public void saveFacultyDirectory(String filename) {
		try {
			FacultyRecordIO.writeFacultyRecords(filename, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + filename);
		}
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
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());
			
			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}
	
	/**
	 * removes a Faculty from the directory
	 * @param id faculty ID
	 * @return true if removed, false if not
	 */
	public boolean removeFaculty(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(id)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returns the Faculty specified by the ID
	 * @param id Faculty ID
	 * @return Faculty object
	 */
	public Faculty getFacultyById(String id) {
		for(int i = 0; i < facultyDirectory.size(); i++){
			if(facultyDirectory.get(i).getId().equals(id)){
				return facultyDirectory.get(i);
			}
		}
		return null;
	}
	
	/**
	 * gets all the Faculty in the directory and returns as array
	 * @return String array of Faculty in directory
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}
}
