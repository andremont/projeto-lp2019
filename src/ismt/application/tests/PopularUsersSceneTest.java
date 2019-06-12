package ismt.application.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ismt.application.engine.Mailbox;
import ismt.application.engine.MailboxDB;
import ismt.application.engine.User;
import ismt.application.engine.UserDB;
import ismt.application.engine.Utils;
import ismt.application.scene.PopularUsersScene;

public class PopularUsersSceneTest {

	PopularUsersScene popularUsersScene;
	UserDB userDB;
	MailboxDB mailboxDB;

	/**
	 * Init the required variables for the tests
	 * 
	 * @throws Exception
	 */
	// @BeforeEach limpar o processo todo de novo, cada vez que se faz um teste vai
	// estanciar todos os objetos necessários para executar os testes
	@BeforeEach
	void setUp() throws Exception {
		try {
			popularUsersScene = new PopularUsersScene();
			popularUsersScene.setSceneName("Users");
			userDB = Utils.getAllUsers();
			mailboxDB = Utils.getAllMailboxes();
		} catch (Exception e) {
		}
	}

	/**
	 * Check if the top list has the same elements as the ones asked on the top
	 */
	// @test identificação do teste, fazer um test case de sucesso em que o número
	// total de utilizadores que está na base de dados vai ser igual ao número de
	// utilizadores retornado da lista
	@Test
	void getUserWithMustConnections_OK_TotalElements_Equal_Top() {
		int expectedUsers = userDB.getUsers().size();
		LinkedList<User> popularUsers = popularUsersScene.getUserWithMustConnections(userDB, expectedUsers);
		assertEquals(expectedUsers, popularUsers.size());
	}

	/**
	 * Check if the top list only returns the required top even if the list has more
	 * elements than the top
	 */
	// @Test é um teste que vai dar "Ok" sendo que o total de elementos não é igual
	// ao top
	// Assert métodos do Junit, ele verifica se for verdade o teste passa e se for
	// falso o teste reprova
	@Test
	void getUserWithMustConnections_OK_TotalElements_NotEqual_Top() {
		int expectedUsers = 2;
		LinkedList<User> popularUsers = popularUsersScene.getUserWithMustConnections(userDB, expectedUsers);
		assertNotEquals(userDB.getUsers().size(), popularUsers.size());
		assertEquals(expectedUsers, popularUsers.size());
	}

	/**
	 * The function is not prepare to have the users with null and it will crash
	 * with NPE
	 */
	// @Test é um teste para causar um crash propositado, coloquei o setUsers a
	// Null, para garantir que o método va crashar para um caso especifico
	@Test
	void getUserWithMustConnections_NOT_OK() {
		userDB.setUsers(null);
		org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> {
			popularUsersScene.getUserWithMustConnections(userDB, 3);
		});
	}

	/**
	 * Check if the top list has the same elements as the ones asked on the top
	 */
	// @Test é um teste que vai dar "Ok" sendo que o total de elementos não é igual
	// ao top
	// Assert métodos do Junit, ele verifica se for verdade o teste passa e se for
	// falso o teste reprova
	@Test
	void getUserWithMustMessages_OK_TotalElements_Equal_Top() {
		int expectedUsers = mailboxDB.getMailboxes().size();
		LinkedList<Mailbox> popularUsers = popularUsersScene.getUserWithMustMessages(mailboxDB, expectedUsers);
		assertEquals(expectedUsers, popularUsers.size());
	}

	/**
	 * Check if the top list only returns the required top even if the list has more
	 * elements than the top
	 */
	// @Test é um teste que vai dar "Ok" sendo que o total de elementos não é igual
	// ao top
	// Assert métodos do Junit, ele verifica se for verdade o teste passa e se for
	// falso o teste reprova
	@Test
	void getUserWithMustMessages_OK_TotalElements_NotEqual_Top() {
		int expectedUsers = 1;
		LinkedList<Mailbox> popularUsers = popularUsersScene.getUserWithMustMessages(mailboxDB, expectedUsers);
		assertNotEquals(mailboxDB.getMailboxes().size(), popularUsers.size());
		assertEquals(expectedUsers, popularUsers.size());
	}

	/**
	 * The function is not prepare to have the mailbox with null and it will crash
	 * with NPE
	 */
	// @Test é um teste para causar um crash propositado, coloquei o setUsers a
	// Null, para garantir que o método va crashar para um caso especifico
	@Test
	void getUserWithMustMessages_NOT_OK() {
		mailboxDB.setMailboxes(null);
		org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> {
			popularUsersScene.getUserWithMustMessages(mailboxDB, 3);
		});
	}
}
