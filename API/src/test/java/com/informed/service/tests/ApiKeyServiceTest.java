package com.informed.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.informed.data.ApiKeyRepository;
import com.informed.domain.ApiKey;
import com.informed.service.ApiKeyService;
import com.informed.utilities.ResourceNotFoundException;
import com.informed.utilities.ServiceException;

public class ApiKeyServiceTest {

	@InjectMocks
	private ApiKeyService apiKeyService;

	@Mock
	private ApiKeyRepository apiKeyRepository;

	@Captor
	private ArgumentCaptor<ApiKey> apiKeyCaptor;

	private String testAdminApiKeyId = "admin-key";
	private ApiKey testAdminApiKey;

	private String testClientApiKeyId = "test-key";
	private ApiKey testClientApiKey;

	private List<ApiKey> testApiKeyCollection;

	/**
	 * Method for initialising mock objects.
	 */
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		testAdminApiKey = new ApiKey(testAdminApiKeyId);
		testAdminApiKey.grantAuthority("ROLE_ADMIN");
		testClientApiKey = new ApiKey(testClientApiKeyId);

		testApiKeyCollection = new ArrayList<ApiKey>();
		testApiKeyCollection.add(testAdminApiKey);
		testApiKeyCollection.add(testClientApiKey);

		// Used to support tests to prevent no administrative keys remaining
		List<ApiKey> adminOnlyApiKeyCollection = new ArrayList<ApiKey>();
		adminOnlyApiKeyCollection.add(testAdminApiKey);

		Mockito.when(apiKeyRepository.getAll()).thenReturn(testApiKeyCollection);
		Mockito.when(apiKeyRepository.getById(testAdminApiKeyId)).thenReturn(testAdminApiKey);
		Mockito.when(apiKeyRepository.getById(testClientApiKeyId)).thenReturn(testClientApiKey);
		Mockito.when(apiKeyRepository.getByAuthority("ROLE_ADMIN")).thenReturn(adminOnlyApiKeyCollection);
	}

	@Test
	public void canGetApiKeyFromApiKeyService() {
		List<ApiKey> keys = apiKeyService.getAll();
		assertTrue(keys.size() == testApiKeyCollection.size());
		assertNotNull(keys);
	}

	@Test
	public void canGetApiKeyFromApiKeyServiceById() {
		ApiKey testKey = apiKeyService.getById(testClientApiKeyId);
		assertEquals(testClientApiKey, testKey);
	}

	@Test
	public void canAddNewApiKey() {
		apiKeyService.add("new-key");
		Mockito.verify(apiKeyRepository, times(1)).add(apiKeyCaptor.capture());
	}

	@Test
	public void canDeleteApiKey() {
		apiKeyService.delete(testClientApiKeyId);
		Mockito.verify(apiKeyRepository, times(1)).delete(testClientApiKeyId);
	}

	@Test
	public void canGetAuthoritiesFromApiKeyService() {
		Collection<? extends GrantedAuthority> authorities = apiKeyService.getAuthoritiesById(testClientApiKeyId);
		assertNotNull(authorities);
		assertTrue(authorities.size() == 1);
	}

	@Test
	public void canGrantAuthorityUsingApiKeyService() {
		apiKeyService.grantAuthority(testClientApiKeyId, "ROLE_TEST");
		assertTrue(testClientApiKey.getAuthorities().size() == 2);
	}

	@Test
	public void canRemoveAuthorityUsingApiKeyService() {
		apiKeyService.removeAuthority(testClientApiKeyId, "ROLE_API_CLIENT");
		assertTrue(testClientApiKey.getAuthorities().size() == 0);
	}

	@Test
	public void userDetailsObjectReturnedWhenValidApiKeySupplied() {
		Mockito.when(apiKeyRepository.getById(testClientApiKeyId)).thenReturn(testClientApiKey);
		UserDetails userDetails = apiKeyService.loadUserByUsername(testClientApiKeyId);

		assertEquals(userDetails.getClass(), User.class);
		assertNotNull(userDetails);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void unknownUserExceptionThrownWhenInvalidApiKeySuppliedOnFind() {
		Mockito.when(apiKeyRepository.getById(testClientApiKeyId)).thenReturn(null);
		apiKeyService.loadUserByUsername(testClientApiKeyId);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void unknownUserExceptionThrownWhenInvalidApiKeySuppliedOnFindAuthorities() {
		Mockito.when(apiKeyRepository.getById(testClientApiKeyId)).thenReturn(null);
		apiKeyService.getAuthoritiesById(testClientApiKeyId);
	}

	@Test(expected = ServiceException.class)
	public void cannotCreateDuplicateApiKeys() {
		// Key already exists from setup method
		apiKeyService.add(testClientApiKeyId);
	}

	@Test(expected = ServiceException.class)
	public void cannotAddNewApiKeyWithWhitespaceInIndentifier() {
		apiKeyService.add("new key");
	}

	@Test(expected = ServiceException.class)
	public void cannotAddNewApiKeyWithEmptyIndentifier() {
		apiKeyService.add("");
	}
	
	@Test(expected = ServiceException.class)
	public void cannotAddNewApiKeyWithNullIdentifier() {
		apiKeyService.add(null);
	}
	
	@Test(expected = ServiceException.class)
	public void cannotAddNewApiKeyWithSpecialCharactersInIndentifier() {
		apiKeyService.add("key!");
	}

	@Test(expected = ServiceException.class)
	public void cannotRemoveAllAdminKeys() {
		apiKeyService.delete(testAdminApiKeyId);
	}
	
	
}
