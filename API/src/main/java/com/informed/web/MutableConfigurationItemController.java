package com.informed.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.informed.domain.MutableConfigurationItem;
import com.informed.service.MutableConfigurationItemService;
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
public class MutableConfigurationItemController extends Loggable {

	/**
	 * Configuration item service layer injector.
	 */
	@Autowired
	private MutableConfigurationItemService mutableConfigurationItemService;

	/**
	 * Returns a list of active API clients.
	 */
	@RequestMapping(value = "configuration-items", method = RequestMethod.GET)
	@ApiOperation(value = "Gets all active configuration items", notes = "Gets all active configuration items", response = MutableConfigurationItem.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<MutableConfigurationItem> getAll() {
		return mutableConfigurationItemService.getAll();
	}

	/**
	 * Gets a specific Configuration item by key.
	 * 
	 */
	@RequestMapping(value = "configuration-items/{key}", method = RequestMethod.GET)
	@ApiOperation(value = "Gets a specific configuration item", notes = "Gets a specific configuration item", response = MutableConfigurationItem.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Resource not found", response = RestResponseResource.class),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public MutableConfigurationItem getByKey(@PathVariable(value = "key") String key) {
		return mutableConfigurationItemService.getByKey(key);
	}

	/**
	 * Adds a new configuration item.
	 * 
	 */
	@RequestMapping(value = "configuration-items", method = RequestMethod.POST)
	@ApiOperation(value = "Adds a new configuration item", notes = "Adds a new configuration item", response = MutableConfigurationItem.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public MutableConfigurationItem add(String key, String value) {
		return mutableConfigurationItemService.add(key, value);
	}

	/**
	 * Deletes a configuration item.
	 * 
	 */
	@RequestMapping(value = "configuration-items", method = RequestMethod.DELETE)
	@ApiOperation(value = "Deletes a configuration item", notes = "Deletes a configuration item")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = RestResponseResource.class),
			@ApiResponse(code = 404, message = "Resource not found", response = RestResponseResource.class),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public RestResponseResource delete(String key) {
		mutableConfigurationItemService.delete(key);
		return new RestResponseResource(HttpStatus.OK, "Successfully removed configuration item with key '" + key + "'");
	}

	/**
	 * Updates a configuration item.
	 * 
	 */
	@RequestMapping(value = "configuration-items", method = RequestMethod.PUT)
	@ApiOperation(value = "Updates a configuration item", notes = "Updates a configuration item")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = RestResponseResource.class),
			@ApiResponse(code = 404, message = "Resource not found", response = RestResponseResource.class),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public RestResponseResource update(String key, String value) {
		mutableConfigurationItemService.update(key, value);
		return new RestResponseResource(HttpStatus.OK,
				"Successfully updated configuration item with key '" + key + "' with a value of " + value);
	}

}