package ismt.application.scene;

import java.io.IOException;

import ismt.application.engine.User;
import ismt.application.engine.UserDB;
import ismt.application.engine.Utils;
import ismt.application.scene.SceneBase;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProfileScene extends SceneBase{

	@Override
	public Scene buildScene(Stage primaryStage, Scene sceneMain) {
		
		// Build pane
		StackPane root_stack = new StackPane();
	
		
		// Build labels
		final Text initial_instructions = new Text("");
		final Label label = new Label("Visualização de Perfis");
		label.setStyle("-fx-text-fill: #06008f; -fx-font-size: 14px;  -fx-font-family: 'Open Sans Light'");
    
        
        // Build table
        TableView<User> table = new TableView<User>();
		table.setEditable(true);
		// table.setFixedCellSize(25);
	   //  table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(30));
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	     for(TableColumn column: table.getColumns())
	     {           
	         column.setMinWidth(10); 
	         column.setMaxWidth(100);
	        
	     }
	
		 TableView<User> tableProfile = new TableView<User>();
		 tableProfile.setEditable(true);
		 tableProfile.setFixedCellSize(25);
	    tableProfile.prefHeightProperty().bind(Bindings.size(tableProfile.getItems()).multiply(tableProfile.getFixedCellSize()).add(53));

	    tableProfile.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	     for(TableColumn column: table.getColumns())
	     {           
	         column.setMinWidth(10); 
	         column.setMaxWidth(100);
	     }
	    TableColumn<User, String> emailCol = new TableColumn<>("email");
        TableColumn<User, String> connectionsCol = new TableColumn<>("connections");
	     
        tableProfile.getColumns().addAll(emailCol, connectionsCol);
		
		table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	         
			   @Override
			   public void handle(MouseEvent arg0) {
			    String index = table.getSelectionModel().getSelectedItem().getName();
			             User person = Utils.GetUser(index);
			            
			          
			            
			            
			             
			             
			          // Obtain users' data
			             ObservableList<User> dataProfiles = FXCollections.observableArrayList(person);
			             
			             
			             // Bind data to User class properties
			          
			           
			             emailCol.setCellValueFactory(new PropertyValueFactory<User,String>("Email"));
			             connectionsCol.setCellValueFactory(new PropertyValueFactory<User,String>("ConnectionNumber"));
			             
			             
			         
			             // Bind data to table
			             	tableProfile.setItems(dataProfiles);
			             	
			             	
			         
			          	 
			    
			   }
			        });
        
		 
		
        TableColumn<User, String> nameCol = new TableColumn<>("Name");
    
        
        table.getColumns().addAll(nameCol);
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
     
        nameCol.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        
    
        // Bind data to table
        	table.setItems(data);
       
        	
        	// Create buttons and specify the actions that will be performed when the buttons are pressed.
    		Button button_to_back = new Button("Back");
    	// Build vertical container
        final VBox vbox = new VBox();
        
        vbox.setSpacing(2);
        vbox.setPadding(new Insets(10, 10, 0, 10));
        vbox.getChildren().addAll(label, table, tableProfile, button_to_back);
       vbox.setAlignment(Pos.CENTER);
        final VBox vboxProfile = new VBox();
        
     
        
		

		// Assign action to button click
		button_to_back.setOnAction((ActionEvent event) -> {
				primaryStage.setScene(sceneMain);
		});

		// Build horizontal container
		HBox pane_for_buttons = new HBox(20); // space between buttons is 16
	
		pane_for_buttons.setAlignment(Pos.CENTER); // The Box is centered
		// Specify empty space around the HBox
		pane_for_buttons.setPadding(new Insets(0, 0, 20, 0));

		BorderPane border_pane = new BorderPane();
		border_pane.setBottom(pane_for_buttons);
		initial_instructions.setFont(new Font(24));
		
		// Add all controls on root pane
		root_stack.getChildren().addAll(border_pane, vbox);
		
		// Create new scene with default size and filled pane
		Scene tempScene = new Scene(root_stack, APP_WIDTH, APP_HEIGHT);
		
		// Eliminate  background specifications of the StackPane to make the fill of the Scene visible
		root_stack.setBackground(null);
		tempScene.getStylesheets().add(resourceFolder + "/play.css");

		return tempScene;
	}

}
