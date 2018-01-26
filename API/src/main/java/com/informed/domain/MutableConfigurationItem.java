package com.informed.domain;

/**
 * Domain representation of a configuration item.
 * 
 * @author James Cruddas
 *
 */
public class MutableConfigurationItem {

	private String key;
	private Object value;

	/**
	 * Default constructor for creating a configuration item.
	 * 
	 * @param key
	 *            the configuration item name.
	 * @param value
	 *            the value of the configuration item.
	 */
	public MutableConfigurationItem(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Get method for returning a configuration item's key.
	 * 
	 * @return the configuration item's key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Get method for returning a configuration item's value.
	 * 
	 * @return the value of a configuration item.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Set method for setting a configuration item value.
	 * 
	 * @param value
	 *            the value to set.
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
