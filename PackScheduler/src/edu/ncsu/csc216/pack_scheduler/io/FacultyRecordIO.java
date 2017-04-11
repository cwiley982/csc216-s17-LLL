/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;


/**
 * Reads student records and writes them into a new file
 * 
 * @author hiteshwaripatel
 * @author Caitlyn
 *
 */
public class FacultyRecordIO {

	/**
	 * Reads faculty records from file and returns as a LinkedList
	 * @param fileName file to read Faculty records from
	 * @return a list of Faculty
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		if (fileName == null || fileName.equals("")) {
			fileReader.close();
			throw new FileNotFoundException();
		}
		int i = 0;
		while (fileReader.hasNextLine()) {
			try {
				faculty.add(i, processFaculty(fileReader.nextLine()));
				i++;
			} catch (IllegalArgumentException e) {
				//skip the line
			}
		}
		fileReader.close();
		return faculty;
	}

	private static Faculty processFaculty(String line) {
		Scanner lineReader = new Scanner(line);
		lineReader.useDelimiter(",");
		String firstName, lastName, id, email, password;
		int courses;
		Faculty f;
		try {
			firstName = lineReader.next();
			lastName = lineReader.next();
			id = lineReader.next();
			email = lineReader.next();
			password = lineReader.next();
			courses = lineReader.nextInt();
		} catch(NoSuchElementException e){
			throw new IllegalArgumentException();
		} finally{
			lineReader.close();
		}
		f = new Faculty(firstName, lastName, id, email, password, courses);
		return f;
	}
	/**
	 * Writes a new text file with the faculties from
	 * facultyDirectory
	 * @param fileName the name of the file containing the faculty
	 * @param facultyDirectory LinkedList containing the faculties
	 * @throws IOException if unable to write to the file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for(int i = 0; i < facultyDirectory.size(); i++){
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		
		fileWriter.close();
	}

}
