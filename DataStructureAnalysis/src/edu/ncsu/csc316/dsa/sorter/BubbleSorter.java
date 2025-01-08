package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter uses the bubble sorting algorithm to sort data
 * @author mhgausi
 *
 * @param <E> the generic type of data to sort
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E>  {

	/**
	 * BubbleSorter constructor; sets the comparator to be used
	 * @param comparator to be used
	 */
    public BubbleSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
	/**
	 * Default constructor for SelectionSorter
	 */
    public BubbleSorter() {
        super(null);
    }
    
	/**
	 * Sorts data using comparisons with bubble sort algorithm; overrides default sort method
	 * @param data array of generic objects to be sorted
	 */
	@Override
	public void sort(E[] data) {
		boolean r = true;
		while (r) {
			r = false;
			for (int i = 1; i <= data.length - 1; i++) {
				if (compare(data[i], data[i - 1]) < 0) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
	}
}
