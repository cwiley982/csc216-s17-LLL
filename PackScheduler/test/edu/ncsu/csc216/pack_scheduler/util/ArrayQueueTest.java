/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests the functionality of the ArrayQueue class
 * 
 * @author Caitlyn
 *
 */
public class ArrayQueueTest {
	
	private static final int CAPACITY = 5;
	/**
	 * Tests adding one element to the queue
	 */
	@Test
	public void testAddOneElement() {
		ArrayQueue<String> q = new ArrayQueue<String>(CAPACITY);
		try {
			q.enqueue("one");
			assertEquals(1, q.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests adding multiple elements to the queue
	 */
	@Test
	public void testAddMultipleElements() {
		ArrayQueue<String> q = new ArrayQueue<String>(CAPACITY);
		try {
			q.enqueue("one");
			q.enqueue("two");
			q.enqueue("three");
			assertEquals(3, q.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests removing one element from the queue
	 */
	@Test
	public void testRemoveOneElement() {
		ArrayQueue<String> q = new ArrayQueue<String>(CAPACITY);
		q.enqueue("one");
		assertEquals("one", q.dequeue());
	}

	/**
	 * Tests removing multiple elements from the queue
	 */
	@Test
	public void testRemoveMultipleElements() {
		ArrayQueue<String> q = new ArrayQueue<String>(CAPACITY);
		q.enqueue("one");
		q.enqueue("two");
		q.enqueue("three");
		assertEquals(3, q.size());
		q.dequeue();
		assertEquals("two", q.dequeue());
	}

	/**
	 * Tests removing the last element from the queue
	 */
	@Test
	public void testRemoveLastElement() {
		ArrayQueue<String> q = new ArrayQueue<String>(CAPACITY);
		q.enqueue("one");
		q.enqueue("two");
		q.enqueue("three");
		assertEquals(3, q.size());
		q.dequeue();
		q.dequeue();
		assertEquals("one", q.dequeue());
		assertTrue(q.isEmpty());
	}

	/**
	 * Tests removing an element from an empty queue
	 */
	@Test
	public void testRemoveFromEmptyStack() {
		ArrayQueue<String> q = new ArrayQueue<String>(CAPACITY);
		try {
			q.dequeue();
			fail();
		} catch (NoSuchElementException e) {
			assertTrue(q.isEmpty());
		}
	}

	/**
	 * Tests setting the queue capacity to its current size
	 */
	@Test
	public void testSetCapToSize() {
		ArrayQueue<String> q = new ArrayQueue<String>(CAPACITY);
		try {
			q.setCapacity(q.size());
			assertTrue(q.isEmpty());
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
		ArrayQueue<String> q = new ArrayQueue<String>(CAPACITY);
		q.enqueue("one");
		q.enqueue("two");
		try {
			q.setCapacity(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, q.size());
		}

		ArrayQueue<String> q2 = new ArrayQueue<String>(CAPACITY);
		q2.enqueue("one");
		q2.enqueue("two");
		try {
			q2.setCapacity(-3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, q2.size());
		}
	}

}
