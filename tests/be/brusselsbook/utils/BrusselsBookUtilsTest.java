package be.brusselsbook.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BrusselsBookUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenerateEmail() {
		String username = "Hakim";
		String email = BrusselsBookUtils.generateEmail(username);
		assertNotNull(email);
		assertEquals("hakim@brusselsbook.be", email);
	}

}
