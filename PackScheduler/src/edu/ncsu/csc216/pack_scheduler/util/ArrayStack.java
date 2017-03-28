package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {

	private ArrayList<E> list;
	private int capacity;

	/**
	 * Constructs a stack by using an ArrayList
	 * 
	 * @param capacity
	 *            the initial capacity of the stack
	 */
	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}

	@Override
	public void push(E element) throws IllegalArgumentException {
		if (size() + 1 > capacity) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < size(); i++) {
			if (element.equals(list.get(i))) {
				throw new IllegalArgumentException();
			}
		}

		list.add(size(), element);
	}

	@Override
	public E pop() throws EmptyStackException {
		if (size() == 0) {
			throw new EmptyStackException();
		}
		return list.remove(size() - 1);
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

		this.capacity = capacity;
	}

}
