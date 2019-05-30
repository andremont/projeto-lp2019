package ismt.application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ismt.application.engine.Mailbox;

class MailboxTest {

	Mailbox mailboxTemp;
	
	@BeforeEach
	void setUp() throws Exception {
		mailboxTemp = new Mailbox();
		mailboxTemp.setUserID(1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetUserID() {
		int expected = 1;
		int actual = mailboxTemp.getUserID();
		assertEquals(expected, actual);;
	}

	@Test
	void testMailbox() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetMessages() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetMessages() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetMessageNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetMessageNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetUserID() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetUserName() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetUserName() {
		fail("Not yet implemented"); // TODO
	}

}
