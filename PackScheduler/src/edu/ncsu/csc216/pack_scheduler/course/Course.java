/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course class for WolfScheduler. Subclass of activity. Inherants activity fields 
 * and also contains course name, section, credits, and instructorID
 * @author Claire Brown
 *
 */
public class Course extends Activity implements Comparable<Course> 
{
 
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Validator to make sure the course name is legal */
	private CourseNameValidator validator;
	private CourseRoll roll;
	
	/**
	 * Constructs a course object with values for all fields
	 * 
	 * @param name
	 *            name of the Course
	 * @param title
	 *            title of the Course
	 * @param section
	 *            section of the Course
	 * @param credits
	 *            credit hours for Course
	 * @param instructorId
	 *            instructor's Unity ID
	 * @param enrollmentCap
	 *            the maximum number of students that can enroll in the course
	 * @param meetingDays
	 *            meeting day for Course as a series of chars
	 * @param startTime
	 *            start time for Course
	 * @param endTime
	 *            end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) 
	{
		super(title, meetingDays, startTime, endTime);
		validator = new CourseNameValidator();
		roll = new CourseRoll(enrollmentCap);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}
	
	/**
	 * Constructs a Course with a given name, title, section, credits,
	 * instructor ID and meeting days
	 * 
	 * @param name
	 *            name of the Course
	 * @param title
	 *            title of the Course
	 * @param section
	 *            section of the Course
	 * @param credits
	 *            credit hours for Course
	 * @param instructorId
	 *            instructor's Unity ID
	 * @param enrollmentCap
	 *            the maximum number of students that can enroll in the course
	 * @param meetingDays
	 *            meeting day for Course as a series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays)
	{
	    this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}
	
	/**Sets the day(s) the course meets as a series of chars
	 * Throws IllegalArgumentException if it consists of chars
	 * other than M, T, W, H, F or A, the string is null or the
	 * string is empty. If meetingDays contains
	 * "A," it must be the only character
	 * @param meetingDays the days the event occurs
	 * @throws IllegalArgumentException if consists of chars
	 * other than M, T, W, H, F or A, the string is null or the
	 * string is empty. If meetingDays contains
	 * "A," it must be the only character
	 */
	@Override
	public void setMeetingDays(String meetingDays)
	{
		if (meetingDays == null || meetingDays.length() == 0)
		{
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for (int i = 0; i < meetingDays.length(); i++)
		{
			if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' && meetingDays.charAt(i) != 'W'
					&& meetingDays.charAt(i) != 'H' && meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A')
			{
				throw new IllegalArgumentException("Invalid meeting days");
			}
		}
		if (meetingDays.contains("A") && meetingDays.length() != 1)
		{
			throw new IllegalArgumentException("Invalid meeting days");
		}
		super.setMeetingDays(meetingDays);
	}
	/**
	 * Return's the course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the course's name
	 * Throws an IllegalArgumentException if the name is null,
	 * has a length less than 4, or length greater than 6.
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null, length is 
	 * less than 4, or length is greater than 6 
	 */
	private void setName(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Invalid name");
		}
		try {
			if (validator.isValid(name)) {
				this.name = name;
			} else {
				throw new IllegalArgumentException();
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid name");
		}
	}

	/**
	 * Returns the course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the course's section
	 * Throws an IllegalArgumentException if section
	 * number is not three digits, is null or 
	 * contains an empty String.
	 * @param section the section to set
	 * @throws IllegalArgumentException if section
	 * number is not three digits, null or is 
	 * an empty String
	 */
	public void setSection(String section) {
		if (section == null || section.length() == 0)
		{
			throw new IllegalArgumentException("Invalid section");
		}
		if (section.length() != 3)
		{
			throw new IllegalArgumentException("Invalid section");
		}
		else
		{
			for (int i = 0; i < 2; i++)
			{
				if (!Character.isDigit(section.charAt(i)))
				{
					throw new IllegalArgumentException("Invalid section");
				}
			}
		}
		this.section = section;
	}

	/**
	 * Return the course's number of credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the course's number of credits
	 * Throws an IllegalArgumentException if credits
	 * is not an int or if the value is less than 1 or 
	 * greater than 5
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits
	 * is less than 1 or greater than 5
	 */
	public void setCredits(int credits) 
	{
		if (credits < 1 || credits > 5)
		{
			throw new IllegalArgumentException("Invalid credits");
		}
		this.credits = credits;
	}

	/**
	 * Returns the course's instructor's Unity ID
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the course's instructor's Unity ID
	 * Throws an IllegalArgumentException if the value 
	 * is null or an empty string
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId
	 * is null or an empty String
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0)
		{
			throw new IllegalArgumentException("Invalid instructor Id");
		}
		this.instructorId = instructorId;
	}


	
	/**
	 * Returns a comma separated value String of all course fields
	 * @return String representation of Course
	 */
	@Override
	public String toString() 
	{
		 if (getMeetingDays().equals("A")) 
		 {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getCourseRoll().getEnrollmentCap() + "," + getMeetingDays();
		 }
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ getCourseRoll().getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + ","
				+ getEndTime();
	}
	
	/** Generates a hashCode for Course using all fields
	 * @return hashCode for Course
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	/** Compares a given object to this object for the equality on all fields
	 * @param obj The Course to compare 
	 * @return true if the objects are the same in all fields
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	/**Returns a short array with the course's name, section
	 * number, title, and meeting days.
	 * @return shortDisplay array containing name, section,
	 * title and meeting days
	 */
	@Override
	public String[] getShortDisplayArray() 
	{
		String[] shortDisplay = new String[5];
		shortDisplay[0] = name;
		shortDisplay[1] = section;
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		shortDisplay[4] = Integer.toString(roll.getOpenSeats());
		return shortDisplay;
	}
	/**Returns a longer array with the course's name, section 
	 * number, title, number of credits, instructor's unity ID, the 
	 * meeting days, and an empty String
	 * @return longDisplay array containing name, section, 
	 * title, credits, instructorID, meeting days, and an 
	 * empty string
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = name;
		longDisplay[1] = section;
		longDisplay[2] = getTitle();
		longDisplay[3] = String.valueOf(credits);
		longDisplay[4] = instructorId;
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";
		return longDisplay;
	}
	/**
	 * Checks if a specific course already exists in the schedule
	 * @param course course to check for a duplicate for
	 * @return true if the course already exists in the schedule
	 * @throws IllegalArgumentException if wrong 
	 */
	public boolean isDuplicate(Activity course)
	{
		try{
			Course c = (Course)course;
			return this.getName().equals(c.getName()); 
		}
		catch(ClassCastException e)
		{
			return false;
		}
	}
	/**
	 * Compares the name and section of the current course to another course.
	 * First checks name and then checks section. 
	 */
	@Override
	public int compareTo(Course o) {
		int compareToVal = 0;
		/**Tests if the names are the same are the same**/
		if (this.getName().equals(o.getName())){
			/**Tests if the sections are the same**/
			if(this.getSection().equals(o.getSection()))
			{
				return 0;
			}
			/**If the names are the same, but the sections aren't the same**/
			else{
				compareToVal = this.getSection().compareTo(o.getSection());
				if (compareToVal > 0)
					compareToVal = 1;
				else if (compareToVal < 0)
					compareToVal = -1;
				return compareToVal;
			}
		}
		/**If names are not equal, which comes first**/
		else{
			compareToVal = this.getName().compareTo(o.getName());
			if (compareToVal > 0)
				compareToVal = 1;
			else if (compareToVal < 0)
				compareToVal = -1;
			return compareToVal;
		}
	}

	/**
	 * Gets the students enrolled in the course
	 * 
	 * @return a CourseRoll containing students in the class
	 */
	public CourseRoll getCourseRoll(){
		return roll;
	}
	
}
