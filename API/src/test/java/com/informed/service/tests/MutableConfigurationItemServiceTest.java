package com.informed.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.informed.data.MutableConfigurationItemRepository;
import com.informed.domain.MutableConfigurationItem;
import com.informed.service.MutableConfigurationItemService;
import com.informed.utilities.ServiceException;

public class MutableConfigurationItemServiceTest {

	@InjectMocks
	private MutableConfigurationItemService mutableConfigurationItemService;

	@Mock
	private MutableConfigurationItemRepository mutableConfigurationItemRepository;

	@Captor
	private ArgumentCaptor<MutableConfigurationItem> configurationItemCaptor;

	private static String configurationItem1Key = "configuraton-item-1-key";
	private static String configurationItem1Value = "configuraton-item-1-value";
	private MutableConfigurationItem testConfigurationItem1;
	
	private static String configurationItem2Key = "configuraton-item-2-key";
	private static String configurationItem2Value = "configuraton-item-2-value";
	private MutableConfigurationItem testConfigurationItem2;
	
	private List<MutableConfigurationItem> testConfigurationItemCollection;

	/**
	 * Method for initialising mock objects.
	 */
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		testConfigurationItem1 = new MutableConfigurationItem(configurationItem1Key, configurationItem1Value);
		testConfigurationItem2 = new MutableConfigurationItem(configurationItem2Key, configurationItem2Value);
		testConfigurationItemCollection = new ArrayList<MutableConfigurationItem>();
		
		testConfigurationItemCollection.add(testConfigurationItem1);
		testConfigurationItemCollection.add(testConfigurationItem2);

		Mockito.when(mutableConfigurationItemRepository.getAll()).thenReturn(testConfigurationItemCollection);
		Mockito.when(mutableConfigurationItemRepository.getByKey(configurationItem1Key)).thenReturn(testConfigurationItem1);
		Mockito.when(mutableConfigurationItemRepository.getByKey(configurationItem2Key)).thenReturn(testConfigurationItem2);
	}

	@Test
	public void canGetConfigurationItemsFromConfigurationItemService() {
		List<MutableConfigurationItem> mutableConfigurationItems = mutableConfigurationItemService.getAll();
		assertTrue(mutableConfigurationItems.size() == mutableConfigurationItems.size());
		assertNotNull(mutableConfigurationItems);
	}

	@Test
	public void canGetConfigurationItemByKey() {
		MutableConfigurationItem testConfigurationItem = mutableConfigurationItemService.getByKey(configurationItem1Key);
		assertEquals(testConfigurationItem1, testConfigurationItem);
	}

	@Test
	public void canAddNewConfigurationItem() {
		mutableConfigurationItemService.add("new-item", "new-value");
		Mockito.verify(mutableConfigurationItemRepository, times(1)).add(configurationItemCaptor.capture());
	}

	@Test
	public void canDeleteConfigurationItemUsingKey() {
		mutableConfigurationItemService.delete(configurationItem1Key);
		Mockito.verify(mutableConfigurationItemRepository, times(1)).delete(configurationItem1Key);
	}
	
	@Test
	public void canUpdateConfigurationItemUsingKey() {
		mutableConfigurationItemService.update(configurationItem1Key, "new value");
		Mockito.verify(mutableConfigurationItemRepository, times(1)).update(configurationItemCaptor.capture());
	}

	@Test(expected = ServiceException.class)
	public void cannotAddConfigurationItemWithoutValue() {
		mutableConfigurationItemService.add(configurationItem1Key, null);
	}
	
	@Test(expected = ServiceException.class)
	public void cannotCreateDuplicateConfigurationItems() {
		// Key already exists from setup method
		mutableConfigurationItemService.add(configurationItem1Key, "test value");
	}

	@Test(expected = ServiceException.class)
	public void cannotAddConfigurationItemWithWhitespaceInName() {
		mutableConfigurationItemService.add("new key", "test value");
	}

	@Test(expected = ServiceException.class)
	public void cannotAddConfigurationItemWithSpecialCharactersInName() {
		mutableConfigurationItemService.add("new-key!", "test value");
	}
	
}
