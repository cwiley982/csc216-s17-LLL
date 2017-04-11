package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Recursive implementation of a LinkedList
 * @author Lauren
 *@param <E> element
 *
 */
public class LinkedListRecursive<E> {
	
	/** size of the list */
	private int size;
	/** front node of the list */
	private ListNode front;
	
	/**
	 * constructs a LinkedListRecursive
	 */
	public LinkedListRecursive() {
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * returns whether or not the list is empty
	 * @return boolean true if empty, false if not
	 */
	public boolean isEmpty() {
		if(this.size == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * returns the size of the list
	 * @return int size
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * adds an element to the end of the list
	 * @param element to add
	 * @return true if added, false if not
	 */
	public boolean add(E element) {
		if(isEmpty()) {
			this.front = new ListNode(element, null);
			this.size++;
			return true;
		}
		if(this.contains(element)) {
			throw new IllegalArgumentException();
		}
		return this.front.add(element);
	}
	
	/**
	 * adds an element at the specified index
	 * @param index to add element
	 * @param element to add
	 */
	public void add(int index, E element) {
		if(this.contains(element)) {
			throw new IllegalArgumentException();
		}
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			throw new NullPointerException();
		}
		if(index == 0){
			this.front = new ListNode(element, this.front);
			this.size++;
		} else {
			this.front.add(index - 1, element);
		}
	}
	
	/**
	 * returns the element at the specified index
	 * @param index index of element
	 * @return element
	 */
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return this.front.get(index);
	}
	
	/**
	 * removes an element from the list
	 * @param element to remove
	 * @return true if removed, false if not
	 */
	public boolean remove(E element) {
		if(element == null) {
			return false;
		}
		if(isEmpty()) {
			return false;
		}
		if(this.front.data == element) {
			this.front = this.front.next;
			this.size--;
			return true;
		}
		return this.front.remove(element);
	}
	
	/**
	 * removes an element from the list and returns it
	 * @param index of element
	 * @return element	
	 */
	public E remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			E returnData = this.front.data;
			this.front = this.front.next;
			this.size--;
			return returnData;
		}
		return this.front.remove(index - 1);
	}
	
	/**
	 * sets an element at the given index
	 * @param index of element
	 * @param element to set
	 * @return element replaced
	 */
	public E set(int index, E element) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		if(element == null){
			throw new NullPointerException();
		}
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		return this.front.set(index, element);	
	}
	
	/**
	 * determines whether or not the list contains an element
	 * @param element contained
	 * @return true if contained, false if not
	 */
	public boolean contains (E element) {
		if(isEmpty()) {
			return false;
		}
		return this.front.contains(element);
	}
	
	/**
	 * describes behavior & state of a LinkedNode
	 * @author Lauren
	 *
	 */
	private class ListNode {
		
		/** the node's data */
		public E data;
		/** next LinkedNode */
		public ListNode next;
		
		/**
		 * creates a new ListNode
		 * @param data the node's data
		 * @param next next node in the list
		 */
		public ListNode (E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * adds an element to the end of a list
		 * @param element to add
		 * @return true if added, false if not
		 */
		public boolean add(E element) {
			if(next == null) {
				size++;
				next = new ListNode(element, null);
				return true;
			}
			return next.add(element);
		}

		/**
		 * adds a node to the list at a particular index
		 * @param index of element
		 * @param element to add
		 */
		public void add (int index, E element) {
			if(index == 0){
				size++;
				next = new ListNode(element, next);
			} else {
				next.add(index - 1, element);
			}
		}
		
		/**
		 * returns a node's data
		 * @param index of node
		 * @return data of node
		 */
		public E get(int index) {
			if(index == 0) {
				return data;
			}
			return next.get(index - 1);
		}
		
		/**
		 * removes an element from a particular index
		 * @param index of element to remove
		 * @return element
		 */
		public E remove(int index) {
			if(index == 0) {
				size--;
				E returnData = next.data;
				next = next.next;
				return returnData;
			}
			return next.remove(index - 1);
		}
		
		/**
		 * removes specified element from list
		 * @param element to remove
		 * @return true if removed, false if not
		 */
		public boolean remove (E element) {
			if(next.data == element) {
				size--;
				next = next.next;
				return true;
			}
			return next.remove(element);
		}
		
		/**
		 * sets an element at a particular index
		 * @param index to replace
		 * @param element to replace with
		 * @return element replaced
		 */
		public E set (int index, E element) {
			if(index == 0) {
				E returnData = data;
				data = element;
				return returnData;
			}
			return next.set(index - 1, element);
		}
		
		/**
		 * determines whether or not a list contains a particular element
		 * @param element contained
		 * @return true if contained, false if not
		 */
		public boolean contains (E element) {
			if(data == element) {
				return true;
			}
			if(next == null) {
				return false;
			}
			return next.contains(element);
		}
		
	}
}
