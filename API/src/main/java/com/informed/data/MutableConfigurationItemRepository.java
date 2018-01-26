package com.informed.data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.informed.domain.MutableConfigurationItem;
import com.informed.utilities.Loggable;
import com.informed.utilities.ServiceException;

/**
 * Repository pattern implementation for retrieving configuration items liable
 * to change at runtime.
 * 
 * @author James Cruddas
 *
 */
@Repository
public class MutableConfigurationItemRepository extends Loggable {

	/**
	 * Name of file that configuration is stored.
	 */
	@Value("${mutable.configuration.location}")
	private String mutableConfigurationLocation;

	/**
	 * Private resource holder used for accessing system assets.
	 */
	private Resource resource;

	/**
	 * Private GSON builder for deserializing JSON objects used to store
	 * configuration items.
	 */
	private Gson gson;

	/**
	 * Class type definition to support deserialization parameters.
	 */
	private Type collectionType;

	/**
	 * Default constructor.
	 */
	public MutableConfigurationItemRepository() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		collectionType = new TypeToken<ArrayList<MutableConfigurationItem>>() {
		}.getType();
	}

	/**
	 * Method for returning all mutable configuration items.
	 * 
	 * @return A list of configuration items
	 */
	public List<MutableConfigurationItem> getAll() {

		resource = new FileSystemResource(mutableConfigurationLocation);

		try (JsonReader reader = new JsonReader(new InputStreamReader(resource.getInputStream()))) {

			this.logDebug("Attempting to read configuration file " + resource.getFilename() + " from path "
					+ resource.getFile().getAbsolutePath());

			return gson.fromJson(reader, collectionType);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Method for retrieving a configuration item by key.
	 * 
	 * @param key
	 *            the key for a configuration item
	 * @return a configuration item domain object representation
	 */
	public MutableConfigurationItem getByKey(String key) {
		List<MutableConfigurationItem> mutableConfigurationItems = getAll();

		for (MutableConfigurationItem mutableConfigurationItem : mutableConfigurationItems) {
			if (key.equals(mutableConfigurationItem.getKey())) {
				return mutableConfigurationItem;
			}
		}

		return null;
	}

	/**
	 * Creates a new configuration item.
	 * 
	 * @param key
	 *            Configuration item object to be added to the repository
	 */
	public void add(MutableConfigurationItem key) {
		List<MutableConfigurationItem> mutableConfigurationItems = getAll();
		mutableConfigurationItems.add(key);
		writeToFile(mutableConfigurationItems);
		this.logInfo("Created configuration item with key " + key.getKey());
	}

	/**
	 * Deletes a configuration item.
	 * 
	 * @param key
	 *            key of the configuration item to be deleted
	 */
	public void delete(String key) {

		List<MutableConfigurationItem> mutableConfigurationItems = getAll();

		ListIterator<MutableConfigurationItem> iterator = mutableConfigurationItems.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getKey().equals(key)) {
				iterator.remove();
			}
		}

		writeToFile(mutableConfigurationItems);
		this.logInfo("Deleted configuration item with key " + key);
	}

	/**
	 * Updates a configuration item with a new value.
	 * 
	 * @param mutableConfigurationItem
	 *            a modified configuration item object.
	 */
	public void update(MutableConfigurationItem mutableConfigurationItem) {
		List<MutableConfigurationItem> mutableConfigurationItems = (ArrayList<MutableConfigurationItem>) getAll();

		ListIterator<MutableConfigurationItem> iterator = mutableConfigurationItems.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getKey().equals(mutableConfigurationItem.getKey())) {
				iterator.set(mutableConfigurationItem);
			}
		}

		writeToFile(mutableConfigurationItems);
		this.logInfo("Updated configuration item with key " + mutableConfigurationItem.getKey());
	}

	/**
	 * Private helper method for writing configuration item objects to a JSON
	 * file.
	 * 
	 * @param mutableConfigurationItems
	 *            a revised collection of configuration keys to be persisted in
	 *            a JSON file.
	 */
	private void writeToFile(List<MutableConfigurationItem> mutableConfigurationItems) {
		resource = new FileSystemResource(mutableConfigurationLocation);

		try (Writer writer = new FileWriter(resource.getFile())) {
			this.logDebug("Attempting to write to configuration file " + resource.getFilename() + " on path "
					+ resource.getFile().getAbsolutePath());
			
			gson.toJson(mutableConfigurationItems, writer);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}
}
