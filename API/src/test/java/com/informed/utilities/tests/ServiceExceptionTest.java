package com.informed.utilities.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.informed.utilities.ServiceException;

public class ServiceExceptionTest {

	@Test
	public void canInstantiateServiceExceptionUsingDefaultConstructor() {
		ServiceException ex = new ServiceException();
		assertNotNull(ex);
		assertEquals(ex.getMessage(), "An error occurred whilst processing the request");
	}
	
	@Test
	public void canInstantiateServiceExceptionByException() {
		String exceptionMessage = "test message";	
		Exception testException = new Exception(exceptionMessage);
		
		ServiceException ex = new ServiceException(testException);
		assertNotNull(ex);
	}
	
	@Test
	public void errorListPopulatedWhenInstantiatingServiceExceptionByException() {
		String exceptionMessage = "test message";	
		Exception testException = new Exception(exceptionMessage);
		
		ServiceException ex = new ServiceException(testException);
		assertNotNull(ex);
		assertTrue(ex.getErrors().size() == 1);
	}
	
	@Test
	public void errorListPopulatedWhenInstantiatingServiceExceptionByString() {
		String exceptionMessage = "test message";			
		ServiceException ex = new ServiceException(exceptionMessage);
		assertNotNull(ex);
		assertTrue(ex.getErrors().size() == 1);
	}
	
	@Test
	public void canAddErrorsToDefaultConstructorInitialisedException() {			
		ServiceException ex = new ServiceException();
		ex.addError("An error");
		assertNotNull(ex);
		assertNotNull(ex.getErrors());
		assertTrue(ex.getErrors().size() == 1);
	}
	
	@Test
	public void canAddErrorsToStringInitialisedException() {
		String exceptionMessage = "test message";			
		ServiceException ex = new ServiceException(exceptionMessage);
		ex.addError("Second error");
		assertNotNull(ex);
		assertTrue(ex.getErrors().size() == 2);
	}
	
}
