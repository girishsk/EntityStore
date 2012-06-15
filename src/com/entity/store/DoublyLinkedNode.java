/*
 * This class defines a doublyLinkedNode which holds the FactDbRecord structure.
 * This is used to traverse to the previous or the next node in the list. 
 */

package com.entity.store;

public class DoublyLinkedNode {

    // Defining previous and next nodes and data field. 
	public DoublyLinkedNode previous;
	public FactDbRecordImpl data;
	public DoublyLinkedNode next;

	public DoublyLinkedNode() {

	}
	// Constructor 
	public DoublyLinkedNode(FactDbRecordImpl data, DoublyLinkedNode prev,
			DoublyLinkedNode next) {
		this.data = data;
		this.previous = prev;
		this.next = next;
	}
	// Constructor 
	public DoublyLinkedNode(FactDbRecordImpl data) {
		this(data, null, null);
	}

	@Override
	public String toString() {
		return this.data.toString();
	}

}
