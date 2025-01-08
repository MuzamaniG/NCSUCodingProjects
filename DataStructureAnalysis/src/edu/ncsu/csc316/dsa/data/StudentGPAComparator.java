package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 * @author mhgausi
 *
 */
public class StudentGPAComparator implements Comparator<Student> { 

	/**
	 * Compares students based on GPA in descending order
	 * @param one student being compared to student two
	 * @param two student being compared to student one
	 * @return int -1 if one comes before two, 1 if one comes after two,
	 * and 0 if one and two are equal
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getGpa() > two.getGpa()) {
			return -1;
		}
		else if (one.getGpa() < two.getGpa()) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
