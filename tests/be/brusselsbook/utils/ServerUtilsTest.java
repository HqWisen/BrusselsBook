package be.brusselsbook.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ServerUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValidEmailAddress() {
		assertTrue(ServerUtils.validEmailAddress("hakim@gmail.com"));
		assertFalse(ServerUtils.validEmailAddress("hakimgmail.com"));
		assertFalse(ServerUtils.validEmailAddress("hakim@gmailcom"));
	}


}
