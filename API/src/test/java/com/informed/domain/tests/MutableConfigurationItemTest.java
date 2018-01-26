package com.informed.domain.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import com.informed.domain.MutableConfigurationItem;

public class MutableConfigurationItemTest {

	String testKeyId = "test";
	String testValue = "test value";
	
	@Test
	public void canCreateConfigurationItemWithKey() {
		MutableConfigurationItem mutableConfigurationItem = new MutableConfigurationItem(testKeyId, testValue);
		assertNotNull(mutableConfigurationItem);
	}


	@Test
	public void configurationItemRetainsKey() {
		MutableConfigurationItem mutableConfigurationItem = new MutableConfigurationItem(testKeyId, testValue);
		String configurationItemId = mutableConfigurationItem.getKey();
		assertFalse(configurationItemId.isEmpty());
		assertEquals(configurationItemId, testKeyId);
	}
	
	@Test
	public void configurationItemRetainsValue() {
		MutableConfigurationItem mutableConfigurationItem = new MutableConfigurationItem(testKeyId, testValue);
		assertEquals(mutableConfigurationItem.getValue(), testValue);
	}

	@Test
	public void canUpdateConfigurationItemStringValue() {
		MutableConfigurationItem mutableConfigurationItem = new MutableConfigurationItem(testKeyId, testValue);
		String newValue = "new";
		mutableConfigurationItem.setValue(newValue);
		assertEquals(mutableConfigurationItem.getValue(), newValue);
	}
	
	@Test
	public void canUpdateConfigurationItemValueWithObject() {
		MutableConfigurationItem mutableConfigurationItem = new MutableConfigurationItem(testKeyId, testValue);
		Date newValue = new Date();;
		mutableConfigurationItem.setValue(newValue);
		assertEquals(mutableConfigurationItem.getValue(), newValue);
	}
}
