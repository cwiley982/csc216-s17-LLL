package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Class for creating an instance of registration manager
 * @author Sam
 *
 */
public class RegistrationManager {

	/**the instance of registration manager*/
	private static RegistrationManager instance;
	/**the instance of course catalog*/
	private CourseCatalog courseCatalog;
	/**the instance of student directory*/
	private StudentDirectory studentDirectory;
	/**the instance of user*/
	private User registrar;
	/**the current user*/
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/**the password to use*/
	private static final String PW = "Regi5tr@r";
	/**the hashed password*/
	private static String hashPW;

	//Static code block for hashing the registrar user's password
	{
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(PW.getBytes());
			hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	private RegistrationManager() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		registrar = new Registrar();
		currentUser = null;
	}
	
	/**
	 * Method to create an instance of registrationManager
	 * @return the instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Get method for the course catalog
	 * @return the course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * get method for student directory
	 * @return the student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Method to login as a registrar
	 * @param id the id of the user that wants to log in
	 * @param password the password of the user that wants to log in
	 * @return true if the login is successful and false otherwise
	 */
	public boolean login(String id, String password) {
		if (currentUser == null) 
		{
			Student s = studentDirectory.getStudentById(id);
			if (s != null) {
				try {

					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (s.getPassword().equals(localHashPW)) {
						currentUser = s;
						return true;
					} else {
						return false;
					}
				} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				}
			} else if (registrar.getId().equals(id)) {
				MessageDigest digest;
				try {
					digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (registrar.getPassword().equals(localHashPW)) {
						currentUser = registrar;
						return true;
					} else {
						return false;
					}
				} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				}
			} else if (s == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}
		}
		/*if user already logged in*/
		else {
			return false;
		}
		
		return false;
	}

	/**
	 * Method that logs out the registrar.
	 */
	public void logout() {
		currentUser = null; 
	}

	/**
	 * method that gets the current user
	 * @return the current user
	 */
	public User getCurrentUser() { 
		return currentUser;
	}

	/**
	 * method that clears the data in course catalog and student directory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c
	 *            Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c
	 *            Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every
	 * course and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}

	/**
	 * The inner class for registrar which is a user that can work with registration manager
	 * @author Sam
	 *
	 */
	private static class Registrar extends User {

		private static final String FIRST_NAME = "Wolf";
		private static final String LAST_NAME = "Scheduler";
		private static final String ID = "registrar";
		private static final String EMAIL = "registrar@ncsu.edu";

		/**
		 * Create a registrar user with the user id of registrar and
		 * password of Regi5tr@r.  Note that hard coding passwords in a 
		 * project is HORRIBLY INSECURE, but it simplifies testing here.
		 * This should NEVER be done in practice!
		 */
		public Registrar() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}
}