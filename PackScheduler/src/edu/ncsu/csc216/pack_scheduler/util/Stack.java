package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * The stack interface that will be implemented by ArrayStack and LinkedStack,
 * holds usual methods
 * 
 * @author Caitlyn
 *
 * @param <E>
 *            generic type so this interface can be used for many object types
 */
public interface Stack<E> {
	/**
	 * Adds an element to the top of the stack
	 * 
	 * @param element
	 *            the element to be added to the stack
	 * @throws IllegalArgumentException
	 *             if there is no room (capacity has been reached)
	 */
	public void push(E element) throws IllegalArgumentException;

	/**
	 * Returns the object on the top of the stack and removes it from the stack
	 * 
	 * @return the element removed from the top of the stack
	 * @throws EmptyStackException
	 *             if the stack is empty and there are no elements to remove
	 */
	public E pop() throws EmptyStackException;

	/**
	 * Tells if the stack is empty or not
	 * 
	 * @return true if the stack contains no elements
	 */
	public boolean isEmpty();

	/**
	 * Returns the size of the stack
	 * 
	 * @return the number of elements in the stack
	 */
	public int size();

	/**
	 * Sets the capacity of the stack (the max number of elements allowed)
	 * 
	 * @param capacity
	 *            new capacity to set for the stack
	 * @throws IllegalArgumentException
	 *             if new capacity is negative or less than the size of the
	 *             stack
	 */
	public void setCapacity(int capacity) throws IllegalArgumentException;
}
