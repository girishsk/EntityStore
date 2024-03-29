package test.com.entity.store;

import java.util.List;

import com.entity.store.FactDbRecord;
import com.entity.store.FactDbRecordImpl;
import com.entity.store.FactDbRetriveApi;

import junit.framework.Assert;
import junit.framework.TestCase;

public class EntityStoreTest extends TestCase {

	FactDbRetriveApi api;

	@Override
	public void setUp() {
		api = new FactDbRetriveApi();
	}

	@Override
	public void tearDown() {
		api = null;
	}

	/**
	 * Testing the previous functionality and state
	 */
	public void testPrevious() {
		api.insertRecord(1, "name", "firstname");
		api.insertRecord(1, "age", "10");
		api.insertRecord(2, "name", "lastname");
		api.insertRecord(1, "age", "20");

		FactDbRecord retrivedRecord = api.previous();

		Assert.assertEquals(2, retrivedRecord.getEntityId());
		Assert.assertEquals("name", retrivedRecord.fieldName());
		Assert.assertEquals("lastname", retrivedRecord.fieldValue());
	}
	/**
	 * Testing the next functionality and state
	 */
	public void testNext() {
		api.insertRecord(1, "name", "firstname");
		api.insertRecord(1, "age", "10");
		api.insertRecord(2, "name", "lastname");
		api.insertRecord(1, "age", "20");
		api.gotoBeforeEarliest();

		FactDbRecord retrivedRecord = api.next();

		Assert.assertEquals(1, retrivedRecord.getEntityId());
		Assert.assertEquals("age", retrivedRecord.fieldName());
		Assert.assertEquals("10", retrivedRecord.fieldValue());
	}
	/**
	 * Testing the 'gotoAfterLatest' functionality and state
	 */
	public void testNextOnLastNode() {

		api.insertRecord(1, "name", "firstname");
		api.insertRecord(1, "age", "10");
		api.gotoAfterLatest();

		Assert.assertNull(api.next());
	}
	/**
	 * Testing the 'gotoBeforeEarliest' functionality and state
	 */
	public void testPreviousOnFirstNode() {

		api.insertRecord(1, "name", "firstname");
		api.insertRecord(1, "age", "10");
		api.gotoBeforeEarliest();

		Assert.assertNull(api.previous());
	}
	/**
	 * Testing the state of the head Node
	 */
	public void testStateOfTheHead() {

		api.insertRecord(1, "name", "firstname");
		api.insertRecord(1, "age", "10");
		api.insertRecord(1, "age", "20");
		api.insertRecord(1, "name", "lastname");
		api.insertRecord(1, "address", "529 saratoga ave");
		api.insertRecord(1, "weight", "150");

		api.retriveRecord(1, "name");

		Assert.assertNull(api.next());

		Assert.assertEquals("address", api.previous().fieldName());
	}
	/**
	 * Testing the unknown key
	 */
	public void testNoData() {
		List<FactDbRecordImpl> retrivedRecords = api.retriveRecord(1,
				"unknownKey");
		Assert.assertEquals(0, retrivedRecords.size());
	}
	
	
	/**
	 * Trying to retrieve unknown/ non-existing entity. 
	 */
	public void testWithOneDataAndNonExistingEntity() {
		api.insertRecord(1, "name", "firstname");
		List<FactDbRecordImpl> retrivedRecords = api.retriveRecord(2, "name");
		Assert.assertEquals(0, retrivedRecords.size());
	}

	/**
	 * Testing for one record and existing entity
	 */
	public void testWithOneDataAndExistingEntity() {
		api.insertRecord(1, "name", "firstname");
		List<FactDbRecordImpl> retrivedRecords = api.retriveRecord(1, "name");
		Assert.assertEquals(1, retrivedRecords.size());
		FactDbRecordImpl data = retrivedRecords.get(0);
		Assert.assertEquals("name", data.fieldName());
		Assert.assertEquals("firstname", data.fieldValue());
		Assert.assertEquals(1, data.getEntityId());
	}

	/**
	 * Testing for one record and a non existing key
	 */
	public void testWithOneDataAndNonExistingKey() {
		api.insertRecord(1, "name", "firstname");
		List<FactDbRecordImpl> retrivedRecords = api.retriveRecord(1, "age");
		Assert.assertEquals(0, retrivedRecords.size());
	}

	/**
	 * Getting the latest record for an entity and key
	 */
	public void testGetLatestRecordForAnEntityAndKey() {
		api.insertRecord(1, "name", "firstname");
		api.insertRecord(1, "age", "10");
		api.insertRecord(2, "name", "lastname");
		api.insertRecord(1, "age", "20");

		List<FactDbRecordImpl> retrivedRecords = api.retriveRecord(1, "age");

		Assert.assertEquals(1, retrivedRecords.size());

		FactDbRecordImpl data = retrivedRecords.get(0);

		Assert.assertEquals(1, data.getEntityId());
		Assert.assertEquals("age", data.fieldName());
		Assert.assertEquals("20", data.fieldValue());
	}

	/**
	 * Getting data with multiple keys. 
	 */
	public void testGetDataWithMultipleKeys() {

		api.insertRecord(1, "name", "firstname");
		api.insertRecord(1, "age", "10");
		api.insertRecord(1, "age", "20");
		api.insertRecord(1, "name", "lastname");
		api.insertRecord(1, "address", "529 saratoga ave");
		api.insertRecord(1, "weight", "150");

		api.insertRecord(2, "name", "firstname");
		api.insertRecord(2, "age", "10");

		List<FactDbRecordImpl> retrivedRecords = api.retriveRecord(1, "age",
				"name", "address", "weight");
		Assert.assertEquals(4, retrivedRecords.size());
	}

	/**
	 * Getting data with no keys 
	 */
	public void testGetDataWithNoKeys() {
		api.insertRecord(1, "name", "firstname");
		api.insertRecord(1, "age", "10");

		List<FactDbRecordImpl> retrivedRecords = api.retriveRecord(1);
		Assert.assertEquals(0, retrivedRecords.size());
	}

	/**
	 * Testing the time stamp 
	 * @throws Exception
	 */
	public void testTimeStamp() throws Exception {
		long startTime = System.currentTimeMillis();
		Thread.sleep(10);

		api.insertRecord(1, "name", "firstname");
		List<FactDbRecordImpl> retrivedRecords = api.retriveRecord(1, "name");

		Assert.assertEquals(1, retrivedRecords.size());

		FactDbRecordImpl data = retrivedRecords.get(0);
		Assert.assertTrue(data.getMillis() - startTime >= 10);

	}

}
