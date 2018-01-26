package com.informed.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.informed.utilities.ResourceNotFoundException;
import com.informed.utilities.RestResponseResource;
import com.informed.utilities.ServiceException;

/**
 * A custom handler for managing ServiceExceptions thrown by the application and
 * returning its details as a JSON object.
 * 
 * @author James Cruddas
 *
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	HttpHeaders headers;

	/**
	 * Default constructor.
	 */
	public RestResponseExceptionHandler() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	/**
	 * A custom handler for yielding client responses in a JSON format when a
	 * Runtime exception is thrown.
	 * 
	 * @param e
	 *            the exception to be wrapped in a JSON object.
	 * @param request
	 *            the originating web request.
	 * @return a JSON representation of a runtime exception.
	 */
	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<Object> handleException(RuntimeException e, WebRequest request) {
		RestResponseResource res = new RestResponseResource(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return handleExceptionInternal(e, res, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	/**
	 * A custom handler for yielding client responses in a JSON format when a
	 * ServiceException exception is thrown.
	 * 
	 * @param e
	 *            the exception to be wrapped in a JSON object.
	 * @param request
	 *            the originating web request.
	 * @return a JSON representation of a ServiceException exception.
	 */
	@ExceptionHandler(value = { ServiceException.class })
	protected ResponseEntity<Object> handleServiceException(ServiceException e, WebRequest request) {
		RestResponseResource res = new RestResponseResource(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		res.setErrors(e.getErrors());
		return handleExceptionInternal(e, res, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	/**
	 * A custom handler for yielding client responses in a JSON format when a
	 * ResourceNotFoundException exception is thrown.
	 * 
	 * @param e
	 *            the exception to be wrapped in a JSON object.
	 * @param request
	 *            the originating web request.
	 * @return a JSON representation of a ResourceNotFoundException exception.
	 */
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException e, WebRequest request) {
		RestResponseResource res = new RestResponseResource(HttpStatus.NOT_FOUND, e.getMessage());
		res.setErrors(e.getErrors());
		return handleExceptionInternal(e, res, headers, HttpStatus.NOT_FOUND, request);
	}

	/**
	 * A custom handler for yielding client responses in a JSON format when a
	 * AccessDeniedException exception is thrown.
	 * 
	 * @param e
	 *            the exception to be wrapped in a JSON object.
	 * @param request
	 *            the originating web request.
	 * @return a JSON representation of a AccessDeniedException exception.
	 */
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
		RestResponseResource res = new RestResponseResource(HttpStatus.FORBIDDEN, e.getMessage());
		return handleExceptionInternal(e, res, headers, HttpStatus.FORBIDDEN, request);
	}
}