package ismt.application.scene;

import java.io.IOException;
import ismt.application.scene.SceneBase;
import ismt.application.engine.User;
import ismt.application.engine.UserDB;
import ismt.application.engine.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UsersScene extends SceneBase{

	public Scene buildScene(Stage primaryStage, Scene sceneMain) {
		
		// Build pane
		StackPane root_stack = new StackPane();
		
		// Build labels
		final Text initial_instructions = new Text("Exemplo");
		final Label label = new Label("Users list");
        label.setFont(new Font("Arial", 20));
        
        // Build table
        TableView<User> table = new TableView<User>();
		table.setEditable(true);
        TableColumn<User, String> userIDCol = new TableColumn<>("UserID");
        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        TableColumn<User, Integer> connectionsCol = new TableColumn<>("Connections");
        
        table.getColumns().addAll(userIDCol, nameCol, emailCol, connectionsCol);
        UserDB db = new UserDB();
        
        // Get data from JSON file
        try {
        	
			 db = Utils.getAllUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Obtain users' data
        final ObservableList<User> data = FXCollections.observableArrayList(db.getUsers());
        
        // Bind data to User class properties
        userIDCol.setCellValueFactory(new PropertyValueFactory<User,String>("userID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
    	emailCol.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
    	connectionsCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("connectionNumber"));
        // Bind data to table
    	table.setItems(data);
    	
    	// Build vertical container
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
        
		// Create buttons and specify the actions that will be performed when the buttons are pressed.
		Button button_to_back = new Button("Back");

		// Assign action to button click
		button_to_back.setOnAction((ActionEvent event) -> {
				primaryStage.setScene(sceneMain);
		});

		// Build horizontal container
		HBox pane_for_buttons = new HBox(16); // space between buttons is 16
		pane_for_buttons.getChildren().addAll(button_to_back);
		pane_for_buttons.setAlignment(Pos.CENTER); // The Box is centered
		// Specify empty space around the HBox
		pane_for_buttons.setPadding(new Insets(0, 0, 20, 0));

		BorderPane border_pane = new BorderPane();
		border_pane.setBottom(pane_for_buttons);
		initial_instructions.setFont(new Font(24));
		
		// Add all controls on root pane
		root_stack.getChildren().addAll(vbox, border_pane, initial_instructions);
		
		// Create new scene with default size and filled pane
		Scene tempScene = new Scene(root_stack, APP_WIDTH, APP_HEIGHT);
		
		// Eliminate  background specifications of the StackPane to make the fill of the Scene visible
		root_stack.setBackground(null);
		tempScene.getStylesheets().add(resourceFolder + "/play.css");

		return tempScene;
	}	
}
