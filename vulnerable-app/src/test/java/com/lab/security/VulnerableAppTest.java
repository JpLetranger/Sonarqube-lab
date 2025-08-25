package com.lab.security;

import junit.framework.TestCase;

public class VulnerableAppTest extends TestCase {
    
    public void testLogin() {
        VulnerableApp app = new VulnerableApp();
        assertTrue(app.login("admin"));
        assertFalse(app.login("user"));
    }
}
