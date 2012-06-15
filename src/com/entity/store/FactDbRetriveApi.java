package com.entity.store;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FactDbRetriveApi  implements FactDbApi {

	DoublyLinkedNode head;
	
	@Override
	public FactDbRecord next() {
		head = head.next;
		if(head != null) {
		return (FactDbRecord)head.data;
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public FactDbRecord previous() {
		head = head.prev;
		if(head != null) {
		return (FactDbRecord)head.data;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void gotoAfterLatest() {
		DoublyLinkedNode d;
		for( d = head;d.next!=null;d = d.next)
		{}
		head = d;
	}

	@Override
	public void gotoBeforeEarliest() {
		DoublyLinkedNode d;
		for( d = head;d.prev!=null;d = d.prev)
		{}
		head = d;
	}
	
	public void insertRecord(int id, String name, String value){
		FactDbRecordImpl fget = new FactDbRecordImpl();
		DoublyLinkedNode obj = new DoublyLinkedNode();
		
		fget.setRecord(id, name, value);
		if (head == null) {
			obj.data = fget ;
			obj.next = null;
			obj.prev = null;
			head = obj;
		}
		else
		{
			obj.data = fget;
			obj.prev = head;
			head.next = obj;
			head = head.next;
		}
	}
	
	public List<FactDbRecordImpl> retriveRecord(int entityId, String... inputKeys){
		
		Set<String> keys = new HashSet<String>(Arrays.asList(inputKeys));
		
		List<FactDbRecordImpl> retrievedValue = new ArrayList<FactDbRecordImpl>();
		DoublyLinkedNode d; 
		d = head;
		
		//assume the data is organized in ascending order of timestamp ; so go to the last and then start searching the data.
		gotoAfterLatest();
		
		while(head != null) {
			if(entityId == head.data.getEntityId() && keys.contains(head.data.fieldName())) {
				retrievedValue.add(head.data);
				keys.remove(head.data.fieldName());
				if(keys.isEmpty()) {
					break;
				}
				
			}
			head = head.prev;
		}
		head = d;
		return retrievedValue;
	}
	
	public static void main(String args[])
	{
		
		FactDbRetriveApi api = new FactDbRetriveApi();
		api.insertRecord(1, "name", "Girish");
		api.insertRecord(1, "age", "10");
		api.insertRecord(2,"name","Shachi");
		api.insertRecord(2,"age","8");
		api.gotoBeforeEarliest();
		
		
		System.out.println(api.retriveRecord(1, "name", "age"));
		System.out.println(api.retriveRecord(2, "name", "age"));
	}
}
