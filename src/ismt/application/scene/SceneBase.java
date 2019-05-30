package ismt.application.scene;

import java.io.File;
import ismt.application.engine.MailboxDB;
import ismt.application.engine.UserDB;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class SceneBase {

		protected static final int APP_WIDTH = 910;
		protected static final int APP_HEIGHT = 600;
		static final int BUTTON_SIZE = 100;
		static final int GAP_SIZE = 10;
		protected final String resourceFolder = new File("resource").toURI().toString();
		protected static UserDB userdb;
		protected static MailboxDB mailboxdb;
		public static String sceneName;
		
		public abstract Scene buildScene(Stage primaryStage, Scene sceneMain);

		public String getSceneName() {
			return sceneName;
		}

		public void setSceneName(String sceneName) {
			SceneBase.sceneName = sceneName;
		}
	}

