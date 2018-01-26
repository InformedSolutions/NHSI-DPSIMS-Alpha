package com.informed.utilities.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.informed.utilities.ResourceNotFoundException;

public class ResourceNotFoundExceptionTest {

	@Test
	public void canInstantiateResourceNotFoundException() {
		ResourceNotFoundException ex = new ResourceNotFoundException();
		assertNotNull(ex);
		assertEquals(ex.getMessage(), "The requested resource could not be found");
	}
	
	@Test
	public void canGetErrorsFromResourceNotFoundException() {
		ResourceNotFoundException ex = new ResourceNotFoundException();
		assertTrue(ex.getErrors().size() == 1);
	}

}
