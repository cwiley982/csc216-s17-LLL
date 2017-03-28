/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests student record IO
 * @author cvandive
 *
 */
public class StudentRecordIOTest {
	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";
	
	private String validStudent7 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private String validStudent9 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private String validStudent5 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private String validStudent1 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private String validStudent3 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private String validStudent4 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private String validStudent2 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private String validStudent10 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private String validStudent6 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private String validStudent8 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	
	private String[] validStudents = {validStudent1, validStudent2, validStudent3, validStudent4, validStudent5, validStudent6, validStudent7, validStudent8, validStudent9, validStudent10};
	/**
	 * Sets up the hashed version of pw
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	/**
	 * Test method for read student records.
	 * @throws FileNotFoundException if inavlid file
	 */
	@Test
	public void testReadStudentRecords() throws FileNotFoundException {
		//testing valid Student records
		try{
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			assertEquals(10, students.size());
			
			for(int i = 0; i < students.size(); i++){
				assertEquals(validStudents[i], students.get(i).toString());
			}
		} catch(FileNotFoundException e){
			fail("Unexpected error reading " + "student_records.txt");
		}
		//testing file that does not exist
		SortedList<Student> students = null;
		try{
			students = StudentRecordIO.readStudentRecords("test-files/fake_file.txt");
			assertEquals(0, students.size());
		} catch(FileNotFoundException e){
			assertEquals(students, null);
		}
		//testing file that is empty
		try{
			students = StudentRecordIO.readStudentRecords("test-files/Empty_File.txt");
			assertEquals(0, students.size());
		} catch(FileNotFoundException e){
			assertEquals(students, null);
		}
		//testing invalid Student records
		try{
			students = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
			assertEquals(0, students.size());
		} catch(IllegalArgumentException e){
			assertEquals(0, students.size());
		}
	
	}

	/**
	 * Test method for write student records.
	 * @throws FileNotFoundException if file is not found
	 */
	@Test
	public void testWriteStudentRecords() throws FileNotFoundException 
	{
		SortedList<Student> studentDirectory = StudentRecordIO.readStudentRecords("test-files/expected_student_records.txt");
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", studentDirectory);
		} 
		catch (FileNotFoundException e){
			throw new FileNotFoundException();
		}
		catch (IOException e) {
			fail("Cannot write to student directory file");
		}
		
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}
	
	/**
	 * tests writing to a file with no permissions
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}

}
