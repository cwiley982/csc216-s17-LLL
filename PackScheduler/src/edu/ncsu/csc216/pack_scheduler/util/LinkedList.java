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
		return new LinkedListIterator(index);
	}

	@Override
	public int size() {
		return size;
	}

	private class ListNode {
		private E data;
		private ListNode next;
		private ListNode prev;

		public ListNode(E data) {
			this.data = data;
		}

		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	private class LinkedListIterator implements ListIterator<E> {
		private ListNode previous;
		private ListNode next;
		private int previousIndex;
		private int nextIndex;
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
			previous = new ListNode(element);
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

