package ismt.application.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ismt.application.scene.MaxDistanceScene;
import javafx.scene.Scene;
import javafx.stage.Stage;

class MaxDistanceSceneTest {

	MaxDistanceScene sceneTemp;
	
	@BeforeEach
	void setUp() throws Exception {
		sceneTemp = new MaxDistanceScene();
		sceneTemp.setSceneName("MaxDistance");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testBuildScene() {
		MaxDistanceScene expected = sceneTemp;
		Scene actual = sceneTemp.buildScene(new Stage(), new Scene(null));
		assertEquals(expected, actual);
	}

}
