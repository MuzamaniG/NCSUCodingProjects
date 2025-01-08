package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author mhgausi
 * 
 * @param <E> generic objects to be sorted
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Default constructor for InsertionSorter
	 */
    public InsertionSorter() {
        super(null);
    }
    
    /**
     * Constructor for InsertionSorter; sets the comparator that should be used
     * @param comparator to be used
     */
    public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
	/**
	 * Sorts generic objects within an array 
	 * @param data generic object array containing objects to be sorted
	 */
    @Override
	public void sort(E[] data) {
		for (int i = 1; i <= data.length - 1; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && compare(data[j], x) > 0) { //checks if the previous index j has a larger int value
				data[j + 1] = data[j];
				j--;
			}
			data[j + 1] = x;
		}
	}
}
