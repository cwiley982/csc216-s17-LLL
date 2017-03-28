/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

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
	public LinkedQueue(){
		this.list = new LinkedAbstractList<E>();
	}
	/**
	 * adds an element to the back of the queue
	 */
	@Override
	public void enqueue(E element) {
		this.list.add(element);
	}
	
	/**
	 * removes an element from the front of the queue and returns it
	 */
	@Override
	public E dequeue() {
		return this.list.remove(0);
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
	public void setCapacity(int capacity) {
		this.list.setCapacity(capacity);
	}

}
