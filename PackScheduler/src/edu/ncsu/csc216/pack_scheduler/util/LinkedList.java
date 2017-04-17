package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Creates a linked list to be used for in adding faculty to a list by the
 * registrar. List can be traversed both ways by using the inner class
 * LinkedListIterator.
 * 
 * @author Caitlyn
 *
 * @param <E>
 *            generic parameter
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	private ListNode front;
	private ListNode back;
	private int size;

	/**
	 * Constructs a linked list and has front and back set to null and has them
	 * pointing forward and backward to each other
	 */
	public LinkedList() {
		size = 0;
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedListIterator listIterator = new LinkedListIterator(index);
		return listIterator;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * adds an element to the given index
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}

	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return super.remove(index);
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return super.get(index);
	}

	/**
	 * Creates ListNodes to be referenced in ListIterator
	 * 
	 * @author Caitlyn
	 *
	 */
	private class ListNode {
		/** data contained in the current list node */
		private E data;
		/** the ListNode after the current one */
		private ListNode next;
		/** The ListNode before the current one */
		private ListNode prev;

		/**
		 * Constructs a ListNode with just data, usually only used for the first
		 * node in the list
		 * 
		 * @param data
		 *            the data held in the node
		 */
		public ListNode(E data) {
			this.data = data;
		}

		/**
		 * Constructs a ListNode with certain data and sets the references to
		 * the next and previous nodes
		 * 
		 * @param data
		 *            data held in the node
		 * @param prev
		 *            previous node
		 * @param next
		 *            next node
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	/**
	 * Creates a LinkedListIterator that will sit between nodes and be doubly
	 * linked so it can traverse the list forwards and backwards
	 * 
	 * @author Caitlyn
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** Node before where the iterator is */
		private ListNode previous;
		/** Node after where the iterator is */
		private ListNode next;
		/** Index of previous node */
		private int previousIndex;
		/** Index of next node */
		private int nextIndex;
		/** Last node retrieved using next() or previous() methods */
		private ListNode lastRetrieved;

		public LinkedListIterator(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			}
			previous = front; // index -1
			next = front.next; // index 0
			for (int i = 0; i < index; i++) {
				previous = previous.next;
				next = next.next;
			}
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}

		@Override
		public void add(E element) {
			if (element == null) {
				throw new NullPointerException();
			}
			ListNode newNode = new ListNode(element, previous, next);
			previous.next = newNode;
			next.prev = newNode;
			previous = newNode;
			previousIndex++;
			nextIndex++;
			lastRetrieved = null;
			size++;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public boolean hasPrevious() {
			return previous != null;
		}

		@Override
		public E next() {
			if (next == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			E data = next.data;
			next = next.next;
			previous = previous.next;
			nextIndex++;
			previousIndex++;
			return data;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public E previous() {
			if (previous == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			E data = previous.data;
			previous = previous.prev;
			next = next.prev;
			nextIndex--;
			previousIndex--;
			return data;
		}

		@Override
		public int previousIndex() {
			return previousIndex;
		}

		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			} else {
				lastRetrieved.prev.next = lastRetrieved.next;
				lastRetrieved.next.prev = lastRetrieved.prev;
				lastRetrieved = null;
				size--;
			}
		}

		@Override
		public void set(E element) {
			if (element == null) {
				throw new NullPointerException();
			}
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			} else if (lastRetrieved == next) { // change next node
				next.data = element;
			} else if (lastRetrieved == previous) { // change previous node
				previous.data = element;
			}
		}
	}
}