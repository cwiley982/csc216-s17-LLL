/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Tests faculty record IO
 * 
 * @author Caitlyn
 *
 */
public class FacultyRecordIOTest {
	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";

	private String validFaculty1 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	private String validFaculty2 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	private String validFaculty3 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	private String validFaculty4 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	private String validFaculty5 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	private String validFaculty6 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	private String validFaculty7 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	private String validFaculty8 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";

	private String[] validFacultys = { validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5,
			validFaculty6, validFaculty7, validFaculty8 };

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

			for (int i = 0; i < validFacultys.length; i++) {
				validFacultys[i] = validFacultys[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Test method for read faculty records.
	 * 
	 * @throws FileNotFoundException
	 *             if inavlid file
	 */
	@Test
	public void testReadFacultyRecords() throws FileNotFoundException {
		// testing valid Faculty records
		try {
			LinkedList<Faculty> facultys = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			assertEquals(8, facultys.size());

			for (int i = 0; i < facultys.size(); i++) {
				assertEquals(validFacultys[i], facultys.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + "faculty_records.txt");
		}
		// testing file that does not exist
		LinkedList<Faculty> facultys = null;
		try {
			facultys = FacultyRecordIO.readFacultyRecords("test-files/fake_file.txt");
			assertEquals(0, facultys.size());
		} catch (FileNotFoundException e) {
			assertEquals(facultys, null);
		}
		// testing file that is empty
		try {
			facultys = FacultyRecordIO.readFacultyRecords("test-files/Empty_File.txt");
			assertEquals(0, facultys.size());
		} catch (FileNotFoundException e) {
			assertEquals(facultys, null);
		}
		// testing invalid Faculty records
		try {
			facultys = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
			assertEquals(0, facultys.size());
		} catch (IllegalArgumentException e) {
			assertEquals(0, facultys.size());
		}

	}

	/**
	 * Test method for write faculty records.
	 * 
	 * @throws FileNotFoundException
	 *             if file is not found
	 */
	@Test
	public void testWriteFacultyRecords() throws FileNotFoundException {
		LinkedList<Faculty> facultyDirectory = FacultyRecordIO
				.readFacultyRecords("test-files/expected_faculty_records.txt");
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", facultyDirectory);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			fail("Cannot write to faculty directory file");
		}

		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}

	/**
	 * tests writing to a file with no permissions
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 3));
		// Assumption that you are using a hash of "pw" stored in hashPW

		try {
			FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", faculty);
			fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
		} catch (IOException e) {
			assertEquals("/home/sesmith5/actual_faculty_records.txt (Permission denied)", e.getMessage());
			// The actual error message on Jenkins!
		}

	}

	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
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
