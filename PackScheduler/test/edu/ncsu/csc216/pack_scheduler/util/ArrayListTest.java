/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the ArrayList class
 * 
 * @author Sam, Claire, Caitlyn
 *
 */
public class ArrayListTest {

	/**
	 * Tests the ArrayList constructor 
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
		for(int i = 0; i < list.size(); i++){
			assertEquals("", list.get(i));
		}
	}

	/**
	 * Tests the get function for the custom array list
	 */
	@Test
	public void testGet() {
		ArrayList<String> list = new ArrayList<String>();
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
	 * Tests the size method of the Array List
	 */
	@Test
	public void testSize() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
		//add an element and then test that when we have that functionality
		list.add(0, "Hi");
		assertEquals(1, list.size());
		list.add(1, "Yo");
		list.add(2, "Hello");
		assertEquals(3, list.size());
	}
	/**
	 * Tests the add method of the Array List
	 */
	@Test
	public void testAdd(){
		ArrayList<String> list = new ArrayList<String>();
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
		//adds in the middle
		list.add(1, "Chocolate");
		assertEquals(2, list.size());
		assertEquals("Chocolate", list.get(1));
		//insert at the beginning. Other elements should shift
		list.add(0, "Happiness!");
		assertEquals(3, list.size());
		assertEquals("Happiness!", list.get(0));
		assertEquals("Claire", list.get(1));
		assertEquals("Chocolate", list.get(2));
		//attempts to add when size = capacity. Should be able to handle
		list.add(3, "Joy");
		list.add(4, "Super");
		list.add(5, "Fun");
		list.add(6, "Words");
		list.add(7, "Are");
		list.add(8, "Cool");
		list.add(9, "Sweet!");
		list.add(10, "You got it!");
	}
	/**
	 * Tests the remove method for the Array List
	 */
	@Test
	public void testRemove(){
		ArrayList<String> list = new ArrayList<String>();
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
	 * Tests the set method
	 */
	@Test
	public void testSet(){
		ArrayList<String> list = new ArrayList<String>();
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
	}

}
