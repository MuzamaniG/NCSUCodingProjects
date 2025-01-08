package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.LastElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.MiddleElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.FirstElementSelector;

/**
 * Tests the QuickSorter class
 * @author Dr. King
 * @author mhgausi
 */
public class QuickSorterTest {

	/** Integers in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** Integers in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** Integers in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** Array of Students to be tested */
	private Student[] students;
	
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
	
	/** Sorter of integers */
	private QuickSorter<Integer> integerSorter;
	/** Sorter of students */
	private QuickSorter<Student> studentSorter;

	/** Setup before testing */
	@Before
	public void setUp() {
		integerSorter = new QuickSorter<>();
		studentSorter = new QuickSorter<Student>(new StudentIDComparator());
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		students = new Student[] {sFour, sThree, sFive, sOne, sTwo};
	}

	/** Tests algorithm for sorting integers */
	@Test
	public void testSortIntegers() {
		Integer[] expectedResults = { 1, 2, 3, 4, 5 };
		integerSorter.sort(dataAscending);
        assertArrayEquals(expectedResults, dataAscending);

        integerSorter.sort(dataDescending);
        assertArrayEquals(expectedResults, dataDescending);

        integerSorter.sort(dataRandom);
        assertArrayEquals(expectedResults, dataRandom);
	}

	/** Tests algorithm for sorting Student objects */
	@Test
	public void testSortStudent() {
        studentSorter.sort(students);
        
        assertEquals(sOne, students[0]);
        assertEquals(sTwo, students[1]);
        assertEquals(sThree, students[2]);
        assertEquals(sFour, students[3]);
        assertEquals(sFive, students[4]);

	}
	
	/** Tests the different PivotSelector classes */
	@Test
	public void testPivotSelector() {
		LastElementSelector lastSelector = new LastElementSelector();
		MiddleElementSelector middleSelector = new MiddleElementSelector();
		FirstElementSelector firstSelector = new FirstElementSelector();
		
		assertEquals(lastSelector.selectPivot(1, 15), 15);
		assertEquals(middleSelector.selectPivot(1, 15), 8);
		assertEquals(firstSelector.selectPivot(1, 15), 1);
		
	}
}