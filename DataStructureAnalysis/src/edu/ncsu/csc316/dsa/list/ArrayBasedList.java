package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author mhgausi
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }
    
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    /**
     * Adds generic object to given index
     * @param index of added object
     * @param value generic object being added
     * @throws NullPointerException if generic object is null
     * @throws IndexOutOfBoundsException if index is negative or greater than to size
     */
    public void add(int index, E value) {
        if (value == null) {
            throw new NullPointerException();
        } 
        else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(size + 1);
        if (size != 0) {
            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
        }

        data[index] = value;
        size++;
		
    }
    
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    /**
     * Removes generic object E from index
     * @param index of generic object to be removed
     * @return E object that was removed
     * @throws IndexOutOfBoundsException if index is negative or greater than or equal to size
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E value = data[index];
        for (int i = index; i < size - 1; i++) {
        	data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return value;
    }
    
    /**
     * Gets generic object from given index
     * @param index of object being returned
     * @return E object being returned from index
     * @throws IndexOutOfBoundsException if index is negative or greater than or equal to size
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
		if (data[index] != null) {
			return data[index];
		}
        return null;
    }
    
    /**
     * Sets generic object to given index of ArrayBasedList
     * @param index of object being set to index
     * @param value generic object being set to index
     * @return E object set to index
     */
    public E set(int index, E value) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (value == null) {
			throw new NullPointerException();
		}  
		E object = data[index];   
		data[index] = value;
		return object;
		
    }
    
    /** 
     * Returns the current size of the ArrayBasedList
     * @return int size of ArrayBasedList
     */
    public int size() {
    	return size;
    }
    
    private class ElementIterator implements Iterator<E> {
    	/** Iterator's position */
        private int position;
        /** Whether current element can be removed */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            this.position = 0;
            this.removeOK = false;
        }

        /**
         * Checks if the iteration has more elements
         * @return true if iteration has more elements based on current position
         */
        @Override
        public boolean hasNext() {
        	return position < size;     
        }

        /** 
         * Returns the next generic object element in the iteration
         * @return next generic object
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            removeOK = true;
            return data[position++];
        }
            
        /** 
         * Removes the current position in the iterator
         */
        @Override
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            for (int i = position - 1; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
            data[size - 1] = null; 
            size--;
            removeOK = false;
            if (position > 0) {
                position--;
            }
        }
    }
}
