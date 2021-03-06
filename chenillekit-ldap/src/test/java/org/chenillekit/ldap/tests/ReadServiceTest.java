/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/
 *
 * Copyright 2008-2010 by chenillekit.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.chenillekit.ldap.tests;

import netscape.ldap.LDAPEntry;
import org.chenillekit.ldap.ChenilleKitLDAPTestModule;
import org.chenillekit.ldap.services.internal.ReadService;
import org.chenillekit.test.AbstractTestSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.naming.NamingException;
import java.util.Enumeration;
import java.util.List;

/**
 * @version $Id$
 */
public class ReadServiceTest extends AbstractTestSuite
{
    private ReadService searcherService;

    @BeforeTest
    public final void setup_registry()
    {
        super.setup_registry(ChenilleKitLDAPTestModule.class);
        searcherService = registry.getService(ReadService.class);
    }

    @Test(threadPoolSize = 4, invocationCount = 50, successPercentage = 98)
    public void simple_search() throws NamingException
    {
        String baseDN = "o=Bund,c=DE";
        String filter = "(cn=Bund*)";
        String attribute = "mail";
        List<LDAPEntry> matches = searcherService.search(baseDN, filter, attribute);
        for (LDAPEntry match : matches)
        {
            Enumeration values = match.getAttribute(attribute).getStringValues();
            while (values.hasMoreElements())
            {
                String value = (String) values.nextElement();
            }
        }

        assertTrue(matches.size() >= 1);
    }

    @Test(threadPoolSize = 4, invocationCount = 50, successPercentage = 98)
    public void lookup() throws NamingException
    {
    	String baseDN = "o=Bund,c=DE";
        String filter = "(cn=Bund*)";

        List<LDAPEntry> matches = searcherService.search(baseDN, filter);

        assertTrue(matches.size() >= 1);

        LDAPEntry match = matches.get(0);

        String matchDN = match.getDN();
        LDAPEntry entry = searcherService.lookup(matchDN);

        assertNotNull(entry);
    }
}
