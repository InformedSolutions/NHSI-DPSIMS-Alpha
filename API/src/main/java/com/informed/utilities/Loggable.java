package com.informed.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class that provides logging functionality to derived classes.
 * 
 * @author James Cruddas
 *
 */
public abstract class Loggable {

	/**
	 * A common logging implementation that can be accessed by derived by other
	 * classes.
	 * 
	 */
	protected static final Logger logger = LoggerFactory.getLogger(Loggable.class);

	/**
	 * Generic method for logging trace records.
	 * 
	 * @param message
	 *            Message to be logged
	 */
	protected void logTrace(String message) {
		logger.trace(message);
	}

	/**
	 * Generic method for logging debug records.
	 * 
	 * @param message
	 *            Message to be logged
	 */
	protected void logDebug(String message) {
		logger.debug(message);
	}

	/**
	 * Generic method for logging information records.
	 * 
	 * @param message
	 *            Message to be logged
	 */
	protected void logInfo(String message) {
		logger.info(message);
	}

	/**
	 * Generic method for logging trace records.
	 * 
	 * @param message
	 *            Message to be logged
	 */
	protected void logWarn(String message) {
		logger.warn(message);
	}

	/**
	 * Generic method for logging errors.
	 * 
	 * @param e
	 *            Exception to be logged
	 */
	protected void logError(Exception e) {
		logger.error(e.getMessage());
	}

	/**
	 * Generic method for logging errors.
	 * 
	 * @param message
	 *            Message to be logged
	 */
	protected void logError(String message) {
		logger.error(message);
	}

	/**
	 * Method for retrieving Log provider.
	 * 
	 * @return Log provider (log4j2)
	 */
	public Logger getLogger() {
		return logger;
	}
}
