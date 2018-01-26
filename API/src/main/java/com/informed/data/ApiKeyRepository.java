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
import com.informed.domain.ApiKey;
import com.informed.utilities.Loggable;
import com.informed.utilities.ServiceException;

/**
 * A repository pattern implementation for retrieving API keys from a local JSON
 * file.
 * 
 * @author James Cruddas
 */
@Repository
public class ApiKeyRepository extends Loggable {

	/**
	 * Name of file that keys are stored.
	 */
	@Value("${keys.location}")
	private String keyStoreLocation;

	/**
	 * Private resource holder used for accessing system assets.
	 */
	private Resource resource;

	/**
	 * Private GSON builder for deserializing JSON objects used to store API
	 * keys.
	 */
	private Gson gson;

	/**
	 * Class type definition to support deserialization parameters.
	 */
	private Type collectionType;

	/**
	 * Default constructor.
	 */
	public ApiKeyRepository() {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").setPrettyPrinting().create();
		collectionType = new TypeToken<ArrayList<ApiKey>>() {
		}.getType();
	}

	/**
	 * Method for returning all known API keys.
	 * 
	 * @return A list of API keys
	 */
	public List<ApiKey> getAll() {

		resource = new FileSystemResource(keyStoreLocation);

		try (JsonReader reader = new JsonReader(new InputStreamReader(resource.getInputStream()))) {

			this.logDebug("Attempting to read keystore " + resource.getFilename() + " from path "
					+ resource.getFile().getAbsolutePath());
			
			return gson.fromJson(reader, collectionType);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Method for retrieving a known API key by id.
	 * 
	 * @param id
	 *            the identifier of an API key
	 * @return an API key domain object representation
	 */
	public ApiKey getById(String id) {
		List<ApiKey> keys = getAll();
		
		for (ApiKey key : keys) {
			if (id.equals(key.getId())) {
				return key;
			}
		}
		
		return null;
	}

	/**
	 * Method for retrieving by a granted authority type.
	 * 
	 * @param authority
	 *            the authority to search for
	 * @return a list of API keys with the matching authority
	 */
	public List<ApiKey> getByAuthority(String authority) {
		List<ApiKey> keys = getAll();
		List<ApiKey> keysWithMatchingAuthority = new ArrayList<ApiKey>();

		for (ApiKey key : keys) {
			if (key.hasAuthority(authority)) {
				keysWithMatchingAuthority.add(key);
			}
		}

		return keysWithMatchingAuthority;
	}

	/**
	 * Creates a new API key.
	 * 
	 * @param key
	 *            API key object to be added to the repository
	 */
	public void add(ApiKey key) {
		List<ApiKey> keys = getAll();
		keys.add(key);
		writeToFile(keys);
		this.logInfo("Created API key with identifier " + key.getId());
	}

	/**
	 * Deletes an API key.
	 * 
	 * @param id
	 *            for API key to be deleted
	 */
	public void delete(String id) {

		List<ApiKey> keys = getAll();

		ListIterator<ApiKey> iterator = keys.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId().equals(id)) {
				iterator.remove();
			}
		}

		writeToFile(keys);
		this.logInfo("Deleted API key with identifier " + id);
	}

	/**
	 * Updates an API key with new values.
	 * 
	 * @param key
	 *            a modified API key object.
	 */
	public void update(ApiKey key) {
		List<ApiKey> keys = (ArrayList<ApiKey>) getAll();

		ListIterator<ApiKey> iterator = keys.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId().equals(key.getId())) {
				iterator.set(key);
			}
		}

		writeToFile(keys);
		this.logInfo("Updated API key with identifier " + key.getId());
	}

	/**
	 * Private helper method for writing API key objects to a JSON file.
	 * 
	 * @param keys
	 *            a revised collection of API keys to be persisted in a JSON
	 *            file.
	 */
	private void writeToFile(List<ApiKey> keys) {
		resource = new FileSystemResource(keyStoreLocation);

		try (Writer writer = new FileWriter(resource.getFile())) {
			this.logDebug("Attempting to write to keystore " + resource.getFilename() + " on path "
					+ resource.getFile().getAbsolutePath());
			
			gson.toJson(keys, writer);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}
}
