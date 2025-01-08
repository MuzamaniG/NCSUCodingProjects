package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the StudentGPAComparator class
 * @author Dr. King
 * @author mhgausi
 */
public class StudentGPAComparatorTest {

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

	/** Comparator to be used */
	private StudentGPAComparator comparator;

	/** Setup before testing */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 4.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}

	/** Tests the compare method */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertEquals(comparator.compare(sFive, sTwo), -1);
		assertEquals(comparator.compare(sThree, sFour), 1);
		assertEquals(comparator.compare(sFour, sFive), 0);
	}

}
