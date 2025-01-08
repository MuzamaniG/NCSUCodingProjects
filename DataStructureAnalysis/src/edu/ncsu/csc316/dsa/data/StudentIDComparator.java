package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 * @author mhgausi
 *
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order
	 * @param one student being compared to student two
	 * @param two student being compared to student one
	 * @return int -1 if one comes before two, 1 if one comes after two,
	 * and 0 if one and two are equal
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getId() < two.getId()) {
			return -1;
		}
		else if (one.getId() > two.getId()) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
