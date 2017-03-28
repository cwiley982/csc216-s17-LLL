package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Implements the Stack interface using LinkedAbstractList
 * 
 * @author Caitlyn
 *
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {

	private LinkedAbstractList<E> list;
	/**
	 * Constructs a LinkedStack and sets the initial capacity
	 * 
	 * @param capacity
	 *            the initial capacity of the stack
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>();
		setCapacity(capacity);
	}

	@Override
	public void push(E element) throws IllegalArgumentException {
		// if (size() + 1 > capacity) { // TODO fix this
		// throw new IllegalArgumentException();
		// }
		list.add(size(), element);
	}

	@Override
	public E pop() throws EmptyStackException {
		if (size() == 0) {
			throw new EmptyStackException();
		}
		return (E) list.remove(size() - 1);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		list.setCapacity(capacity);

	}

}
