package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<E> extends AbstractSequentialList<E> {

	private ListNode front;
	private ListNode back;
	private int size;

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

	@Override
	public void add(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		listIterator(index).add(element);
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
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			return next.data;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			return previous.data;
		}

		@Override
		public int previousIndex() {
			return previousIndex;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void set(E arg0) {
			// TODO Auto-generated method stub

		}

	}

}

