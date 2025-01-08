package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author mhgausi 
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

	@Override
	public void sort(E[] data) {
		if (data.length < 2) {
			return;
		}
		int mid = data.length / 2;
        @SuppressWarnings("unchecked")
		E[] left = (E[]) new Comparable[mid]; // = copyArray(data, 0, mid);
        @SuppressWarnings("unchecked")
		E[] right = (E[]) new Comparable[data.length - mid];
        for (int i = 0; i < data.length; i++) {
        	if (i < mid) {
        		left[i] = data[i];
        	}
        	if (i >= mid) {
        		right[i - mid] = data[i];
        	}
        }
        sort(left);
        sort(right);
        
        merge(left, right, data);
		
	}
	
	/**
	 * Merges two arrays for the purpose of sorting
	 * @param left first array, elements that should be on the left
	 * @param right second array, elements that should be on the right
	 * @param data third array, full array that will be sorted
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int leftIndex = 0;
		int rightIndex = 0;
		while (leftIndex + rightIndex < data.length) {
			if (rightIndex == right.length || (leftIndex < left.length &&
					compare(left[leftIndex], right[rightIndex]) < 0)) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex++;
			}
			else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex++;
			}
		}
	}
}