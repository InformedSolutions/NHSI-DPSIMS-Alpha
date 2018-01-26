package com.informed.utilities.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.slf4j.Log4jLogger;
import org.junit.Test;
import org.slf4j.Logger;

import com.informed.utilities.Loggable;

/**
 * Logging framework (Log4j2) tests.
 * 
 * @author James Cruddas
 *
 */
public class LogProviderTest {

	TestLoggableSubClass loggableObject;

	public LogProviderTest() {
		loggableObject = new TestLoggableSubClass();
	}

	@Test
	public void canLoadLogProviderXmlConfiguration() {
		LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
		Configuration config = logContext.getConfiguration();
		assertNotNull(config);

		ConfigurationSource configurationSource = config.getConfigurationSource();
		String configurationLocation = configurationSource.getLocation();

		assertNotNull(configurationLocation);
		assertTrue(configurationLocation.contains("log4j2.xml"));
	}

	@Test
	public void canInitialiseLogProvider() {
		Logger logger = loggableObject.getLogger();
		Object logProviderClass = logger.getClass();
		assertEquals(logProviderClass, Log4jLogger.class);
	}

	@Test
	public void canWriteToTrace() {
		loggableObject.writeToTrace("Test trace log");
	}

	@Test
	public void canWriteToDebug() {
		loggableObject.writeToDebug("Test debug log");
	}

	@Test
	public void canWriteToInfo() {
		loggableObject.writeToInfo("Test info log");
	}

	@Test
	public void canWriteToWarn() {
		loggableObject.writeToWarn("Test warn log");
	}

	@Test
	public void canWriteStringToError() {
		loggableObject.writeStringToError("Test error log - String");
	}

	@Test
	public void canWriteExceptionToError() {
		Exception e = new Exception("Test error log exception - Exception");
		loggableObject.writeExceptionToError(e);
	}

	/**
	 * Private class to test protected methods of Loggable class.
	 * 
	 * @author James Cruddas
	 *
	 */
	private class TestLoggableSubClass extends Loggable {
		public void writeStringToError(String message) {
			this.logError(message);
		}

		public void writeExceptionToError(Exception e) {
			this.logError(e);
		}

		public void writeToInfo(String message) {
			this.logInfo(message);
		}

		public void writeToDebug(String message) {
			this.logDebug(message);
		}

		public void writeToTrace(String message) {
			this.logTrace(message);
		}

		public void writeToWarn(String message) {
			this.logWarn(message);
		}
	}
}
