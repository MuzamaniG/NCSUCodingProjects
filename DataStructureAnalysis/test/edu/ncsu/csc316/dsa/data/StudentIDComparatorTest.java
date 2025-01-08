package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the StudentIDComparator class 
 * @author Dr. King
 * @author mhgausi
 */
public class StudentIDComparatorTest {

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
	private StudentIDComparator comparator;

	/** Setup before testing */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/** Tests the compare method */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sTwo, sThree) < 0);
		assertTrue(comparator.compare(sThree, sFour) < 0);
		assertTrue(comparator.compare(sFour, sFive) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sFive, sFour) < 0);
		assertFalse(comparator.compare(sThree, sTwo) < 0);
		assertFalse(comparator.compare(sFour, sThree) < 0);
		
		assertFalse(comparator.compare(sFour, sThree) == 0);
		sFour.setId(3);
		assertEquals(comparator.compare(sFour, sThree), 0);

	}


}
