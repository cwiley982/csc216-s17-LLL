package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the SortedList class
 * 
 * @author kagordo3
 */
public class SortedListTest {

	/**
	 * Creates an empty sortedList and ensures that the size is zero and that it
	 * does not contain anything. Then tests that the array grows.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		// TODO Test that the list grows by adding at least 11 elements
		list.add("avocado");
		list.add("Hershey's");
		list.add("grapefruit");
		list.add("british chocolate");
		list.add("s'mores");
		list.add("santa");
		list.add("hearts");
		list.add("oreos");
		list.add("pickle");
		list.add("corn");
		list.add("cucumber");
		assertEquals(11, list.size());
		// Remember the list's initial capacity is 10

	}

	/**
	 * Attempts to add a new object into the sortedList. The size should now be
	 * one and the new object should be at the beginning of the list. Also
	 * attempts to add a null object, a duplicate element, and a new object at
	 * different positions in the list.
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		// TODO Test adding to the front, middle and back of the list
		list.add("avocado");
		assertEquals(2, list.size());
		assertEquals("avocado", list.get(0));
		
		list.add("zebra");
		assertEquals(3, list.size());
		assertEquals("zebra", list.get(2));
		
		list.add("computer");
		assertEquals(4, list.size());
		assertEquals("computer", list.get(2));
		
		// TODO Test adding a null element
		try{
			list.add(null);
		}
		catch(NullPointerException e){
			assertEquals(4, list.size());
		}

		// TODO Test adding a duplicate element
		try{
			list.add("banana");
		}
		catch(IllegalArgumentException e){
			assertEquals(4, list.size());

		}
	}

	/**
	 * Tests that user can get an element from a list. Focuses mainly on error
	 * and boundary cases, like retrieving an element from an empty list, an
	 * element with a negative index, and an element at a size.
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here. Instead this test method should focus on the error
		// and boundary cases.

		// TODO Test getting an element from an empty list
		try{
			list.get(0);
			fail();
		}
		catch(IndexOutOfBoundsException e){
			//skip
		}

		// TODO Add some elements to the list
		list.add("element");

		// TODO Test getting an element at an index < 0
		try{
			list.get(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e){
			//skip
		}

		// TODO Test getting an element at size
		try{
			list.get(list.size());
			fail();
		}
		catch(IndexOutOfBoundsException e){
			//skip
		}

	}

	/**
	 * Tests that elements can be removed from the sorted list. Tries removing
	 * from an empty list, removing something at an index less than 0, and elements at
	 * different locations in the list.
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// Test removing from an empty list
		try{
			list.remove(0);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			//skip
		}
		//Add some elements to the list - at least 4
		list.add("chocolate");
		list.add("fries");
		list.add("tuna fish sandwich");
		list.add("mayo");

		//Test removing an element at an index < 0
		try{
			list.remove(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			//skip
		}

		//Test removing an element at size
		try{
			list.remove(list.size());
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			//skip
		}

		//Test removing a middle element
		list.remove(2);
		assertEquals(3, list.size());
		assertEquals("chocolate", list.get(0));
		assertEquals("fries", list.get(1));
		assertEquals("tuna fish sandwich", list.get(2));
		
		//Test removing the last element
		list.remove(2);
		assertEquals(2, list.size());
		assertEquals("chocolate", list.get(0));
		assertEquals("fries", list.get(1));
		
		//Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("fries", list.get(0));
		
		//Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}

	/**
	 * Tests that elements within the list are located at the correct index, and
	 * that elements not contained within the list return -1
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("cake!!"));
		
		// Add some elements
		list.add("cake");
		list.add("chocolate cake");
		list.add("double chocolate cake");
		list.add("triple chocolate cake");
		
		// Test various calls to indexOf for elements in the list
		// and not in the list
		assertEquals(2, list.indexOf("double chocolate cake"));
		assertEquals(0, list.indexOf("cake"));
		assertEquals(3, list.indexOf("triple chocolate cake"));
		assertEquals(-1, list.indexOf("vanilla cake"));
		
		//Test checking the index of null
		try{
			list.indexOf(null);
			fail();
		}
		catch(NullPointerException e)
		{
			//skip
		}
	}

	/**
	 * Tests that the list is emptied when cleared
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// TODO Add some elements
		list.add("apple");
		list.add("bacon");
		list.add("cat");
		list.add("darkness");
		list.add("elephant");
		list.add("fun");
		list.add("goop");
		// TODO Clear the list
		
		list.clear();

		// TODO Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * Tests that the list is not empty when elements are added
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// TODO Test that the list starts empty
		assertEquals(0, list.size());

		// TODO Add at least one element
		list.add("apple");
		list.add("bacon");

		// TODO Check that the list is no longer empty
		assertFalse(list.size() == 0);
	}

	/**
	 * Tests that the list contains added elements
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// TODO Test the empty list case
		assertFalse(list.contains("apple"));
		// TODO Add some elements
		list.add("apple");
		list.add("bacon");
		list.add("cat");
		list.add("darkness");
		list.add("elephant");
		list.add("fun");
		list.add("goop");
		// TODO Test some true and false cases
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("darkness"));
		assertTrue(list.contains("fun"));
		assertFalse(list.contains("woop"));
		assertFalse(list.contains("bananas"));
		assertFalse(list.contains("shoes"));
		}

	/**
	 * Tests equality between lists
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		
		list2.add("apple");
		list2.add("banana");
		
		list3.add("sadness");
		list3.add("woop");

		// TODO Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
	}

	/**
	 * Tests the equality of list hashCodes
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		
		list2.add("apple");
		list2.add("banana");
		
		list3.add("sadness");
		list3.add("woop");

		// TODO Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}
	

}
