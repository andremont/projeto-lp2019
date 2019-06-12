package ismt.application.scene;

import java.io.IOException;
import java.util.function.Predicate;

import javax.swing.event.ChangeListener;

import ismt.application.engine.Connection;
import ismt.application.engine.User;
import ismt.application.engine.UserDB;
import ismt.application.engine.Utils;
import ismt.application.scene.SceneBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class DistanceScene extends SceneBase{

	@Override
	public Scene buildScene(Stage primaryStage, Scene sceneMain) {
		
		// Build pane
		StackPane root_stack = new StackPane();
		
		// Build labels
	
		
		final Label label = new Label("Users list");
        label.setFont(new Font("Arial", 20));
        
        //Text Field
        TextField searchField = new TextField();
        searchField.setPrefWidth(100);
        searchField.maxWidth(100);
   
        
        
      
        
        /** Build specific task scene 
         * 
         * 
         * 
         */
        
        
        
		// Create buttons and specify the actions that will be performed when the buttons are pressed.
		Button button_to_back = new Button("Back");
		
		
		// Assign action to button click
		//Back
		button_to_back.setOnAction((ActionEvent event) -> {
				primaryStage.setScene(sceneMain);
		});
		

		
		

		// Build horizontal container
		//HBox pane_for_buttons = new HBox(16); // space between buttons is 16
		//pane_for_buttons.getChildren().add(button_to_back);
		//pane_for_buttons.setAlignment(Pos.CENTER); // The Box is centered
		
	
		

		
		
		
		
		
		//Tableview, Criação da tabela 
		
		TableView<User> tableD = new TableView<User>();
		tableD.setEditable(true);
        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        tableD.getColumns().add(nameCol);
       
		
    				
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
    		    	tableD.setItems(data);	
    		
    	
		//Vertical container para o text field , label e botao 
		Label labelD = new Label("Enter distance:");
		VBox pane_for_search = new VBox(20);
		pane_for_search.getChildren().addAll(labelD,searchField,button_to_back,tableD);
		pane_for_search.setPrefWidth(100);
		
		
		// Add all controls on root pane
		root_stack.getChildren().addAll(pane_for_search);
		
		
		
		FilteredList<User> filteredData = new FilteredList<>(data, e -> true);
        
		searchField.setOnKeyPressed(e ->{
            searchField.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredData.setPredicate((Predicate<? super User>) user->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    
                    if(user.getConnectionNumber()== Integer.parseInt(newValue)){
                        return true;
                   
                    }
                    return false;
                });
            });
         
            SortedList<User> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableD.comparatorProperty());
            tableD.setItems(sortedData);
           
        });
		
		
		// Create new scene with default size and filled pane
		Scene DistanceScene = new Scene(root_stack, APP_WIDTH, APP_HEIGHT);
		
		// Eliminate  background specifications of the StackPane to make the fill of the Scene visible
		root_stack.setBackground(null);
		DistanceScene.getStylesheets().add(resourceFolder + "/play.css");

		return DistanceScene;
	}

}