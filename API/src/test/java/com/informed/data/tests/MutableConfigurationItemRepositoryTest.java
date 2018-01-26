package com.informed.data.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ReflectionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.informed.data.MutableConfigurationItemRepository;
import com.informed.domain.MutableConfigurationItem;
import com.informed.utilities.ServiceException;

public class MutableConfigurationItemRepositoryTest {

	private static MutableConfigurationItemRepository mutableConfigurationItemRepository;
	private static Gson gson;
	private static Resource resource;

	private static String configurationItem1Key = "configuraton-item-1-key";
	private static String configurationItem1Value = "configuraton-item-1-value";

	private static String configurationItem2Key = "configuraton-item-2-key";
	private static String configurationItem2Value = "configuraton-item-2-value";

	/**
	 * Common setup method for use in tests.
	 */
	@BeforeClass
	public static void setup() {
		mutableConfigurationItemRepository = new MutableConfigurationItemRepository();
		gson = new GsonBuilder().setPrettyPrinting().create();
		String testConfigurationLocation = "src/main/resources/test-mutable-configuration.json";

		// Spring's reflection library is used here to set the injected property
		// value for testing purposes
		Field field = ReflectionUtils.findField(MutableConfigurationItemRepository.class, "mutableConfigurationLocation");
		field.setAccessible(true);
		ReflectionUtils.setField(field, mutableConfigurationItemRepository, testConfigurationLocation);

		List<MutableConfigurationItem> testConfigurationItems = new ArrayList<MutableConfigurationItem>();

		MutableConfigurationItem item1 = new MutableConfigurationItem(configurationItem1Key, configurationItem1Value);
		MutableConfigurationItem item2 = new MutableConfigurationItem(configurationItem2Key, configurationItem2Value);

		testConfigurationItems.add(item1);
		testConfigurationItems.add(item2);

		resource = new FileSystemResource(testConfigurationLocation);
		try (Writer writer = new FileWriter(resource.getFile())) {
			gson.toJson(testConfigurationItems, writer);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Common teardown method for use in tests.
	 */
	@AfterClass
	public static void tearDown() {
		try {
			// After tests have been executed, remove the test-keys.json file
			resource.getFile().delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void canReadMutableConfigurationItemsUsingApiRepository() {
		List<MutableConfigurationItem> mutableConfigurationItems = mutableConfigurationItemRepository.getAll();
		assertNotNull(mutableConfigurationItems);
		assertTrue(mutableConfigurationItems.size() == 2);
	}

	@Test
	public void canGetByKeyUsingMutableConfigurationItemRepository() {
		MutableConfigurationItem mutableConfigurationItem = mutableConfigurationItemRepository.getByKey(configurationItem1Key);
		assertNotNull(mutableConfigurationItem);
	}

	// This test covers write scenarios so no Create test is needed
	@Test
	public void canUpdateUsingMutableApiRepository() {
		MutableConfigurationItem mutableConfigurationItem = mutableConfigurationItemRepository.getByKey(configurationItem1Key);
		String newValue = "new";
		mutableConfigurationItem.setValue(newValue);
		mutableConfigurationItemRepository.update(mutableConfigurationItem);

		// Reload key to check changes persisted
		mutableConfigurationItem = mutableConfigurationItemRepository.getByKey(configurationItem1Key);

		assertNotNull(mutableConfigurationItem);
		assertEquals(mutableConfigurationItem.getValue(), newValue);
	}

	@Test
	public void canDeleteUsingMutableConfigurationItemRepository() {
		List<MutableConfigurationItem> mutableConfigurationItems = mutableConfigurationItemRepository.getAll();
		assertTrue(mutableConfigurationItems.size() == 2);

		mutableConfigurationItemRepository.add(new MutableConfigurationItem("item-3", "test value"));

		mutableConfigurationItems = mutableConfigurationItemRepository.getAll();
		assertTrue(mutableConfigurationItems.size() == 3);

		mutableConfigurationItemRepository.delete("item-3");
		mutableConfigurationItems = mutableConfigurationItemRepository.getAll();
		assertTrue(mutableConfigurationItems.size() == 2);
	}

}
