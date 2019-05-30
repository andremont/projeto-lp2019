package ismt.application.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ismt.application.engine.User;


public class UserTest {

	User userTemp;

	@BeforeEach
	void setUp() throws Exception {
		userTemp = new User();
		userTemp.setUserID(1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetUserID() {
		int expected = 1;
		int actual = userTemp.getUserID();
		assertEquals(expected, actual);
	}
	
	@Test
	void testSetUserID() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetUsername() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetUsername() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetPassword() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetPassword() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetEmail() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetEmail() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetName() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetName() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetTitle() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetTitle() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetDescription() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetDescription() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetConnections() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetConnections() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testAddConnection() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetConnectionNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetConnectionNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testCountConnections() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetMailbox() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetMailbox() {
		fail("Not yet implemented"); // TODO
	}

}
