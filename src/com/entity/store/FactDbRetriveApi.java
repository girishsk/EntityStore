package com.entity.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FactDbRetriveApi implements FactDbApi {

	DoublyLinkedNode head;

	@Override
	public FactDbRecord next() {
		DoublyLinkedNode temp = head;
		temp = temp.next;
		if (temp != null) {
			head = head.next;
			return (FactDbRecord) head.data;
		} else {
			return null;
		}

	}

	@Override
	public FactDbRecord previous() {
		DoublyLinkedNode temp = head;
		temp = temp.previous;
		if (temp != null) {
			head = head.previous;
			return (FactDbRecord) head.data;
		} else {
			return null;
		}
	}

	@Override
	public void gotoAfterLatest() {
		DoublyLinkedNode temp;
		if (head != null) {
			for (temp = head; temp.next != null; temp = temp.next) {
			}
			head = temp;
		}
	}

	@Override
	public void gotoBeforeEarliest() {
		DoublyLinkedNode temp;
		if (head != null) {
			for (temp = head; temp.previous != null; temp = temp.previous) {
			}
			head = temp;
		}
	}

	public void insertRecord(int id, String name, String value) {
		FactDbRecordImpl fget = new FactDbRecordImpl();
		DoublyLinkedNode obj = new DoublyLinkedNode();

		fget.setRecord(id, name, value);
		if (head == null) {
			obj.data = fget;
			obj.next = null;
			obj.previous = null;
			head = obj;
		} else {
			obj.data = fget;
			obj.previous = head;
			head.next = obj;
			head = head.next;
		}
	}

	public List<FactDbRecordImpl> retriveRecord(int entityId,
			String... inputKeys) {

		Set<String> keys = new HashSet<String>(Arrays.asList(inputKeys));

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
