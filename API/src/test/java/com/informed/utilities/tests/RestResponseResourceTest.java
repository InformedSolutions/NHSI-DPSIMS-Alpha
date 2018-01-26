package com.informed.utilities.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.informed.utilities.RestResponseResource;

public class RestResponseResourceTest {

	@Test
	public void canInstantiateRestResponseEntityResource() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String exceptionMessage = "test message";		
		RestResponseResource responseResource = new RestResponseResource(status, exceptionMessage);

		assertEquals(responseResource.status, status.value());
		assertEquals(responseResource.message, exceptionMessage);
	}
	
	@Test
	public void canSetErrorsOnResponseEntityResource() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String exceptionMessage = "test message";		
		RestResponseResource responseResource = new RestResponseResource(status, exceptionMessage);

		String testString = "Test string";
		List<String> errors = new ArrayList<String>();
		errors.add(testString);
		responseResource.setErrors(errors);
		
		assertTrue(responseResource.errors.size() == 1);
	}

}
