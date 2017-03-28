/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

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
<<<<<<< HEAD
	public ArrayQueue(int capacity){
=======
	public ArrayQueue(int capacity) {
>>>>>>> refs/remotes/origin/master
		this.list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * adds an element to the back of the queue
	 */
	@Override
	public void enqueue(E element) {
		if(this.size() == this.capacity){
			throw new IllegalArgumentException();
		}
		this.list.add(this.size()-1, element);
	}
	
	/**
	 * removes an element from the front of the queue and returns it
	 */
	@Override
	public E dequeue() {
		return this.list.remove(0);
	}
	
	/**
	 * returns true if list is empty, false if not
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

	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < this.size()){
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
