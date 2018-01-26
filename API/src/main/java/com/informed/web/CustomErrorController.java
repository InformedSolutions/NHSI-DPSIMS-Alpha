package com.informed.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informed.utilities.RestResponseResource;

/**
 * Custom controller definition for returning JSON objects under error
 * conditions (before dispatchlet server can manage exceptions) rather than the
 * default Spring Whitelabel page.
 * 
 * @author James Cruddas
 *
 */
@RestController
public class CustomErrorController implements ErrorController {

	/**
	 * Static error path used to construct URL.
	 */
	private static final String PATH = "/error";

	/**
	 * Method for returning JSON object instead of Spring Whitelabel error page.
	 * 
	 * @param request
	 *            An HttpRequest inclusive of the originating HTTP status code.
	 * @return a JSON object wrapping the originating error.
	 */
	@RequestMapping(value = PATH)
	public RestResponseResource error(HttpServletRequest request) {
		int statusCode = (int) request.getAttribute("javax.servlet.error.status_code");
		HttpStatus status = HttpStatus.valueOf(statusCode);
		RestResponseResource res = new RestResponseResource(status, status.name());
		return res;
	}

	/**
	 * Method for retrieving the error path.
	 */
	@Override
	public String getErrorPath() {
		return PATH;
	}
}