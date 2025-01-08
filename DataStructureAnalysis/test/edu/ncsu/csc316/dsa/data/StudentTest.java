package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Student class
 * @author Dr. King
 * @author mhgausi
 */
public class StudentTest {

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

	/** Setup before testing */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/** Tests setFirst */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/** Tests setLast */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/** Tests setId */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/** Tests setGpa */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/** Tests setUnityID */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/** Tests compareTo method */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
	}
	
	/** Tests getCreditHours and setCreditHours */
	@Test
	public void testGetAndSetCreditHours() {
		assertEquals(sOne.getCreditHours(), 1);
		assertEquals(sTwo.getCreditHours(), 2);
		assertEquals(sThree.getCreditHours(), 3);
		assertEquals(sFour.getCreditHours(), 4);
		assertEquals(sFive.getCreditHours(), 5);
		sOne.setCreditHours(12);
		assertEquals(sOne.getCreditHours(), 12);
	}
	
	/** Tests toString */
	@Test
	public void testToString() {
		assertEquals(sOne.toString(), "[OneFirst, OneLast, 1, 1, "
				+ "1.0, oneUnityID]");
		assertEquals(sTwo.toString(), "[TwoFirst, TwoLast, 2, 2, "
				+ "2.0, twoUnityID]");
	}
	
	/** Tests equals() */
	@Test 
	public void testEquals() {
		Student s = sOne;
		assertTrue(s.equals(sOne));
		assertFalse(s.equals(sTwo));
	}
}
