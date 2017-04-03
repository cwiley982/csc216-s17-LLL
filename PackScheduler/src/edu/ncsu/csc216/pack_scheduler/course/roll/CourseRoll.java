package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;
/**
 * Class that handles operations of a course roll, including adding dropping and increasing its capacity
 * @author Sam
 */
public class CourseRoll {
	
	private LinkedAbstractList<Student> roll;
	private int enrollmentCap;
	private LinkedQueue<Student> waitlist;
	private static final int MIN_ENROLLMENT = 10;
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * Constructor for CourseRoll
	 * @param cap the maximum capacity to set.
	 * @param c that CourseRoll is associated with
	 */
	public CourseRoll(Course c, int cap) {
		if(c == null){
			throw new IllegalArgumentException();
		}
		roll = new LinkedAbstractList<Student>(250);
		setEnrollmentCap(cap);
		this.waitlist = new LinkedQueue<Student>(MIN_ENROLLMENT);
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
		this.roll.setCapacity(cap);
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
		if (s == null || getOpenSeats() < 0) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < roll.size(); i++) {
			if(s.equals(roll.get(i))) {
				throw new IllegalArgumentException();
			}
		}
		if (roll.size() < enrollmentCap) {
			roll.add(s);
		} else {
			if (waitlist.size() == MIN_ENROLLMENT) {
				throw new IllegalArgumentException();
			} else {
				waitlist.enqueue(s);
			}
		}
	}

	/**
	 * Method that removes a student from a roll
	 * @param s the student to remove
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		int rollSize = roll.size();
		for (int i = 0; i < rollSize; i++) {
			if(s.equals(roll.get(i))) {
				roll.remove(i);
				if (waitlist.size() != 0) {
					roll.add(waitlist.dequeue());
				}
				break;
			}
		}

		int waitlistSize = waitlist.size();
		for (int i = 0; i < waitlistSize; i++) {
			Student student = waitlist.dequeue(); // remove student
			if(!student.equals(s)){
				waitlist.enqueue(student); // add back if not student trying to
											// drop
			}
		}
	}
	
	/**
	 * returns the number of students on the waitlist
	 * @return size of waitlist
	 */
	public int getNumberOnWaitlist(){
		return waitlist.size();
	}
	/**
	 * True if a student can enroll false otherwise
	 * @param s the student to test
	 * @return true if they can enroll false otherwise
	 * @throws IllegalArgumentException if s is null
	 */
	public boolean canEnroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		if (getOpenSeats() <= 0) {
			return false;
		}
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				return false;
			}
		}
		return true;
	}

}
