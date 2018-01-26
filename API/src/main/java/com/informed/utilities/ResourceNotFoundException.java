package com.informed.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * A custom 404 exception type for use by RESTful services.
 * 
 * @author James Cruddas
 *
 */
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * A list of errors to be returned to a client.
	 */
	private List<String> errors;

	/**
	 * Default constructor.
	 */
	public ResourceNotFoundException() {
		super("The requested resource could not be found");
		errors = new ArrayList<String>();
		errors.add("The requested resource could not be found");
	}

	/**
	 * Get method for accessing a list of errors to be returned to a client.
	 * @return a list of errors to be returned to a client
	 */
	public List<String> getErrors() {
		return errors;
	}

}