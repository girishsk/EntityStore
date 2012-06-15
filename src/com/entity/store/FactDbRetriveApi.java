/*
 * This class implements FactDbApi and methods used to traverse to the next and previous nodes. 
 * It also has methods to insert and retrieve records.
 */

package com.entity.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FactDbRetriveApi implements FactDbApi {

	// Head node maintains the state of the traversal. 
	DoublyLinkedNode head;

	/**
	 * This method traverses to the next node in the list. 
	 * @see com.entity.store.FactDbApi#next()
	 */
	@Override
	public FactDbRecord next() {
		DoublyLinkedNode temp = head;
		temp = temp.next;
		//traverse to the next nodes till its null and return the records
		if (temp != null) {
			head = head.next;
			return (FactDbRecord) head.data;
		} else {
			return null;
		}

	}
	

	/**
	 * This method traverses to the previous node in the list. 
	 * @see com.entity.store.FactDbApi#previous()
	 */
	@Override
	public FactDbRecord previous() {
		DoublyLinkedNode temp = head;
		temp = temp.previous;
		//traverse to the previous nodes till its null and return the records
		if (temp != null) {
			head = head.previous;
			return (FactDbRecord) head.data;
		} else {
			return null;
		}
	}

	/**
	 * This method traverses till the latest node in the list. 
	 * @see com.entity.store.FactDbApi#gotoAfterLatest()
	 */
	
	@Override
	public void gotoAfterLatest() {
		DoublyLinkedNode temp;
		if (head != null) {
			// traverse through the list till temp.next is null
			for (temp = head; temp.next != null; temp = temp.next) {
			}
			head = temp;
		}
	}

	/**
	 * This method traverses from the current node till the first node
	 * @see com.entity.store.FactDbApi#gotoBeforeEarliest()
	 */
	@Override
	public void gotoBeforeEarliest() {
		DoublyLinkedNode temp;
		if (head != null) {
			//traverse through the list till temp.previous is null
			for (temp = head; temp.previous != null; temp = temp.previous) {
			}
			head = temp;
		}
	}

	/**
	 * This method inserts the record into the list and reassigns the next and the previous pointers of the node. 
	 */
	public void insertRecord(int id, String name, String value) {
		FactDbRecordImpl fget = new FactDbRecordImpl();
		DoublyLinkedNode obj = new DoublyLinkedNode();
		// Calls the set record function in the FactDbRecordImpl class. 
		fget.setRecord(id, name, value);
		//if its the first node, insert the data and make next and previous to null. 
		if (head == null) {
			obj.data = fget;
			obj.next = null;
			obj.previous = null;
			head = obj;
		} else {
			// if not the first node, then set the pointers appropriately.
			obj.data = fget;
			obj.previous = head;
			head.next = obj;
			head = head.next;
		}
	}

	/**
	 * This method retrieves the record from the list by first traversing to the last node, and then searching the data
	 * backwards
	 * 
	 */
	public List<FactDbRecordImpl> retriveRecord(int entityId,
			String... inputKeys) {
		// Put the inputKeys into a set 
		Set<String> keys = new HashSet<String>(Arrays.asList(inputKeys));
		// Create an array list to store the retrieved values. 
		List<FactDbRecordImpl> retrievedValue = new ArrayList<FactDbRecordImpl>();
		DoublyLinkedNode temp;
		temp = head;

		// assume the data is organized in ascending order of timestamp ; so go
		// to the last and then start searching the data.
		gotoAfterLatest();

		while (head != null) {
			if (entityId == head.data.getEntityId()
					&& keys.contains(head.data.fieldName())) {
				retrievedValue.add(head.data);
				keys.remove(head.data.fieldName());
				if (keys.isEmpty()) {
					break;
				}

			}
			head = head.previous;
		}
		head = temp;
		return retrievedValue;
	}
}
