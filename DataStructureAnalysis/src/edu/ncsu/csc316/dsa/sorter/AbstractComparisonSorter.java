package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Abstract class for comparison based sorters; extends Comparable and implements Sorter interface
 * @author mhgausi
 * @param <E> generic objects become sorted
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** Comparator to be used */
    private Comparator<E> comparator;
    
    /** 
     * Default constructor for sorters; sets the type of comparator to be used 
     * @param comparator to be used
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Sets the comparator to be used
     * @param comparator to be used
     */
    private void setComparator(Comparator<E> comparator) {
        if (comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
	/**
	 * Private class for the natural ordering of objects if the client
	 * does not specify a Comparator object to use
	 */
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * Compares two generic objects 
     * @param first object being compared
     * @param second object being compared
     * @return int value based on comparison
     */
    public int compare(E first, E second) {
        return comparator.compare(first,  second);
    }
}
