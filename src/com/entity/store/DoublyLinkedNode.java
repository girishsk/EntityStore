package com.entity.store;
public class DoublyLinkedNode {
  
  public DoublyLinkedNode prev;
  public FactDbRecordImpl data;
  public DoublyLinkedNode next;
 
  public DoublyLinkedNode( ) {
    
  }
 
  public DoublyLinkedNode( FactDbRecordImpl data, DoublyLinkedNode prev, DoublyLinkedNode next ) {
    this.data = data;
    this.prev = prev;
    this.next = next;
  }
 
  public DoublyLinkedNode( FactDbRecordImpl data ) {
    this( data, null, null );
  }

  @Override
  public String toString() {
	  return this.data.toString();
  }

}




