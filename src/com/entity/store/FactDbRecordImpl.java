package com.entity.store;

/*
 * This class implements methods from the FactDbRecord class to return the fieldname, 
 * entityid, fieldvalue and the time. 
 * The class also has a function to set the Record values which sets the values for the above mentioned parameters. 
 */

public class FactDbRecordImpl implements FactDbRecord {

	long entityId;
	long time;
	String name;
	String value;

	/*
	 * This is implemented from FactDbRecord class and returns the time of inserting the entry. 
	 * (non-Javadoc)
	 * @see com.entity.store.FactDbRecord#getMillis()
	 */
	@Override
	public long getMillis() {
		return time;
	}

	/*
	 * This is implemented from FactDbRecord class and returns the entityid of inserting the entry. 
	 * (non-Javadoc)
	 * @see com.entity.store.FactDbRecord#getEntityId()
	 */
	@Override
	public long getEntityId() {
		return entityId;
	}

	/*
	 * This is implemented from FactDbRecord class and returns the fieldname of inserting the entry. 
	 * (non-Javadoc)
	 * @see com.entity.store.FactDbRecord#fieldName()
	 */
	@Override
	public String fieldName() {
		return name;
	}
	
	/*
	 * This is implemented from FactDbRecord class and returns the time of inserting the entry. 
	 * (non-Javadoc)
	 * @see com.entity.store.FactDbRecord#fieldValue()
	 */
	@Override
	public String fieldValue() {
		return value;
	}

	/*
	 * Sets the values for the parameteres : entityid, name, value and time. 
	 */
	public void setRecord(long entityId, String name, String value) {
		this.entityId = entityId;
		this.name = name;
		this.value = value;
		this.time = System.currentTimeMillis();

	}

	@Override
	public String toString() {
		return entityId + ":" + name + ":" + value;
	}

}
