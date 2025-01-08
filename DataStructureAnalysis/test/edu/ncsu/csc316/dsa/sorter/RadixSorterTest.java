package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/** 
 * Tests the RadixSorter class
 * @author Dr. King
 * @author mhgausi
 */
public class RadixSorterTest {
	
	/** First student being tested */
	private Student sOne;
	/** Second student being tested */
	private Student sTwo;
	/** Third student being tested */
	private Student sThree;
	/** Fourth student being tested */
	private Student sFour;
	/** Fifth student being tested */
	private Student sFive;
	/** Sorter of students to be used */
	private RadixSorter<Student> sorter;

	/** Necessary setup before testing */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new RadixSorter<Student>();
	}
	
	/** Tests sorting students using RadixSorter */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
		
		Student[] unoriginal = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(unoriginal);
		assertEquals(sOne, unoriginal[0]);
		assertEquals(sTwo, unoriginal[1]);
		assertEquals(sThree, unoriginal[2]);
		assertEquals(sFour, unoriginal[3]);
		assertEquals(sFive, unoriginal[4]);
	}
}