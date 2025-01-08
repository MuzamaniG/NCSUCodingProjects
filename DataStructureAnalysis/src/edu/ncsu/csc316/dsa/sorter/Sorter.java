package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author mhgausi
 * 
 * @param <E> generic objects to be sorted
 */
public interface Sorter<E> {
	
	/**
	 * Sorts generic objects within an array
	 * @param data object array containing objects to be sorted
	 */
	void sort(E[] data);

}
