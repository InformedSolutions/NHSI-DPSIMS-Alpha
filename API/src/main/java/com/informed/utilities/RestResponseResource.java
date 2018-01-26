package com.informed.utilities;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * A custom resource that can be returned to callers as JSON object in the event
 * a ServiceException is thrown by the application. You can freely modify this
 * structure to meet your needs.
 * 
 * @author cruddasj
 * 
 */
public class RestResponseResource {

	/**
	 * The HTTP status code to be returned to a client.
	 */
	public int status;

	/**
	 * An ISO-8601 timestamp of when an event occurred.
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Europe/London")
	public Date time;

	/**
	 * A textual message to be returned to a client.
	 */
	public String message;

	/**
	 * Private collection of errors for aggregation purposes.
	 */
	public List<String> errors;

	/**
	 * Default constructor.
	 * 
	 * @param status
	 *            the HTTP status code to be returned to a client.
	 * @param message
	 *            a textual message to be returned to a client.
	 */
	public RestResponseResource(HttpStatus status, String message) {
		this.status = status.value();
		this.message = message;
		this.time = new Date();
	}

	/**
	 * Set method for adding an errors collection in a response.
	 * 
	 * @param errors errors to be added in a response.
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
