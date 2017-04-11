package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Recursive implementation of a LinkedList
 * @author Lauren
 *@param <E> element
 *
 */
public class LinkedListRecursive<E> {
	
	/** size of the list */
	private int size;
	
	/**
	 * constructs a LinkedListRecursive
	 */
	public LinkedListRecursive() {
		
	}
	
	/**
	 * returns whether or not the list is empty
	 * @return boolean true if empty, false if not
	 */
	public boolean isEmpty() {
		return false;
	}
	
	/**
	 * returns the size of the list
	 * @return int size
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * adds an element to the end of the list
	 * @param element to add
	 * @return true if added, false if not
	 */
	public boolean add(E element) {
		return false;
	}
	
	/**
	 * adds an element at the specified index
	 * @param index to add element
	 * @param element to add
	 */
	public void add(int index, E element) {
		
	}
	
	/**
	 * returns the element at the specified index
	 * @param index index of element
	 * @return element
	 */
	public E get(int index) {
		return null;
	}
	
	/**
	 * removes an element from the list
	 * @param element to remove
	 * @return true if removed, false if not
	 */
	public boolean remove(E element) {
		return false;
	}
	
	/**
	 * removes an element from the list and returns it
	 * @param index of element
	 * @return element
	 */
	public E remove(int index) {
		return null;
	}
}
