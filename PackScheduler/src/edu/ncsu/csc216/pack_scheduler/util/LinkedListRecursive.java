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
		
	}
	
	/**
	 * returns whether or not the list is empty
	 * @return boolean true if empty, false if not
	 */
	public boolean isEmpty() {
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
		return false;
	}
	
	/**
	 * adds an element at the specified index
	 * @param index to add element
	 * @param element to add
	 */
	public void add(int index, E element) {
		
	}
	
	/**
	 * returns the element at the specified index
	 * @param index index of element
	 * @return element
	 */
	public E get(int index) {
		return null;
	}
	
	/**
	 * removes an element from the list
	 * @param element to remove
	 * @return true if removed, false if not
	 */
	public boolean remove(E element) {
		return false;
	}
	
	/**
	 * removes an element from the list and returns it
	 * @param index of element
	 * @return element	
	 */
	public E remove(int index) {
		return null;
	}
	
	/**
	 * sets an element at the given index
	 * @param index of element
	 * @param element to set
	 * @return element replaced
	 */
	public E set(int index, E element) {
		return null;
	}
	
	/**
	 * determines whether or not the list contains an element
	 * @param element contained
	 * @return true if contained, false if not
	 */
	public boolean contains (E element) {
		return false;
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
			
		}
		
		/**
		 * adds a node to the list at a particular inde
		 * @param index of element
		 * @param element to add
		 */
		public void add (int index, E element) {
			
		}
		
		/**
		 * returns a node's data
		 * @param index of node
		 * @return data of node
		 */
		public E get(int index) {
			return null;
		}
		
		/**
		 * removes an element from a particular index
		 * @param index of element to remove
		 * @return element
		 */
		public E remove(int index) {
			return null;
		}
		
		/**
		 * removes specified element from list
		 * @param element to remove
		 * @return true if removed, false if not
		 */
		public boolean remove (E element) {
			return false;
		}
		
		/**
		 * sets an element at a particular index
		 * @param index to replace
		 * @param element to replace with
		 * @return element replaced
		 */
		public E set (int index, E element) {
			return null;
		}
		
		/**
		 * determines whether or not a list contains a particular element
		 * @param element contained
		 * @return true if contained, false if not
		 */
		public boolean contains (E element) {
			return false;
		}
		
	}
}
