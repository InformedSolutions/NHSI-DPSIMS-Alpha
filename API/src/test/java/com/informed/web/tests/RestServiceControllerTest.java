package com.informed.web.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.informed.web.RestServiceController;

public class RestServiceControllerTest {

	@Test
	public void canExtendRestServiceController() {
		TestRestControllerSubClass testSubClass = new TestRestControllerSubClass();
		assertNotNull(testSubClass);
	}

	private class TestRestControllerSubClass extends RestServiceController{	
	}
}
