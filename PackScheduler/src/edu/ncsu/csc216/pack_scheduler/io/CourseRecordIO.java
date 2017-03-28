package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Reads Course records from text files. Creates a set of CourseRecords in a file.
 * 
 * @author Claire Brown
 */
public class CourseRecordIO {

	/**
	 * Reads course records from file and generates a list
	 * of courses. Invalid courses are ignored as well as 
	 * duplicate courses (same name and section number). 
	 * @param fileName file containing the courses
	 * @return a list of valid courses
	 * @throws FileNotFoundException if no file
	 * can be read or found.
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException{
		Scanner fileReader = new Scanner(new File(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		if (fileName == null || fileName.equals(""))
		{
			fileReader.close();
			throw new FileNotFoundException();
		}
		while (fileReader.hasNextLine()) 
		{
			
			try{
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++)
				{
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection()))
					{
						duplicate = true;
					}
				}
				if (!duplicate)
				{
					courses.add(course);
				}
	 		}
	 		catch(IllegalArgumentException e) 
	 		{
	 			//skip line
	 		}
		}
		fileReader.close();
		return courses;
	}
	/**
	 * Processes each course by breaking up the line using a
	 * delimiter.
	 * @param nextLine line from input file to process
	 * @return
	 * @throw IllegalArgumentException
	 */
	private static Course readCourse(String nextLine) 
	{
		Scanner readInput = new Scanner(nextLine);
		readInput.useDelimiter(",");
		Course c = null;
		int courseCredits;
		int enrollmentCap;
		try
		{
			String courseName = readInput.next();
			String courseTitle = readInput.next();
			String courseSection = readInput.next();
			courseCredits = Integer.parseInt(readInput.next());
			String instructorID = readInput.next();
			enrollmentCap = Integer.parseInt(readInput.next());
			String courseMeetDays = readInput.next();
				
			if (!courseMeetDays.equals("A"))
			{
				int startTime = readInput.nextInt();
				int endTime = readInput.nextInt();
				c = new Course(courseName, courseTitle, courseSection, courseCredits, instructorID, enrollmentCap, courseMeetDays, startTime, endTime);
			}
			else
			{
				if (!readInput.hasNext())
				{
					c = new Course(courseName, courseTitle, courseSection, courseCredits, instructorID, enrollmentCap, courseMeetDays);
				}
				/**if courseMeetDays = "A" then there should be no tokens left**/
				else
				{
					readInput.close();
					throw new IllegalArgumentException();
				}
			}
			readInput.close();
			return c;
		}
		catch (NoSuchElementException e)
		{
			readInput.close();
			throw new IllegalArgumentException();
		}

	}
	/**
	 * Writes the list of Course records into a new File
	 * @param fileName the name of the File
	 * @param course the list of activities
	 * @throws IOException if input cannot be interpreted 
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> course) throws IOException
	{
		PrintStream fileWriter = new PrintStream(new File(fileName)); 
		for (int i = 0; i < course.size(); i++) {
		    fileWriter.println(course.get(i).toString());
		}
		fileWriter.close();
		
	}
   

}