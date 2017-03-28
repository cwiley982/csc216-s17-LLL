package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
/**
 * Reads student records and writes them into a new file
 * @author ckbrown3 kagordo3 cavandiv
 *
 */
public class StudentRecordIO {

	/**
	 * Reads student records from file and returns as a SortedList
	 * @param fileName file to read Student records from
	 * @return a list of Students
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		SortedList<Student> students = new SortedList<Student>();
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		if (fileName == null || fileName.equals(""))
		{
			fileReader.close();
			throw new FileNotFoundException();
		}
		while(fileReader.hasNextLine()){
			try{
				students.add(processStudent(fileReader.nextLine()));
			} catch(IllegalArgumentException e){
				//skip the line
			}
		}
		fileReader.close();
		return students;
	}
	private static Student processStudent(String line){
		Scanner lineReader = new Scanner(line);
		lineReader.useDelimiter(",");
		String firstName, lastName, id, email, password;
		int credits;
		Student s;
		try{
			firstName = lineReader.next();
			lastName = lineReader.next();
			id = lineReader.next();
			email = lineReader.next();
			password = lineReader.next();
			credits = lineReader.nextInt();
		} catch(NoSuchElementException e){
			throw new IllegalArgumentException();
		} finally{
			lineReader.close();
		}
		s = new Student(firstName, lastName, id, email, password, credits);
		return s;
	}
	/**
	 * Writes a new text file with the students from
	 * studentDirectory
	 * @param fileName the name of the file containing the students
	 * @param studentDirectory array containing the students
	 * @throws IOException if unable to write to the file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for(int i = 0; i < studentDirectory.size(); i++){
			fileWriter.println(studentDirectory.get(i).toString());
		}
		
		fileWriter.close();
	}

}
