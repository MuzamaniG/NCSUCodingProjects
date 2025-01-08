package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Class for a list that keeps objects in sorted order as defined by the
 * Comparable interface.
 * Implements ISortedList interface
 * 
 * @author mhgausi
 * @author ojain
 * 
 * @param <E> type for ISortedList; must implement Comparable
 */
public class SortedList<E extends Comparable<E>>  implements ISortedList<E> {

	/**
	* ListNode class; Used to contain SortedList data 
	* @author mhgausi
	* @author ojain
	*/
	public class ListNode {
		/** Object data contained in ListNode */
		public E data;
		/** Reference to the next node */
		public ListNode next;
		/** 
		* Constructor for ListNode given E element and another ListNode
		* @param element E object
		* @param node next ListNode
		*/
		public ListNode(E element, ListNode node) {
			this.data = element;
			this.next = node;
		}
		
	}
	
	/** Size of SortedList */
	private int size;
	/** First node contained in SortedList */
	private ListNode front;
	
	/**
	* Constructor for SortedList 
	*/
	public SortedList() {
		size = 0;
		front = null;
	}
	
	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added due to null or duplicate element
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		if (front == null || element.compareTo(front.data) <= 0) {
			front = new ListNode(element, front);
		} 
		else {
			ListNode current = front;
			while (current.next != null && element.compareTo(current.next.data) > 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;
		
	}
	
	/**
	* Returns the element from the given index.  The element is
	* removed from the list.
	* @param idx index to remove element from
	* @return element at given index
	* @throws IndexOutOfBoundsException if the idx is out of bounds
	* 		for the list
	*/
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		E element; 
		if (idx == 0) {
			element = front.data;
			front = front.next; 
			size--;
			return element; 
		}
		else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
			    current = current.next;
			}
			element = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return element;
			
	}
	
	/**
	* Checks given index for element 
	* @param idx index being checked
	*/
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	/**
	* Returns true if the element is in the list.
	* @param element element to search for
	* @return true if element is found
	*/
	@Override
	public boolean contains(E element) {
		ListNode current = front;
		while (current != null) {
			if (current.data.equals(element)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	/**
	* Returns the element at the given index.
	* @param idx index of the element to retrieve
	* @return element at the given index
	* @throws IndexOutOfBoundsException if the idx is out of bounds
	* 		for the list
	*/
	@Override
	public E get(int idx) {
		checkIndex(idx);
		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	/**
	* Returns the number of elements in the list.
	* @return number of elements in the list
	*/
	@Override
	public int size() {
		return size;
	}
	
}