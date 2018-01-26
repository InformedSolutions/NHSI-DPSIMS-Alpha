package com.informed.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * A custom 500 exception type for use by RESTful services.
 * 
 * @author James Cruddas
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {

	/**
	 * Private collection of errors for aggregation purposes.
	 */
	private List<String> errors;

	/**
	 * Default constructor.
	 */
	public ServiceException() {
		super("An error occurred whilst processing the request");
	}

	/**
	 * Constructor that accepts a generic exception type.
	 * 
	 * @param e
	 *            exception to be embodied in custom exception type
	 */
	public ServiceException(Exception e) {
		super(e);
		errors = new ArrayList<String>();
		errors.add(e.getMessage());
	}

	/**
	 * Constructor that accepts a string to set the message in custom exception
	 * type.
	 * 
	 * @param message
	 *            Exception to be embodied in custom exception type
	 */
	public ServiceException(String message) {
		super("An error occurred whilst processing the request");
		errors = new ArrayList<String>();
		errors.add(message);
	}

	/**
	 * Method for appending errors to a service exception.
	 * 
	 * @param message
	 *            the error text to be appended to the error collection.
	 */
	public void addError(String message) {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		this.errors.add(message);
	}

	/**
	 * Method for retrieving error messages contained in the exception.
	 * 
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

}
