package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
/**
 * Class that handles operations of a course roll, including adding dropping and increasing its capacity
 * @author Sam
 */
public class CourseRoll {
	
	private LinkedAbstractList<Student> roll;
	private int enrollmentCap;
	private static final int MIN_ENROLLMENT = 10;
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * Constructor for CourseRoll
	 * @param cap the maximum capacity to set.
	 */
	public CourseRoll(int cap) {
		roll = new LinkedAbstractList<Student>(250);
		setEnrollmentCap(cap);
	}

	/**
	 * Getter method for enrollment cap
	 * @return the enrollmentCap
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Sets the enrollment cap
	 * @param cap the enrollment cap to set
	 * @throws IllegalArgumentException if the cap is invalid.
	 */
	public void setEnrollmentCap(int cap) {
		if(cap > MAX_ENROLLMENT || cap < MIN_ENROLLMENT || cap < roll.size()) {
			throw new IllegalArgumentException("The enrollment cap you entered is invalid.");
		}
		this.enrollmentCap = cap;
	}

	/**
	 * return the amount of open seats in a class
	 * @return the number of open seats in the course
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Adds a student to the roll
	 * 
	 * @param s the student to add
	 * @throws IllegalArgumentException if s is null or if there are no more open seats or if s is already enrolled
	 */
	public void enroll(Student s) {
		if(s == null || getOpenSeats() <= 0) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < roll.size(); i++) {
			if(s.equals(roll.get(i))) {
				throw new IllegalArgumentException();
			}
		}
		roll.add(roll.size(), s);
	}
	/**
	 * Method that removes a student from a roll
	 * @param s the student to remove
	 * @throws IllegalArgumentException if s is null
	 */
	public void drop(Student s) {
		if(s == null) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < roll.size(); i++) {
			if(s.equals(roll.get(i))) {
				roll.remove(i);
				break;
			}
		}
	}
	
	/**
	 * True if a student can enroll false otherwise
	 * @param s the student to test
	 * @return true if they can enroll false otherwise
	 * @throws IllegalArgumentException if s is null
	 */
	public boolean canEnroll(Student s) {
		if(s == null){
			throw new IllegalArgumentException();
		}
		if(getOpenSeats() <= 0) {
			return false;
		}
		for(int i = 0; i < roll.size(); i++) {
			if(s.equals(roll.get(i))) {
				return false;
			}
		}
		return true;
	}

}
