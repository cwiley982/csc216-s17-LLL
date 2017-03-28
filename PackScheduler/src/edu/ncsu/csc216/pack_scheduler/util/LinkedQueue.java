/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * @author lsiegel
 * @param <E>
 *
 */
public class LinkedQueue<E> implements Queue<E> {
	
	private LinkedAbstractList<E> list;
	
	/**
	 * constructs a LinkedQueue
	 */
	public LinkedQueue(int capacity) {
		this.list = new LinkedAbstractList<E>();
		setCapacity(capacity);
	}
	/**
	 * adds an element to the back of the queue
	 */
	@Override
	public void enqueue(E element) throws IllegalArgumentException {
		this.list.add(element);
	}
	
	/**
	 * removes an element from the front of the queue and returns it
	 */
	@Override
	public E dequeue() throws NoSuchElementException {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}
	
	/**
	 * returns true if queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	/**
	 * returns the size of the queue
	 */
	@Override
	public int size() {
		return this.list.size();
	}
	
	/**
	 * sets the capacity of the queue
	 */
	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		this.list.setCapacity(capacity);
	}

}
