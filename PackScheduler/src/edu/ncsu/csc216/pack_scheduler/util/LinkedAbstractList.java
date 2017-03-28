package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a linked list of objects of type E
 * 
 * @author Caitlyn
 * @author Sam
 * @author Claire
 *
 * @param <E>
 *            the generic parameter so that the list can contain any object type
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** the size of the list */
	private int size;
	/** the number of nodes the list can have */
	private int capacity;
	/** the first node in the list */
	private ListNode front;

	/**
	 * Constructs a linked abstract list and initializes the states
	 * 
	 * @param capacity
	 *            the number of nodes the list can hold
	 */
	public LinkedAbstractList(int capacity) {
		size = 0;
		if (capacity > 0) {
			this.capacity = capacity;
		} else {
			throw new IllegalArgumentException();
		}
		front = null;
	}

	/**
	 * Constructs a linked abstract list and initializes the states
	 */
	public LinkedAbstractList() {
		size = 0;
		capacity = 0;
		front = null;
	}

	/**
	 * Gets the node data at the specified index
	 * 
	 * @param index
	 *            the index to get the data from
	 * @return E the object data at the index specified
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return (E) current.data;
	}

	/**
	 * Returns the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Adds a new node to the list at the specified index and links it to the
	 * nodes before and after it
	 * 
	 * @param index
	 *            the index to add the node at
	 * @param element
	 *            the object that the node will hold
	 */
	@Override
	public void add(int index, E element) {
		if (size == capacity){
			throw new IllegalArgumentException();
		}
		else if (element == null) {
			throw new NullPointerException();
		} else if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode currentCheck = front;
		for (int i = 0; i < size; i++) { //checks entire list for a duplicate
			if (currentCheck.equals(element)) {
				throw new IllegalArgumentException();
			}
			currentCheck = currentCheck.next;
		}
		
		if (index == 0) {
			front = new ListNode(element, front);
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;
	}

	/**
	 * Sets a node at the specified index to a different node, size will stay
	 * the same
	 * 
	 * @param index
	 *            the index to change the node at
	 * @param element
	 *            the object to set the node to
	 * @return E returns the node data that's being set
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {
		E elementToReturn = null;
		if (element == null) {
			throw new NullPointerException();
		} else if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		//if the list is empty
		if (front == null)
		{
			throw new IndexOutOfBoundsException();
		}
		//if the list is not empty
		else if (front != null) 
		{
			ListNode current = front;
			ListNode indexToChange = null;
			if (index == 0) {
				for (int i = 0; i < size; i++) {
					if (current.equals(element)) {
						throw new IllegalArgumentException();
					}
					current = current.next;
				}
				if (front.next == null) {
					elementToReturn = front.data;
					front = new ListNode(element);
				} else {
					elementToReturn = front.data;
					front = new ListNode(element, front.next);
				}
			} else if (index == size) {
				for (int i = 0; i < size - 1; i++) {
					if (current.equals(element)) {
						throw new IllegalArgumentException();
					}
					current = current.next;
				}
				elementToReturn = current.next.data;
				current.next = new ListNode(element, null);
			} else {
				for (int i = 0; i < size; i++) {
					if (i == index - 1) {
						indexToChange = current;
						elementToReturn = (E) indexToChange.next;
					}
					if (current.equals(element)) {
						throw new IllegalArgumentException();
					}
					current = current.next;
				}
				elementToReturn = indexToChange.next.data;
				indexToChange.next = new ListNode(element, indexToChange.next.next);
			}
			
		}
		return elementToReturn; 
	}

	/**
	 * Removes a node from the specified index
	 * 
	 * @param index
	 *            the index to remove the node from
	 * @return E the node that was removed
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode removedNode = null;
		if (index == 0) {
			removedNode = front;
			front = front.next;
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			removedNode = current.next;
			current.next = current.next.next;
		}
		size--;
		return removedNode.data;
	}

	/**
	 * Creates a node that will be held in the outer class LinkedAbstractList
	 * 
	 * @author Caitlyn
	 * @author Sam
	 * @author Claire
	 *
	 */
	private class ListNode {
		/** the object that is being stored in the node */
		private E data;
		/** a reference to the next node in the list */
		private ListNode next;
		
		/**
		 * Constructs a node with an object and no next node reference
		 * 
		 * @param data
		 *            the object being stored
		 */
		public ListNode(E data){
			this.data = data;
			next = null; 
		}

		/**
		 * Constructs a node with an object and a reference to the next node in
		 * the list
		 * 
		 * @param data
		 *            the object being stored
		 * @param next
		 *            the next node in the list
		 */
		public ListNode(E data, ListNode next){
			this.data = data;
			this.next = next;
		}

		/**
		 * Determines if the object in the node is equal to the object passed
		 * into the method
		 * 
		 * @param Object
		 *            the object being checked for a duplicate
		 * @return boolean true if the objects are equal, false otherwise
		 */
		@Override
		public boolean equals(Object data) {
			return this.data == data;
		}
	 }
	
}
