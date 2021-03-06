package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Describes state and behavior of a faculty user
 * 
 * @author Lauren
 * @author Caitlyn
 */
public class Faculty extends User {
	
	/** number of courses a faculty member can teach */
	private int maxCourses;
	/** minimum number of courses */
	public static final int MIN_COURSES = 1;
	/** maximum number of courses */
	public static final int MAX_COURSES = 3;
	/** The schedule containing all courses a faculty member will teach */
	private FacultySchedule schedule;
	
	/**
	 * Constructs a faculty object
	 * @param firstName first name
	 * @param lastName last name
	 * @param id ID
	 * @param email email
	 * @param password password
	 * @param courses max number of courses
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int courses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(courses);
		schedule = new FacultySchedule(id);
	}

	/**
	 * Gets the faculty's schedule
	 * 
	 * @return schedule for the faculty member
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}

	/**
	 * Tells whether the faculty has too many classes or not
	 * 
	 * @return true if schedule contains more courses than MAX_COURSES
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;
	}

	/**
	 * sets the max number of courses faculty can teach
	 * @param courses number of courses
	 */
	public void setMaxCourses(int courses) {
		if(courses < MIN_COURSES || courses > MAX_COURSES){
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = courses;
	}
	
	/**
	 * returns max number of courses faculty can teach
	 * @return maxCourses
	 */
	public int getMaxCourses(){
		return this.maxCourses;
	}
	
	/**
	 * hashes the fields of faculty plus the maxCourses field that's been added
	 * @return hash key
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}
	
	/**
	 * tests if two faculty objects are equal
	 * @return true if equal false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}
	
	/**
	 * returns a string representation of faculty
	 * @return comma separated string of all faculty 
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + "," + super.getPassword() + "," + maxCourses;
	}
	
	
}
