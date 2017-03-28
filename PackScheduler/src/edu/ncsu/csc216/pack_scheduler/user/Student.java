package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Outlines a Student object which contains information about students
 * @author kagordo3 ckbrown3 cavandiv
 */
public class Student extends User implements Comparable<Student> {
	
	/** Student's maximum number of credit hours */ 
	private int maxCredits;
	/** Maximum number of credit hours for any student */
	public static final int MAX_CREDITS = 18;
	/**The student's schedule of courses*/
	Schedule schedule = new Schedule();

	/**
	 * Constructs a Student with values for all fields
	 * @param firstName student first name
	 * @param lastName student last name
	 * @param id student id
	 * @param email student email 
	 * @param password student hashed password
	 * @param maxCredits student maximum credit hours
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
	}
	/**
	 * Constructs a student with maximum value of maxCredits
	 * @param firstName student first name
	 * @param lastName student last name
	 * @param id student id
	 * @param email student email
	 * @param password student hashed password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}
	/**
	 * Returns the Student's maximum number of credit hours
	 * @return the maximum number of credit hours
	 */
	public int getMaxCredits() {
		return maxCredits;
	}
	/**
	 * Sets the student's maximum number of credit hours
	 * @param maxCredits the credits to set
	 * @throws IllegalArgumentException if maxCredits is less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if(maxCredits < 3 || maxCredits > 18){
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}
	
	
	/**
	 * Returns a String representation of Student
	 * @return comma separated String of all Student fields
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + "," + super.getPassword() + "," + maxCredits;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}
	/**
	 * Compares a student
	 * @param s student to compare
	 * @return 1 if greater, 0 if the same, -1 if less
	 */
	public int compareTo(Student s) 
	{
		int compareToVal = 0;
		/**Tests if the last names are the same**/
		if (this.getLastName().equals(s.getLastName())){
			/**Tests if the first names are the same**/
			if(this.getFirstName().equals(s.getFirstName())){
				/**Tests if the ids are the same**/
				if(this.getId().equals(s.getId()))
					return 0;
				else
				{
					compareToVal = this.getId().compareTo(s.getId());
					if (compareToVal > 0)
						compareToVal = 1;
					else if (compareToVal < 0)
						compareToVal = -1;
					return compareToVal;
				}
			}
			/**If the last names are the same, but the first names aren't the same**/
			else{
				compareToVal = this.getFirstName().compareTo(s.getFirstName());
				if (compareToVal > 0)
					compareToVal = 1;
				else if (compareToVal < 0)
					compareToVal = -1;
				return compareToVal;
			}
		}
		/**If last names are not equal, which comes first**/
		else{
			compareToVal = this.getLastName().compareTo(s.getLastName());
			if (compareToVal > 0)
				compareToVal = 1;
			else if (compareToVal < 0)
				compareToVal = -1;
			return compareToVal;
		}
	}
	/**
	 * Returns the student's schedule
	 * @return schedule The student's schedule of courses
	 */
	public Schedule getSchedule(){
		return this.schedule;
	}
	/**
	 * Checks if the student can add the course without exceeding the max number of credits, can add the
	 * course without a conflict, and that they are not already enrolled in that course.
	 * @param c the course the student is attempting to add
	 * @return true, if the student can add the course
	 */
	public boolean canAdd(Course c){
		return !(!schedule.canAdd(c) || schedule.getScheduleCredits() + c.getCredits() > getMaxCredits());
	}
}
