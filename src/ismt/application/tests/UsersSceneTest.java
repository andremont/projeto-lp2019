package ismt.application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ismt.application.scene.UsersScene;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

class UsersSceneTest {

	UsersScene sceneTemp;
	
	@BeforeEach
	void setUp() throws Exception {
		sceneTemp = new UsersScene();
		sceneTemp.setSceneName("Users");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testBuildScene() {
		UsersScene expected = sceneTemp;
		Scene actual = sceneTemp.buildScene(new Stage(), new Scene(null));
		assertEquals(expected, actual);
	}

}
