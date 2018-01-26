package com.informed.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.informed.utilities.Loggable;

/**
 * Generic class that provides utility functions for RESTful web service
 * controllers.
 * 
 * @author James Cruddas
 *
 */

/*
 * Set your API endpoint address here so it remains fixed for the lifetime of
 * your service. Versioning of methods should be delegated to controllers which
 * inherit this class.
 */
@RequestMapping(value = "/api")
public abstract class RestServiceController extends Loggable {
}
