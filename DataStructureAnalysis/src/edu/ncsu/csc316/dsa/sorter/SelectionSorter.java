package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/**
	 * SelectionSorter constructor; sets the comparator to be used
	 * @param comparator to be used
	 */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
	/**
	 * Default constructor for SelectionSorter
	 */
    public SelectionSorter() {
        super(null);
    }
    
    /**
     * Sorts generic objects in an array
     * @param data generic object array containing objects to be sorted
     */
    @Override
    public void sort(E[] data) {
        for (int i = 0; i <= data.length - 1; i++) {
        	int min = i;
        	for (int j = i + 1; j <= data.length - 1; j++) {
        		if (compare(data[j], data[min]) < 0) {
        			min = j;
        		}
        	}
        	if (i != min) {
        		E x = data[i];
        		data[i] = data[min];
        		data[min] = x;
        	}
        }
    }
}