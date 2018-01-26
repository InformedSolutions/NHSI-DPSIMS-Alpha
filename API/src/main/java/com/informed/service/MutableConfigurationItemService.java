package com.informed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informed.data.MutableConfigurationItemRepository;
import com.informed.domain.MutableConfigurationItem;
import com.informed.utilities.ResourceNotFoundException;
import com.informed.utilities.ServiceException;

/**
 * A service for managing configuration items liable to change at runtime.
 * 
 * @author James Cruddas
 *
 */
@Service
public class MutableConfigurationItemService {

	@Autowired
	private MutableConfigurationItemRepository mutableConfigurationItemRepository;

	/**
	 * Retrieves all configuration items.
	 * 
	 * @return list of configuration items
	 */
	public List<MutableConfigurationItem> getAll() {
		return mutableConfigurationItemRepository.getAll();
	}

	/**
	 * Retrieves a configuration item by a given key.
	 * 
	 * @param key
	 *            key of the configuration item
	 * @return configuration item key matching the supplied key
	 */
	public MutableConfigurationItem getByKey(String key) {
		MutableConfigurationItem mutableConfigurationItem = mutableConfigurationItemRepository.getByKey(key);
		if (mutableConfigurationItem == null) {
			throw new ResourceNotFoundException();
		}
		return mutableConfigurationItem;
	}


	/**
	 * Creates a new configuration item.
	 * @param key the key under which the configuration item will be stored
	 * @param value the value of the configuration item
	 * @return a JSON representation of the newly created configuration item
	 */
	public MutableConfigurationItem add(String key, Object value) {

		MutableConfigurationItem mutableConfigurationItem = mutableConfigurationItemRepository.getByKey(key);

		if (mutableConfigurationItem != null) {
			throw new ServiceException("A configuration item with the key " + key + " already exists");
		}

		if (value == null) {
			throw new ServiceException("A value must be supplied to create a configuration item");
		}
		
		if (!key.matches("^[a-z]+(-[a-z]+)*$")) {
			throw new ServiceException("Configuration item keys must be lowercase and hyphen delimited");
		}
		
		mutableConfigurationItem = new MutableConfigurationItem(key, value);
		mutableConfigurationItemRepository.add(mutableConfigurationItem);
		return mutableConfigurationItem;
	}	
	
	/**
	 * Deletes a configuration item.
	 * 
	 * @param key
	 *            identifier of the configuration item to be deleted
	 */
	public void delete(String key) {
		MutableConfigurationItem mutableConfigurationItem = mutableConfigurationItemRepository.getByKey(key);

		if (mutableConfigurationItem == null) {
			throw new ResourceNotFoundException();
		}
		
		mutableConfigurationItemRepository.delete(key);
	}

	/**
	 * Updates a configuration item with a new value.
	 * @param key the identifier for a configuration item
	 * @param value a revised value to be set
	 */
	public void update(String key, Object value) {
		MutableConfigurationItem mutableConfigurationItem = mutableConfigurationItemRepository.getByKey(key);

		if (mutableConfigurationItem == null) {
			throw new ResourceNotFoundException();
		}
		
		mutableConfigurationItem.setValue(value);
		mutableConfigurationItemRepository.update(mutableConfigurationItem);
	}
}
