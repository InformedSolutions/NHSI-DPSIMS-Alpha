package com.informed.data.tests;

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
import com.informed.data.ApiKeyRepository;
import com.informed.domain.ApiKey;
import com.informed.utilities.ServiceException;

public class ApiKeyRepositoryTest {

	private static ApiKeyRepository apiKeyRepository;
	private static Gson gson;
	private static Resource resource;
	private static String adminKeyId = "admin-key";
	private static String testClientKeyId = "test-key";

	/**
	 * Common setup method for use in tests.
	 */
	@BeforeClass
	public static void setup() {
		apiKeyRepository = new ApiKeyRepository();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").setPrettyPrinting().create();
		String testKeyStoreLocation = "src/main/resources/test-keys.json";

		// Spring's reflection library is used here to set the injected property
		// value for testing purposes
		Field field = ReflectionUtils.findField(ApiKeyRepository.class, "keyStoreLocation");
		field.setAccessible(true);
		ReflectionUtils.setField(field, apiKeyRepository, testKeyStoreLocation);

		List<ApiKey> testKeys = new ArrayList<ApiKey>();
		ApiKey adminKey = new ApiKey(adminKeyId);
		adminKey.grantAuthority("ROLE_ADMIN");
		testKeys.add(adminKey);

		testKeys.add(new ApiKey(testClientKeyId));

		resource = new FileSystemResource(testKeyStoreLocation);
		try (Writer writer = new FileWriter(resource.getFile())) {
			gson.toJson(testKeys, writer);
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
	public void canReadUsingApiRepository() {
		List<ApiKey> keys = apiKeyRepository.getAll();
		assertNotNull(keys);
		assertTrue(keys.size() == 2);
	}

	@Test
	public void canGetByIdUsingApiRepository() {
		ApiKey key = apiKeyRepository.getById(adminKeyId);
		assertNotNull(key);
	}

	@Test
	public void canGetByAuthorityUsingApiRepository() {
		List<ApiKey> keys = apiKeyRepository.getByAuthority("ROLE_ADMIN");
		assertNotNull(keys);
		assertTrue(keys.size() == 1);
	}

	// This test covers write scenarios so no Create test is needed
	@Test
	public void canUpdateUsingApiRepository() {
		ApiKey key = apiKeyRepository.getById(testClientKeyId);
		key.grantAuthority("ROLE_TEST");
		apiKeyRepository.update(key);

		// Reload key to check changes persisted
		key = apiKeyRepository.getById(testClientKeyId);

		assertNotNull(key);
		assertTrue(key.getAuthorities().size() == 2);
	}

	@Test
	public void canDeleteUsingApiRepository() {
		List<ApiKey> keys = apiKeyRepository.getAll();
		assertTrue(keys.size() == 2);

		apiKeyRepository.add(new ApiKey("test-key-2"));

		keys = apiKeyRepository.getAll();
		assertTrue(keys.size() == 3);

		apiKeyRepository.delete("test-key-2");
		keys = apiKeyRepository.getAll();
		assertTrue(keys.size() == 2);
	}

}
