/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**Conflict interface to be implemented with Activity
 * @author Claire
 *
 */
public interface Conflict {

	/**
	 * Checks to see if the activity the user attempts to add 
	 * conflicts with another activity already on their schedule
	 * @param possibleConflictingActivity activity checked to 
	 * make sure it doesn't conflict with anything
	 * @throws ConflictException if possibleConflictingActivity
	 * does conflict with an already scheduled activity.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
