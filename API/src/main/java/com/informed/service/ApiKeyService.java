package com.informed.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.informed.data.ApiKeyRepository;
import com.informed.domain.ApiKey;
import com.informed.utilities.ResourceNotFoundException;
import com.informed.utilities.ServiceException;

/**
 * A custom authentication service for API key usage.
 * 
 * @author James Cruddas
 *
 */
@Service
public class ApiKeyService implements UserDetailsService {

	@Autowired
	private ApiKeyRepository apiKeyRepository;

	/**
	 * Custom override of the Spring Security loadUserByUsername service amended
	 * to use custom API key repository.
	 */
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		ApiKey apiKey = apiKeyRepository.getById(name);
		if (apiKey == null) {
			throw new UsernameNotFoundException("Bad credentials");
		}
		return new User(apiKey.getId(), apiKey.getKey(), apiKey.getAuthorities());
	}

	/**
	 * Retrieves all active API clients.
	 * 
	 * @return list of current active API clients
	 */
	public List<ApiKey> getAll() {
		return apiKeyRepository.getAll();
	}

	/**
	 * Retrieves a known API key by identifier.
	 * 
	 * @param id
	 *            identifier for the API key
	 * @return API key matching the supplied identifier
	 */
	public ApiKey getById(String id) {
		ApiKey key = apiKeyRepository.getById(id);
		if (key == null) {
			throw new ResourceNotFoundException();
		}
		return key;
	}

	/**
	 * Creates a new API key.
	 * 
	 * @param id
	 *            identifier to be used for the API key
	 * @return newly created API key
	 */
	public ApiKey add(String id) {
		
		//Null check is performed here as an empty string posted to Spring results in a null object
		if (id == null || id.isEmpty()) {
			throw new ServiceException("Cannot create an API key with an empty identifier");
		}
		
		ApiKey key = apiKeyRepository.getById(id);

		if (key != null) {
			throw new ServiceException("An API key with the identifier " + id + " already exists");
		}
				
		if (!id.matches("^[a-z]+(-[a-z]+)*$")) {
			throw new ServiceException("API key identifier must be at least one character, lowercase, and hyphen delimited");
		}

		key = new ApiKey(id);
		apiKeyRepository.add(key);
		return key;
	}

	/**
	 * Deletes an API key.
	 * 
	 * @param id
	 *            identifier of the API key to be deleted
	 */
	public void delete(String id) {
		ApiKey key = apiKeyRepository.getById(id);

		if (key == null) {
			throw new ResourceNotFoundException();
		}

		if (apiKeyRepository.getById(id).hasAuthority("ROLE_ADMIN")
				&& apiKeyRepository.getByAuthority("ROLE_ADMIN").size() == 1) {
			throw new ServiceException(
					"Cannot delete key as this would leave no remaining administrative keys available");
		}

		apiKeyRepository.delete(id);
	}

	/**
	 * Retrieves a known API key by identifier.
	 * 
	 * @param id
	 *            identifier for the API key
	 * @return API key matching the supplied identifier
	 */
	public Collection<? extends GrantedAuthority> getAuthoritiesById(String id) {
		ApiKey key = getById(id);
		return key.getAuthorities();
	}

	/**
	 * Grants an authority to a given key.
	 * 
	 * @param id
	 *            identifier to be used for the API key
	 * @return revised API key
	 */
	public ApiKey grantAuthority(String id, String authority) {
		ApiKey key = getById(id);
		key.grantAuthority(authority);
		apiKeyRepository.update(key);
		return key;
	}

	/**
	 * Removes an authority for a given key.
	 * 
	 * @param id
	 *            identifier to be used for the API key
	 * @return revised API key
	 */
	public ApiKey removeAuthority(String id, String authority) {
		ApiKey key = getById(id);
		key.removeAuthority(authority);
		apiKeyRepository.update(key);
		return key;
	}

}
