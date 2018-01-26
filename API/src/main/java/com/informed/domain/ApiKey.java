package com.informed.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Domain representation of an API key used to secure a RESTful web service.
 * 
 * @author James Cruddas
 *
 */
public class ApiKey {

	/**
	 * Textual identifier of the API key.
	 */
	private String id;

	/**
	 * A password-equivalent key value used for authentication purposes.
	 */
	private String key;

	/**
	 * The date an API key was created.
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Europe/London")
	private Date created;

	/**
	 * A collection of authorities that an API key possesses.
	 */
	private List<String> authorities;

	/**
	 * Default constructor
	 * 
	 * @param id
	 *            a textual identifier used for retrieving an API key.
	 */
	public ApiKey(String id) {
		this.id = id;
		this.key = UUID.randomUUID().toString().replaceAll("-", "");
		this.created = new Date();
		this.authorities = new ArrayList<String>();
		this.authorities.add("ROLE_API_CLIENT");
	}

	/**
	 * Returns authorities granted to the API key.
	 * 
	 * @return authorities granted to the API key
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> result = new HashSet<GrantedAuthority>();

		for (String authority : this.authorities) {
			result.add(new SimpleGrantedAuthority(authority));
		}

		return result;
	}

	/**
	 * Returns whether a key has a given authority.
	 * 
	 * @param authority
	 *            the authority to check for
	 * @return boolean response for whether key has been granted the supplied
	 *         authority
	 */
	public boolean hasAuthority(String authority) {
		for (String keyAuthority : this.authorities) {
			if (keyAuthority.equals(authority)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Grants an authority to the API key.
	 * 
	 * @param authority
	 *            the authority which is to be granted to the API key
	 */
	public void grantAuthority(String authority) {
		this.authorities.add(authority);
	}

	/**
	 * Removes an authority of the API key.
	 * 
	 * @param authority
	 *            the authority which is to be removed from the API key
	 */
	public void removeAuthority(String authority) {
		this.authorities.remove(authority);
	}

	/**
	 * Auto-stamped date an API key was created.
	 * 
	 * @return the date the API key was created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Getter for retrieving an API key identifier.
	 * 
	 * @return the id for the API key
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for retrieving an API key.
	 * 
	 * @return the key allocated to an API key
	 */
	public String getKey() {
		return key;
	}
}
