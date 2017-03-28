package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tester class for LinkedAbstractList, this tests using String objects.
 * @author Sam
 *
 */
public class LinkedAbstractListTest {
	/**
	 * Tests the LinkedList constructor 
	 */
	@Test
	public void testLinkedAbstractList() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>();
		assertEquals(0, list.size());
		for(int i = 0; i < list.size(); i++){
			assertEquals("", list.get(i));
		}
	}

	/**
	 * Tests the get function for the custom Linked list
	 */
	@Test
	public void testGet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		try{
			assertEquals(null, list.get(0));
			fail();
		}
		catch(IndexOutOfBoundsException e){
			//skip
		}
		//add an element to the beginning
		list.add(0, "Hi");
		assertEquals("Hi", list.get(0));
		//adds an element and retrieves in the middle
		list.add(1, "Sup");
		assertEquals("Sup", list.get(1));
		//adds an element and retrieves at the end
		list.add(2, "Hello");
		assertEquals("Hello", list.get(2));
	}
	/**
	 * Tests the size method of the Linked List
	 */
	@Test
	public void testSize() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
		//add an element and then test that when we have that functionality
		list.add(0, "Hi");
		assertEquals(1, list.size());
		list.add(1, "Yo");
		list.add(2, "Hello");
		assertEquals(3, list.size());
	}
	/**
	 * Tests the add method of the Linked List
	 */
	@Test
	public void testAdd(){
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		//attempt to add a null element
		try{
			list.add(1, null);
			fail();
		}
		catch(NullPointerException e){
			//skip
		}
		//add at the beginning
		list.add(0, "Claire");
		assertEquals(1, list.size());
		assertEquals("Claire", list.get(0));
		//attempts to adds a duplicate item
		try{
			list.add(1, "Claire");
			fail();
		}
		catch(IllegalArgumentException e){
			//skip
		}
		//attempt to add at a negative index
		try{
			list.add(-1, "Cake!");
			 fail();
		}
		catch(IndexOutOfBoundsException e){
			//skip
		}
		//attempt to add at an index greater than the size
		try{
			list.add(250, "Cake!");
			 fail();
		}
		catch(IndexOutOfBoundsException e){
			//skip
		}
		// add at the end
		list.add(1, "Chocolate");
		assertEquals(2, list.size());
		assertEquals("Chocolate", list.get(1));
		//insert at the beginning. Other elements should shift
		list.add(0, "Happiness!");
		assertEquals(3, list.size());
		assertEquals("Happiness!", list.get(0));
		assertEquals("Claire", list.get(1));
		assertEquals("Chocolate", list.get(2));
		list.add(3, "Joy");
		list.add(4, "Super");
		list.add(5, "Fun");
		list.add(6, "Words");
		list.add(7, "Are");
		list.add(8, "Cool");
		list.add(9, "Sweet!"); 
		try{
			list.add(10, "You got it!");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			//skip
		}
	}
	/**
	 * Tests the remove method for the Linked List
	 */
	@Test
	public void testRemove(){
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		list.add(0, "Claire");
		list.add(1, "Katherine");
		list.add(2, "Brown");
		//attempt to remove at a negative index
		try{
			list.remove(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			//skip
		}
		//attempt to remove at and index greater than size
		try{
			list.remove(4);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			//skip
		}
		//remove at the beginning
		list.remove(0);
		assertEquals("Katherine", list.get(0));
		assertEquals("Brown", list.get(1));
		assertEquals(2, list.size());
		//remove at the end 
		list.remove(1); 
		assertEquals("Katherine", list.get(0));
		assertEquals(1, list.size());
	}
	/**
	 * Tests the set method of Linked List
	 */
	@Test
	public void testSet(){
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		list.add(0, "Claire");
		list.add(1, "Katherine");
		list.add(2, "Brown"); 
		//attempt to set a null element
		try{
			list.set(0, null); 
			fail();
		}
		catch(NullPointerException e){
			//skip
		}
		//attempt to set a duplicate element
		try{
			list.set(1, "Claire");
			fail();
		}
		catch(IllegalArgumentException e){
			//skip
		}
		//attempt to set at a negative index
		try{
			list.set(-1, "Cupcakes");
			fail();
		}
		catch(IndexOutOfBoundsException e){
			//skip
		}
		//attempt to set at an index greater than the size
		try{
			list.set(21, "Cupcakes");
			fail();
		}
		catch(IndexOutOfBoundsException e){
			//skip
		}
		//set at the beginning
		list.set(0, "Computer");
		assertEquals("Computer", list.get(0));
		assertEquals("Katherine", list.get(1));
		assertEquals("Brown", list.get(2));
		//set in the middle 
		list.set(1, "Science");
		assertEquals("Computer", list.get(0));
		assertEquals("Science", list.get(1));
		assertEquals("Brown", list.get(2));
		//set at the end
		list.set(2, "Rocks!");
		assertEquals("Computer", list.get(0));
		assertEquals("Science", list.get(1));
		assertEquals("Rocks!", list.get(2));
		
		LinkedAbstractList<String> list2 = new LinkedAbstractList<String>(10);
		list2.add("Claire");
		list2.set(0, "Sam");
	}
}
