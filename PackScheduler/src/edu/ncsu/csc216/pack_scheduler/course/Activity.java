package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Abstract Activity class for WolfScheduler. Superclass for course and event.
 * Contains title, meeting days, start time and end time.
 * 
 * @author Claire
 * 
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructs a new activity
	 * 
	 * @param title the title of the activity
	 * @param meetingDays the days the activity meets
	 * @param startTime the time the activity starts
	 * @param endTime the time the activity ends
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Returns the course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the course's title Throws an IllegalArgumentException if title is
	 * null or empty
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or empty
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title");
		}
		this.title = title;
	}

	/**
	 * Returns the day(s) the course meets as a series of chars
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the day(s) the course meets as a series of chars Throws
	 * IllegalArgumentException if it consists of chars other than M, T, W, H, F
	 * or A, the string is null or the string is empty. If meetingDays contains
	 * "A," it must be the only character
	 * 
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if consists of chars other than M, T, W, H, F or A, the
	 * string is null or the string is empty. If meetingDays
	 * contains "A," it must be the only character
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the time the course starts at
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the time the course ends at
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the course start and end times Throws an IllegalArgumentException if
	 * the times are not valid military times, the class ends before it begins,
	 * or if there is a start or end time for "A" meeting day courses
	 * 
	 * @param startTime
	 *            the time the course starts
	 * @param endTime
	 *            the time the course ends
	 * @throws IllegalArgumentException
	 *             if not valid military time, class ends before it starts,
	 *             times set for "A" classes
	 */
	public void setActivityTime(int startTime, int endTime) {
		/* checks if valid military time */
		if (startTime < 0 || startTime > 2359) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (endTime < 0 || endTime > 2359) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		/* military time can not have a minute value greater than 59 */
		if (startTime % 100 >= 60 || endTime % 100 >= 60) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		/* checks if starts before it ends */
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		/* checks that if the meetingDay = "A" that startTime and endTime = 0 */
		if (meetingDays.equals("A") && startTime != 0) {
			throw new IllegalArgumentException("Invalid meeting times");
		} else if (meetingDays.equals("A") && endTime != 0) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		this.startTime = startTime;
		this.endTime = endTime;

	}

	/**
	 * Returns the start and end times of the course in standard time (not
	 * military) along with the meeting days. If the meeting day is arranged, it
	 * says so. Calls the militaryTime method to convert the start and end times
	 * 
	 * @return meetingDay and the start and end times in Standard time
	 */
	public String getMeetingString() {
		String standardStart = "";
		String standardEnd = "";
		if (meetingDays.equals("A")) {
			return "Arranged";
		} else {
			standardStart = militaryTime(startTime);
			standardEnd = militaryTime(endTime);
			return meetingDays + " " + standardStart + "-" + standardEnd;
		}
	}

	/**
	 * Converts military time to military time
	 * 
	 * @param time
	 *            the time to be converted
	 * @return the time in standard time as a String
	 */
	public String militaryTime(int time) {
		int minutes = 0;
		int hour;
		String amPm;
		/* determines if afternoon or morning */
		if (time >= 1200) {
			time = time - 1200;
			amPm = "PM";
		} else {
			amPm = "AM";
		}
		/* set minutes */
		minutes = time % 100;
		/* sets hours */
		time = time - minutes;
		if (time == 0) {
			hour = 12;
		} else {
			hour = time / 100;
		}
		/* combines values to form time */
		if (minutes < 10) {
			return hour + ":0" + minutes + amPm;
		}
		return hour + ":" + minutes + amPm;
	}

	/**
	 * Generates a hashCode for Course using all fields
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for the equality on all fields
	 * 
	 * @param obj
	 *            The Course to compare
	 * @return true if the objects are the same in all fields
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Creates a short array w/ information about the activity
	 * 
	 * @return shortDisplay contains 4 elements about the activity
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Creates a long array w/ information about the activity
	 * 
	 * @return longDisplay contains 7 elements about the activity
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks if a specific course or event already exists in the schedule
	 * 
	 * @param activity course/event to check for a duplicate for
	 * @return true if the course/event already exists in the schedule
	 */
	public abstract boolean isDuplicate(Activity activity);

	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException 
	{
		boolean sameDay = false;
		/**compares the meetingDays to see if the activities ever occur on the same day**/
		/*if (possibleConflictingActivity.getMeetingDays().equals("A") || this.getMeetingDays().equals("A"))
		{
		
		}*/
		for (int i = 0; i < this.getMeetingDays().length(); i++)
		{
			if(possibleConflictingActivity.getMeetingDays().indexOf(this.getMeetingDays().charAt(i)) != -1 && 
					this.getMeetingDays().charAt(i) != 'A')
			{
				sameDay = true;
			}
		}
		/**Checks the times if the activities occur on the same day**/
		if (sameDay)
		{
			/**start at the same time**/
			if(this.getStartTime() == possibleConflictingActivity.getStartTime())
			{
				throw new ConflictException();
			}
			/**if one activity begins while another is still occurring.**/
			else if (this.getStartTime() < possibleConflictingActivity.getEndTime() &&
					this.getStartTime() > possibleConflictingActivity.getStartTime())
			{
				throw new ConflictException();
			}
			/**if the other activity begins while the other is still occurring**/
			else if (possibleConflictingActivity.getStartTime() < this.getEndTime() &&
					possibleConflictingActivity.getStartTime() > this.getStartTime())
			{
				throw new ConflictException();
			}
			/**one activity begins when another immediately ends**/
			else if(this.getEndTime() == possibleConflictingActivity.getStartTime() 
				|| this.getStartTime() == possibleConflictingActivity.getEndTime())
			{
				throw new ConflictException();
			}
		}
		
	}
}