package com.entity.store;

public class FactDbRecordImpl implements FactDbRecord {

	long entityId;
	long time;
	String name;
	String value;

	@Override
	public long getMillis() {
		return time;
	}

	@Override
	public long getEntityId() {
		return entityId;
	}

	@Override
	public String fieldName() {
		return name;
	}

	@Override
	public String fieldValue() {
		return value;
	}

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
