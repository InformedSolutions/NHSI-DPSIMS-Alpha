package com.informed.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.informed.utilities.RestResponseResource;
import com.informed.utilities.ServiceException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * A Spring REST controller for managing incidents
 * 
 * @author David Taylor
 *
 */
@RestController
public class IncidentController extends RestServiceController {

	/**
	 * Lists all incidents accessible to a user recorded in past X days.
	 * 
	 * @throws ServiceException a custom runtime exception implementation
	 * @ApiOperation Used to show purpose of method in Swagger UI documentation
	 * @ApiResponses Used to filter response types that can be returned in
	 *               Swagger UI documentation
	 * @RequestMapping Maps an incoming URL segment to method and documents
	 *                 outputs types that will be returned
	 * @ResponseBody Instructs Spring to bind the operation's response to the
	 *               web response body
	 */
	@RequestMapping(value = "v1/incidents", method = RequestMethod.GET)
	@ApiOperation(value = "Lists all incidents accessible to a user recorded in past X days", notes = "Lists all incidents accessible to a user recorded in past X days")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Service error", response = RestResponseResource.class) })
	public String getIncidents(String status) {
		return "Authenticated as ";
	}
}
