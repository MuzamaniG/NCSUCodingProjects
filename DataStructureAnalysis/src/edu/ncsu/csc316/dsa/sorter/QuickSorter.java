package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 * @author mhgausi
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/** PivotSelector type to be used by the QuickSorter */
	 private PivotSelector selector;

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }
    
    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }
    
    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }
    
    /**
     * Sets the selector to be used by the QuickSorter
     * @param selector being used
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }

	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
	}
	
	/**
	 * QuickSort algorithm which moves lower values to the left of a given object and the higher values to the right
	 * @param data array of generic objects
	 * @param low lower index being swapped
	 * @param high higher index being swapped
	 */
	private void quickSort(E[] data, int low, int high) {
		int pivotLocation;
		if (low < high) {
			pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}
	
	/**
	 * Swaps data based on the type of pivot being used
	 * @param data array of generic objects
	 * @param low lower index being swapped
	 * @param high higher index being swapped
	 * @return int that can be used as the pivot location for quickSort
	 */
	private int partition(E[] data, int low, int high) { 
		int pivotIndex = selector.selectPivot(low, high);
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}

	/**
	 * Swaps the low and high value indexes in the array
	 * @param data array of generic objects
	 * @param low lower index being swapped
	 * @param high higher index being swapped
	 */
	private void swap(E[] data, int low, int high) {
        E temp = data[low];
        data[low] = data[high];
        data[high] = temp;
	}
	
	/** 
	 * Aids the partition method by returning an int value for its method given a generic object array, and low/high indices
	 * @param data array of generic objects
	 * @param low lower index being swapped
	 * @param high higher index being swapped
	 * @return int for the final partition
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int separator = low;
		for (int j = low; j <= high - 1; j++) {
			if (compare(data[j], pivot) <= 0) {
				swap(data, separator, j);
				separator++;
			}
		}
		swap(data, separator, high);
		return separator;
	}
    /**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class FirstElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * LastElementSelector chooses the second index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author mhgausi
     *
     */
    public static class LastElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return high;
        }
    }
    
    /**
     * MiddleElementSelector chooses the middle index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author mhgausi
     *
     */
    public static class MiddleElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return (low + high) / 2;
        }
    }
    
    /**
     * RandomElementSelector chooses the a random index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author mhgausi
     *
     */
    public static class RandomElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return new Random().nextInt(high - low + 1) + low;
        }
    }

}