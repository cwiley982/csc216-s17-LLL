package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests the functionality of the ArrayStack class
 * 
 * @author Caitlyn
 *
 * @param <E>
 *            the generic parameter so any object can be used
 */
public class ArrayStackTest<E> {

	/**
	 * Tests adding one element to the stack
	 */
	@Test
	public void testAddOneElement() {
		ArrayStack<String> stack = new ArrayStack<String>();
		try {
			stack.push("hello");
			assertEquals(1, stack.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests adding multiple elements to the stack
	 */
	@Test
	public void testAddMultipleElements() {
		ArrayStack<String> stack = new ArrayStack<String>();
		try {
			stack.push("one");
			stack.push("two");
			stack.push("three");
			assertEquals(3, stack.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests removing one element from the stack
	 */
	@Test
	public void testRemoveOneElement() {
		ArrayStack<String> stack = new ArrayStack<String>();
		stack.push("one");
		assertEquals("one", stack.pop());
	}

	/**
	 * Tests removing multiple elements from the stack
	 */
	@Test
	public void testRemoveMultipleElements() {
		ArrayStack<String> stack = new ArrayStack<String>();
		stack.push("one");
		stack.push("two");
		stack.push("three");
		assertEquals(3, stack.size());
		stack.pop();
		assertEquals("two", stack.pop());
	}

	/**
	 * Tests removing the last element from the stack
	 */
	@Test
	public void testRemoveLastElement() {
		ArrayStack<String> stack = new ArrayStack<String>();
		stack.push("one");
		stack.push("two");
		stack.push("three");
		assertEquals(3, stack.size());
		stack.pop();
		stack.pop();
		assertEquals("one", stack.pop());
		assertTrue(stack.isEmpty());
	}

	/**
	 * Tests removing an element from an empty stack
	 */
	@Test
	public void testRemoveFromEmptyStack() {
		ArrayStack<String> stack = new ArrayStack<String>();
		try {
			stack.pop();
			fail();
		} catch (EmptyStackException e) {
			assertTrue(stack.isEmpty());
		}
	}

	/**
	 * Tests setting the stack capacity to its current size
	 */
	@Test
	public void testSetCapToSize() {
		ArrayStack<String> stack = new ArrayStack<String>();
		try {
			stack.setCapacity(stack.size());
			assertTrue(stack.isEmpty());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests setting the capacity to less than the current size and setting
	 * capacity to a negative number
	 */
	@Test
	public void testSetInvalidCap() {
		ArrayStack<String> stack = new ArrayStack<String>();
		stack.push("one");
		stack.push("two");
		try {
			stack.setCapacity(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, stack.size());
		}

		ArrayStack<String> stack2 = new ArrayStack<String>();
		stack2.push("one");
		stack2.push("two");
		try {
			stack2.setCapacity(-3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, stack2.size());
		}
	}

}
