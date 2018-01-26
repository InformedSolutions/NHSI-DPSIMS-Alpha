package com.informed.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.informed.domain.ApiKey;
import com.informed.service.ApiKeyService;
import com.informed.utilities.Loggable;
import com.informed.utilities.RestResponseResource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller to expose operations for a particular domain entity via web
 * protocols. "Get all" methods should use a pluralised noun of a particular
 * entity.
 * 
 * @author James Cruddas
 *
 */
@RestController
@RequestMapping(value = "/secure")
public class ApiKeyController extends Loggable {

	/**
	 * API key service layer injector.
	 */
	@Autowired
	private ApiKeyService apiKeyService;

	/**
	 * Returns a list of active API clients.
	 */
	@RequestMapping(value = "api-keys", method = RequestMethod.GET)
	@ApiOperation(value = "Gets all active API keys", notes = "Gets all active API keys", response = ApiKey.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<ApiKey> getAll() {
		return apiKeyService.getAll();
	}

	/**
	 * Gets a specific API key.
	 * 
	 */
	@RequestMapping(value = "api-keys/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Gets a specific API key", notes = "Gets a specific API key", response = ApiKey.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Resource not found", response = RestResponseResource.class),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ApiKey getById(@PathVariable(value = "id") String id) {
		return apiKeyService.getById(id);
	}

	/**
	 * Adds a new API key.
	 * 
	 */
	@RequestMapping(value = "api-keys", method = RequestMethod.POST)
	@ApiOperation(value = "Adds a new API key", notes = "Adds a new API key", response = ApiKey.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ApiKey add(String id) {
		return apiKeyService.add(id);
	}

	/**
	 * Deletes an API key.
	 * 
	 */
	@RequestMapping(value = "api-keys", method = RequestMethod.DELETE)
	@ApiOperation(value = "Deletes an API key", notes = "Deletes an API key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = RestResponseResource.class),
			@ApiResponse(code = 404, message = "Resource not found", response = RestResponseResource.class),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public RestResponseResource delete(String id) {
		apiKeyService.delete(id);
		return new RestResponseResource(HttpStatus.OK, "Successfully removed API key with identifier '" + id + "'");
	}

	/**
	 * Gets the authorities of a specific API key.
	 * 
	 */
	@RequestMapping(value = "api-keys/{id}/authorities", method = RequestMethod.GET)
	@ApiOperation(value = "Gets the authorities of a specific API key", notes = "Gets the authorities of a specific API key", response = GrantedAuthority.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Resource not found", response = RestResponseResource.class),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Collection<? extends GrantedAuthority> getAuthoritiesById(@PathVariable(value = "id") String id) {
		return apiKeyService.getAuthoritiesById(id);
	}

	/**
	 * Gets the authorities of a specific API key.
	 * 
	 */
	@RequestMapping(value = "api-keys/{id}/authorities/{authority}", method = RequestMethod.POST)
	@ApiOperation(value = "Grants an authority to a specific API key", notes = "Grants an authority to a specific API key", response = ApiKey.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Resource not found", response = RestResponseResource.class),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ApiKey grantAuthority(@PathVariable(value = "id") String id,
			@PathVariable(value = "authority") String authority) {
		return apiKeyService.grantAuthority(id, authority);
	}

	/**
	 * Gets the authorities of a specific API key.
	 * 
	 */
	@RequestMapping(value = "api-keys/{id}/authorities/{authority}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Removes an authority of a specific API key", notes = "Removes an authority of a specific API key", response = ApiKey.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Resource not found", response = RestResponseResource.class),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ApiKey removeAuthority(@PathVariable(value = "id") String id,
			@PathVariable(value = "authority") String authority) {
		return apiKeyService.removeAuthority(id, authority);
	}

}