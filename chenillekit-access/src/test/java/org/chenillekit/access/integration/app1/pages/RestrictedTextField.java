/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/
 *
 * Copyright 2008 by chenillekit.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.chenillekit.access.integration.app1.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Submit;
import org.apache.tapestry5.corelib.components.TextField;
import org.chenillekit.access.annotations.Restricted;

/**
 *
 * @version $Id$
 */
public class RestrictedTextField
{
	@Persist
	@Property
	private String simpleText1;

	@Persist
	@Property
	private String simpleText2;

	@Component
	private Form simpleForm;

	@Component(parameters = {"value=simpleText1"})
	@Restricted(role = 1)
	private TextField simpleTextField1;

	@Component(parameters = {"value=simpleText2"})
	private TextField simpleTextField2;

	@Component
	private Submit simpleSubmit;
}