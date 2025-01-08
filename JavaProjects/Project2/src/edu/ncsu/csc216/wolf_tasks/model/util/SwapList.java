package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Class for a list that changes the position of elements through
 * swap operations. Implements ISwapList interface
 * 
 * @author mhgausi
 * @author ojain
 *
 * @param <E> type for the ISwapList
 */
public class SwapList<E> implements ISwapList<E> {
	
	/** Initial capacity of SwapList */
	private static final int INITIAL_CAPACITY = 10;
	
	/** Array of generic objects for SwapList */
	private E[] list;
	
	/** Size of SwapList */
	private int size;
	
	/** 
	* Constructor a SwapList as an array-based list with initial
	* capacity 10 and initial size 0.
	*/
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	
	/**
	* Adds the element to the end of the list.
	* @param element element to add
	* @throws NullPointerException if element is null
	* @throws IllegalArgumentException if element cannot be added 
	*/
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		checkCapacity(size + 1);
		list[size] = element;
		size++;
	}
	
	/**
	* Checks the capacity of the list and grows it if needed.
	* @param capacity the capacity needed
	*/
	@SuppressWarnings("unchecked")
	private void checkCapacity(int capacity) {
		E[] arr = null;
		if (capacity > 10) {
			arr = (E[]) new Object[capacity];
			for (int i = 0; i < capacity - 1; i++) {
				arr[i] = list[i];
			}
			list = arr;
		}
	}
	
	/**
	* Returns the element from the given index.  The element is
	* removed from the list.
	* @param idx index to remove element from
	* @return element at given index
	* @throws IndexOutOfBoundsException if the idx is out of bounds
	* 		for the list
	*/
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E element = list[idx];
		for (int i = idx; i < (size - 1); i++) {
			list[i] = list[i + 1];
		}
		size--;
		return element;
	}
	
	/**
	* Checks if the index parameter is out of bounds.
	* @param idx index being checked 
	* @throws IndexOutOfBoundsException for invalid index
	*/
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveUp(int idx) {
		checkIndex(idx);
		if (idx != 0) {
			E temp = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = temp;
		}
	}
	
	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveDown(int idx) {
		checkIndex(idx);
		if (idx != size - 1) {
			E temp = list[idx + 1];
			list[idx + 1] = list[idx];
			list[idx] = temp;
		}
	}
	
	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveToFront(int idx) {
		checkIndex(idx);
		if (idx != 0) {
			E temp = list[idx];
			for (int i = idx; i > 0; i--) {
				list[i] = list[i - 1];
			}
			list[0] = temp;
		}
	}
	
	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveToBack(int idx) {
		checkIndex(idx);
		if (idx != size - 1) {
			E temp = list[idx];
			for (int i = idx; i < (size - 1); i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = temp;
		}
	}
	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return size;
	}

}