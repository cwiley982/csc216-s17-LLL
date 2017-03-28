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
public class ArrayQueue<E> implements Queue<E> {
	
	private ArrayList<E> list;
	
	private int capacity;
	
	/**
	 * constructs an ArrayQueue
	 */
    public ArrayQueue(int capacity){
		this.list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * adds an element to the back of the queue
	 */
	@Override
	public void enqueue(E element) throws IllegalArgumentException {
		if (size() > this.capacity) {
			throw new IllegalArgumentException();
		}
		list.add(this.size(), element);
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
	 * returns true if list is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * returns the size of the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
