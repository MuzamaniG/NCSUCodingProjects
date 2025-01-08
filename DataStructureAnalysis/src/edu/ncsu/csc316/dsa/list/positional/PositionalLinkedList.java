package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author mhgausi
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

    /**
     * PositionalNode class; implements Position interface
     * @param <E> generic object contained in each node
     */
    private static class PositionalNode<E> implements Position<E> {

    	/** Element contained in node */
        private E element;
        /** Next node in list */
        private PositionalNode<E> next;
        /** Previous node in list */
        private PositionalNode<E> previous;

        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * Constructs the PositionalNode with a generic object and the next node
         * @param value generic object in node
         * @param next node
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * Constructs the PositionalNode with a generic object, next node, and previous node
         * @param value generic object stored
         * @param next node
         * @param prev previous node
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * Sets the previous node
         * @param prev previous node
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Gets the previous node
         * @return previous node
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * Gets the next PositionalNode
         * @return next PositionalNode 
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the current element
         * @param element to be set
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /** 
     * Inserts an element between two elements
     * @param element being added
     * @param next node after element
     * @param prev node before element
     * @return node of new element
     */
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
        PositionalNode<E> newNode = new PositionalNode<>(element, next, prev);
        prev.setNext(newNode);
        next.setPrevious(newNode);
        size++;
        return newNode;
    }

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
        PositionalNode<E> node = validate(p);
        return addBetween(element, node.getNext(), node);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
        PositionalNode<E> node = validate(p);
        return addBetween(element, node, node.getPrevious());
	}

	@Override
	public Position<E> addFirst(E element) {
		return addBetween(element, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}

	@Override
	public Position<E> after(Position<E> p) {
        PositionalNode<E> node = validate(p);
        PositionalNode<E> nextNode = node.getNext();
        if (nextNode == tail) {
            return null;
        } 
        else {
            return nextNode;
        }
	}

	@Override
	public Position<E> before(Position<E> p) {
        PositionalNode<E> node = validate(p);
        PositionalNode<E> prevNode = node.getPrevious();
        if (prevNode == front) {
            return null;
        } 
        else {
            return prevNode;
        }
	}

	@Override
	public Position<E> first() {
        if (isEmpty()) {
            return null;
        } 
        else {
            return front.getNext();
        }
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
	       if (isEmpty()) {
	            return null;
	       } 
	       else {
	            return tail.getPrevious();
	       }
	}

    /**
     * Returns a new Iterable collection of list Positions
     * 
     * @return a new Iterable collection of list positions
     */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

    /**
     * Removes the position p from the list and returns the element stored at p.
     * 
     * @param p the position to remove from the list
     * @return the element stored at p
     * @throws IllegalArgumentException if the provided position, p, is not a valid
     *                                  position
     */
	@Override
	public E remove(Position<E> p) {
        PositionalNode<E> node = validate(p);
        PositionalNode<E> prevNode = node.getPrevious();
        PositionalNode<E> nextNode = node.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrevious(prevNode);
        size--;
        E element = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrevious(null);
        return element;
	}

	@Override
	public E set(Position<E> p, E element) {
        PositionalNode<E> node = validate(p);
        E oldElement = node.getElement();
        node.setElement(element);
        return oldElement;
	}

	@Override
	public int size() {
		return size;
	}

	/** 
	 * ElementIterator class; implements Iterator 
	 */
    private class ElementIterator implements Iterator<E> {

    	/** Iterator being used */
        private Iterator<Position<E>> it;

        public ElementIterator() {
            it = new PositionIterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
    
    /**
     * PositionIterator class; implements Iterator
     */
    private class PositionIterator implements Iterator<Position<E>> {

        /** Current position */
        private PositionalNode<E> current = front.getNext();
        /** Previous position */
        private PositionalNode<E> previous = null;
        /** Whether iterator is ok to be removed */
        private boolean removeOK = false;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public Position<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            previous = current;
            current = current.getNext();
            removeOK = true;
            return previous;
        }

        @Override
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            PositionalNode<E> nodeToRemove = previous;
            current = nodeToRemove.getNext(); 
            PositionalLinkedList.this.remove(nodeToRemove);
            previous = null;
            removeOK = false;
        }
    }
    
    /**
     * PositionIterable class; implements Iterable 
     */
    private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
}
