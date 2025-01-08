package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author mhgausi
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }
    
    private static class LinkedListNode<E> {
    	
    	/** Element contained in each node */
        private E element; 
        /** Next node in the list */
        private LinkedListNode<E> next;
        
        public LinkedListNode(E element) {
            this.element = element;
            this.next = null;
        }

        public E getElement() {
            return element;
        }

        public LinkedListNode<E> getNext() {
            return next;
        }
    }

	@Override
	public void add(int index, E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        
        LinkedListNode<E> prev = front;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        
        LinkedListNode<E> newNode = new LinkedListNode<>(element);
        newNode.next = prev.next;
        prev.next = newNode;
        
        if (newNode.next == null) {
            tail = newNode;
        }
        
        size++;
	}

	@Override
	public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        LinkedListNode<E> current = front.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.element;
	}

	@Override
	public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } 
        LinkedListNode<E> prev = front;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        LinkedListNode<E> node = prev.next;
        prev.next = node.next;
        if (node.next == null) {
            tail = prev;
        }
        size--;
        return node.element;
	}

	@Override
	public E set(int index, E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedListNode<E> current = front.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldValue = current.element;
        current.element = element;
        return oldValue;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
    /**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.getElement();
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        LinkedListNode<E> newNode = new LinkedListNode<>(element);
        if (size == 0) {
            front.next = newNode;
        } 
        else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }
    
    private class ElementIterator implements Iterator<E> {
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        
        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
        	this.current = front.getNext();
        }

        @Override
        public boolean hasNext() {
        	return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            current = current.next;
            return element;
        }
         
        @Override    
        public void remove() {
    	    // DO NOT CHANGE THIS METHOD
            throw new UnsupportedOperationException 
            	("This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
        }
    }
    
}
