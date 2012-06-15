# EntityStore

This is the code for the Entity Store, It implements the interfaces provided. 
Note: The input format and the output format is not as same as the one provided in the document, This can be done by having some string manipulations around the input format and also on the output records (Implementing to_String oon objects and aggregating the record content.)

## DataStructure
Doublylinked List is used for the data structre and the data each node contains is the Record data.
Added insert and serach funtionalities other than overriding the interface. 

## Limitations and Efficiency
Storage wise its not as efficient as storing the records contigious in the memory, But it takes into account the ease of navigational capabilities, by using DoublyLinked list. 
Also, the code is not tested for huge amount of data input. The test cases covers most of the aspect of navigation, stateful tests, time test and all the functionality of the code. 
 
