package ismt.application.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import ismt.application.engine.Mailbox;
import ismt.application.engine.MailboxDB;
import ismt.application.engine.User;
import ismt.application.engine.UserDB;
import ismt.application.engine.Utils;
import ismt.application.scene.SceneBase;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
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

public class PopularUsersScene extends SceneBase {

	@Override
	public Scene buildScene(Stage primaryStage, Scene sceneMain) {

		// Build pane
		StackPane root_stack = new StackPane();

		// Build labels
		final Label labelPopularMessages = new Label("Utilizador mais comum por mensagens");
		final Label labelPopularConnections = new Label("Utilizador mais comum por conexões");
		labelPopularConnections.setFont(new Font("Arial", 20));
		labelPopularMessages.setFont(new Font("Arial", 20));

		// init user db to store users
		UserDB db = new UserDB();
		MailboxDB db2 = new MailboxDB();

		LinkedList<User> userWithMustConnections = null;
		LinkedList<Mailbox> userWithMustMessages = null;
		// Get data from JSON file
		try {
			db = Utils.getAllUsers();
			db2 = Utils.getAllMailboxes();

			// get list with must connections
			userWithMustConnections = getUserWithMustConnections(db, 3);

			// get list with must messages
			userWithMustMessages = getUserWithMustMessages(db2, 3);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * Build specific task scene
		 * 
		 * 
		 * 
		 */

		TableView<User> tableMustConnections = CreateMustConnectiosTable(userWithMustConnections);
		TableView<Mailbox> tableMustMessages = CreateMustMessagesTable(userWithMustMessages);

		// Create buttons and specify the actions that will be performed when the
		// buttons are pressed.
		Button button_to_back = new Button("Back");

		// Assign action to button click
		button_to_back.setOnAction((ActionEvent event) -> {
			primaryStage.setScene(sceneMain);
		});

		// Build vertical container
		//A vBox é um elemento de layout onde dá para inserir caracteristicas e outros elementos de layout "é como uma div do HTML"
		final VBox vbox_connections = new VBox();
		vbox_connections.setSpacing(5);
		vbox_connections.setPadding(new Insets(10, 0, 0, 10));
		vbox_connections.getChildren().addAll(labelPopularConnections, tableMustConnections);

		final VBox vbox_messages = new VBox();
		vbox_messages.setSpacing(5);
		vbox_messages.setPadding(new Insets(200, 0, 0, 10));
		vbox_messages.getChildren().addAll(labelPopularMessages, tableMustMessages);

		// Build horizontal container
		HBox pane_for_buttons = new HBox(16); // space between buttons is 16
		pane_for_buttons.getChildren().addAll(button_to_back);
		pane_for_buttons.setAlignment(Pos.CENTER); // The Box is centered
		// Specify empty space around the HBox
		pane_for_buttons.setPadding(new Insets(0, 0, 20, 0));

		BorderPane border_panel_connections = new BorderPane();
		border_panel_connections.setBottom(pane_for_buttons);

		// Add all controls on root pane
		root_stack.getChildren().addAll(vbox_messages, vbox_connections, border_panel_connections);

		// Create new scene with default size and filled pane
		Scene tempScene = new Scene(root_stack, APP_WIDTH, APP_HEIGHT);

		// Eliminate background specifications of the StackPane to make the fill of the
		// Scene visible
		root_stack.setBackground(null);
		tempScene.getStylesheets().add(resourceFolder + "/play.css");

		return tempScene;
	}

	private LinkedList<User> getUserWithMustConnections(UserDB userDB, int numUsers) {

		// bubble sort algorithm
		// for some explanation, please see:
		// https://www.mkyong.com/java/java-bubble-sort-example/ 
		// Tenho 2 ciclos for em que o primeiro vai até ao final do tamanho da lista ou seja
		//o "UsersMustConnections", o segundo vai começar na possição 1 e vai até ao final da lista, menos do valor do i
		//	
		ArrayList<User> usersMustConnections = userDB.getUsers();
		User temp;
		for (int i = 0; i < usersMustConnections.size(); i++) {
			for (int j = 1; j < (usersMustConnections.size() - i); j++) {

				if (usersMustConnections.get(j - 1).getConnectionNumber() < usersMustConnections.get(j)
						.getConnectionNumber()) {
					temp = usersMustConnections.get(j - 1);
					usersMustConnections.set(j - 1, usersMustConnections.get(j));
					usersMustConnections.set(j, temp);
				}
			}
			// if the number of users is reached we return the subList
			//LinkedList utilizei porque é a única que garante a ordem dos registos em memória
			if ((numUsers - 1) == i) {
				return new LinkedList<User>(usersMustConnections.subList(0, numUsers));
			}
		}
		return new LinkedList<User>(usersMustConnections);
	}

	private LinkedList<Mailbox> getUserWithMustMessages(MailboxDB mailboxDB, int numUsers) {
		// bubble sort algorithm
		// for some explanation, please see:
		// https://www.mkyong.com/java/java-bubble-sort-example/
		//
		ArrayList<Mailbox> usersMustMessages = mailboxDB.getMailboxes();
		Mailbox temp;
		for (int i = 0; i < usersMustMessages.size(); i++) {
			for (int j = 1; j < (usersMustMessages.size() - i); j++) {

				if (usersMustMessages.get(j - 1).getMessageNumber() < usersMustMessages.get(j).getMessageNumber()) {
					temp = usersMustMessages.get(j - 1);
					usersMustMessages.set(j - 1, usersMustMessages.get(j));
					usersMustMessages.set(j, temp);
				}

			}
			// if the number of users is reached we return the subList
			if ((numUsers - 1) == i) {
				return new LinkedList<Mailbox>(usersMustMessages.subList(0, numUsers));
			}
		}
		return new LinkedList<Mailbox>(usersMustMessages);
	}

	private TableView<User> CreateMustConnectiosTable(LinkedList<User> users) {
		// Build table
		TableView<User> table = new TableView<User>();
		table.setEditable(true);
		TableColumn<User, String> userIDCol = new TableColumn<>("UserID");
		TableColumn<User, String> nameCol = new TableColumn<>("Nome");
		TableColumn<User, Integer> connectionsCol = new TableColumn<>("Conexões");

		table.getColumns().addAll(userIDCol, nameCol, connectionsCol);

		// Obtain users' data
		// É uma lista que fica com um observable que fica á "escuta" de alterações que acontecem na lista
		final ObservableList<User> data = FXCollections.observableArrayList(users);

		// Bind data to User class properties
		userIDCol.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));
		nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		connectionsCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("connectionNumber"));

		// Bind data to table
		//É para as tabelas redimencionarem consoante o número de linhas que a a tabela tem (Utilizadors)
		table.setItems(data);

		table.setFixedCellSize(32);
		table.prefHeightProperty()
				.bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
		table.minHeightProperty().bind(table.prefHeightProperty());
		table.maxHeightProperty().bind(table.prefHeightProperty());

		return table;
	}

	private TableView<Mailbox> CreateMustMessagesTable(LinkedList<Mailbox> users) {
		// Build table
		TableView<Mailbox> table = new TableView<Mailbox>();
		table.setEditable(true);
		TableColumn<Mailbox, String> userIDCol = new TableColumn<>("UserID");
		TableColumn<Mailbox, String> nameCol = new TableColumn<>("Nome");
		TableColumn<Mailbox, Integer> messagesCol = new TableColumn<>("Mensagens");

		table.getColumns().addAll(userIDCol, nameCol, messagesCol);

		// Obtain users' data
		final ObservableList<Mailbox> data = FXCollections.observableArrayList(users);

		// Bind data to User class properties
		userIDCol.setCellValueFactory(new PropertyValueFactory<Mailbox, String>("userID"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Mailbox, String>("userName"));
		messagesCol.setCellValueFactory(new PropertyValueFactory<Mailbox, Integer>("messageNumber"));

		// Bind data to table
		table.setItems(data);

		table.setFixedCellSize(32);
		table.prefHeightProperty()
				.bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
		table.minHeightProperty().bind(table.prefHeightProperty());
		table.maxHeightProperty().bind(table.prefHeightProperty());

		return table;
	}

}
