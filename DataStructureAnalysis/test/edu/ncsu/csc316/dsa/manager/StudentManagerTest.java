package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;

/** 
 * Tests the StudentManager class
 * @author Dr. King
 * @author mhgausi
 */
public class StudentManagerTest {

	/** StudentManager instance to be used for testing */
	private StudentManager sm;
	/** StudentManager instance to be used for testing with ID comparator */
	private StudentManager smCustomId;
	/** StudentManager instance to be used for testing with GPA comparator */
	private StudentManager smCustomGpa;
	/** Sorter of students to test StudentIDComparator */
	private InsertionSorter<Student> studentIdSorter;
	/** Sorter of students to test StudentGPAComparator */
	private InsertionSorter<Student> studentGpaSorter;
	
	/** Setup before testing */
	@Before
	public void setUp() {
		studentIdSorter = new InsertionSorter<Student>(new StudentIDComparator());
		studentGpaSorter = new InsertionSorter<Student>(new StudentGPAComparator());
		sm = new StudentManager("input/student_ascendingID.csv");
		smCustomId = new StudentManager("input/student_descendingID.csv", studentIdSorter);
		smCustomGpa = new StudentManager("input/student_ascendingID.csv", studentGpaSorter);
	}
	
	/** Tests sort method */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	
	/** Tests the use of StudentGPAComparator with the StudentManager */
	@Test
	public void testSortByGpa() {
		Student[] sorted = smCustomGpa.sort();
		assertEquals("Nichole", sorted[0].getFirst());
		assertEquals("Lewis", sorted[15].getFirst());

	}
	
	/** Tests the use of StudentIdComparator with the StudentManager */
	@Test
	public void testSortById() {
		Student[] sorted = smCustomId.sort();
		assertEquals("Amber", sorted[0].getFirst());
		assertEquals("Dante", sorted[15].getFirst());
	}
}
