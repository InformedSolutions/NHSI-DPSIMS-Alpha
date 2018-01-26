package com.informed.domain.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import com.informed.domain.ApiKey;

public class ApiKeyTest {

	@Test
	public void canCreateApiKeyWithGivenId() {
		ApiKey key = new ApiKey("testKey");
		assertNotNull(key);
	}

	@Test
	public void apiKeyRetainsId() {
		String testKeyId = "test";
		ApiKey key = new ApiKey(testKeyId);
		String keyId = key.getId();
		assertFalse(keyId.isEmpty());
		assertEquals(keyId, testKeyId);
	}

	@Test
	public void apiKeyIsAllocatedUniqueIdenifier() {
		ApiKey key = new ApiKey("testKey");
		assertNotNull(key);
		assertFalse(key.getKey().isEmpty());
	}

	@Test
	public void apiKeyIsAllocatedCreatedDate() {
		ApiKey key = new ApiKey("testKey");
		Object createdPropertyClass = key.getCreated().getClass();
		assertNotNull(key);
		assertEquals(createdPropertyClass, Date.class);
	}

	@Test
	public void apiKeyInstantiatesWithDefaultRole() {
		ApiKey key = new ApiKey("testKey");
		Collection<? extends GrantedAuthority> authorities = key.getAuthorities();
		boolean defaultRoleFound = false;

		for (GrantedAuthority authorityEntry : authorities) {
			if (authorityEntry.getAuthority().equals("ROLE_API_CLIENT")) {
				defaultRoleFound = true;
			}
		}

		assertTrue(defaultRoleFound);
		assertTrue(authorities.size() == 1);
	}

	@Test
	public void canGrantAuthorityToApiKey() {
		ApiKey key = new ApiKey("testKey");
		key.grantAuthority("ROLE_TEST");

		Collection<? extends GrantedAuthority> authorities = key.getAuthorities();
		boolean additionalRoleFound = false;

		for (GrantedAuthority authorityEntry : authorities) {
			if (authorityEntry.getAuthority().equals("ROLE_TEST")) {
				additionalRoleFound = true;
			}
		}

		assertTrue(additionalRoleFound);
		assertTrue(authorities.size() == 2);
	}

	@Test
	public void canRemoveAuthorityFromApiKey() {
		ApiKey key = new ApiKey("testKey");
		String testRole = "ROLE_TEST";
		key.grantAuthority(testRole);
		key.removeAuthority(testRole);

		Collection<? extends GrantedAuthority> authorities = key.getAuthorities();
		boolean removedRoleFound = false;

		for (GrantedAuthority authorityEntry : authorities) {
			if (authorityEntry.getAuthority().equals(testRole)) {
				removedRoleFound = true;
			}
		}

		assertFalse(removedRoleFound);
		assertTrue(authorities.size() == 1);
	}

}
