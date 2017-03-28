/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * describes behavior for a queue
 * 
 * @author Lauren
 * 
 * @param <E>
 *            generic parameter
 */
public interface Queue<E> {
	
	/**
	 * adds an element to the back of the queue
	 * @param element to add
	 */
	public void enqueue(E element) throws IllegalArgumentException;
	
	/**
	 * removes and returns element at front of queue
	 * @return element removed
	 */
	public E dequeue() throws NoSuchElementException;
	
	/**
	 * returns true if queue is empty, false if not
	 * @return boolean true if empty false if not
	 */
	public boolean isEmpty();
	
	/**
	 * returns the number of elements in the queue
	 * @return size
	 */
	public int size();
	
	/**
	 * sets the queue's capacity
	 * @param capacity to set
	 */
	public void setCapacity(int capacity) throws IllegalArgumentException;
	
}
