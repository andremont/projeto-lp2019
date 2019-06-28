package ismt.application.scene;

import java.io.IOException;

import ismt.application.engine.User;
import ismt.application.engine.UserDB;
import ismt.application.engine.Utils;
import ismt.application.scene.SceneBase;
import ismt.application.engine.Connection;
import ismt.application.engine.Mailbox;
import ismt.application.engine.MailboxDB;
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
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class MaxDistanceScene extends SceneBase{

	private static Graph sceneGraph = null;

	@Override
	public Scene buildScene(Stage primaryStage, Scene sceneMain) {
		
		// Build pane
		StackPane root_stack = new StackPane();
		
		/** Build specific task scene 
		 * 
		 * 
		 * 
		 */
        	
    	// init user db to store users
		UserDB userDB = new UserDB();
		
		  // Get data from JSON file
        try {
        	userDB = Utils.getAllUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ArrayList<User> allUsers = userDB.getUsers();
        // Obtain users' data
        final ObservableList<User> data = FXCollections.observableArrayList(userDB.getUsers());
        
		
		// Init Graph
		sceneGraph = InitGraph(allUsers);
		
		// Init Layout
		TableView<User> table1 = CreateTable(data, "name");
		TableView<User> table2 = CreateTable(data, "name");
    		
		final Label resultsLabel = new Label();
		resultsLabel.setFont(new Font("Arial", 50));
		

		final VBox vbox_left_table = new VBox();
		vbox_left_table.setSpacing(5);
		vbox_left_table.setPadding(new Insets(10, 0, 0, 10));
		vbox_left_table.getChildren().addAll(table1);


		final VBox vboxright_table = new VBox();
		vboxright_table.setSpacing(5);
		vboxright_table.setPadding(new Insets(200, 0, 0, 10));
		vboxright_table.getChildren().addAll(table2, resultsLabel);
		

		Button calc_result_button = new Button("Calculate");

		// Assign action to button click
		calc_result_button.setOnAction((ActionEvent event) -> {
			User user1 = table1.getSelectionModel().getSelectedItem();
			User user2 = table2.getSelectionModel().getSelectedItem();

			if (user1 != null && user2 != null) {
				String result = ("A distancia entre os utilizador " + user1.getName() + " e " + user2.getName() + " é de " + Integer.toString(CalculateMaxDistance(user1, user2)));
				resultsLabel.setText(result);
			}
		});
		
		
		root_stack.getChildren().addAll(vbox_left_table, vboxright_table);


		Button button_to_back = new Button("Back");


		button_to_back.setOnAction((ActionEvent event) -> {
				primaryStage.setScene(sceneMain);
		});
		

		HBox pane_for_buttons = new HBox(16); 
		pane_for_buttons.getChildren().addAll(calc_result_button, button_to_back);
		pane_for_buttons.setAlignment(Pos.CENTER);

		pane_for_buttons.setPadding(new Insets(0, 0, 20, 0));

		BorderPane border_pane = new BorderPane();
		border_pane.setBottom(pane_for_buttons);
		

		root_stack.getChildren().addAll(border_pane);
		

		Scene tempScene = new Scene(root_stack, APP_WIDTH, APP_HEIGHT);
		

		root_stack.setBackground(null);
		tempScene.getStylesheets().add(resourceFolder + "/play.css");

		return tempScene;
	}
	
	private int CalculateMaxDistance(User user1, User user2) {
		
	//	System.out.println("User1:" + user1.getName());
	//	System.out.println("User2:" + user2.getName());
		

		if (sceneGraph != null) {
			return sceneGraph.longestPathBetweenUsers(user1, user2);
		}
		
		return 0;
	}

	private Graph InitGraph(ArrayList<User> allUsers) {
		
		Graph g = new Graph(allUsers.size());

		for (User user : allUsers) {
			ArrayList<Connection> connections = user.getConnections();
			
			if (connections != null) {
				for (Connection conn : connections) {
					g.addEdge(conn.getUserID1(), conn.getUserID2());
				}
			}			
		}

		return g;
	}

	
	private TableView<User> CreateTable(ObservableList<User> data, String columnName) {
	
		TableView<User> table = new TableView<User>();
		table.setEditable(true);
		TableColumn<User, String> nameCol = new TableColumn<>(columnName);

		table.getColumns().add(nameCol);

		
		nameCol.setCellValueFactory(new PropertyValueFactory<User, String>(columnName));

	
		table.setItems(data);

		table.setFixedCellSize(32);
		table.prefHeightProperty()
				.bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems())));

		return table;
	}


	public static class Graph { 
        int V; // numero de vertices 
        LinkedList<Integer>[] adj;
          
        // Constructor  
        public Graph(int V) { 
            this.V = V; 
             
            adj = new LinkedList[V]; 
            for(int i = 0; i < V; ++i) { 
                adj[i] = new LinkedList<Integer>(); 
            } 
        } 
          
        // adicona nodos  
        public void addEdge(int s, int d) { 
            adj[s].add(d); 
            adj[d].add(s);
        } 
          
          

        public Pair<Integer, Integer> bfs(int firstNode) { 
            int[] distance = new int[V]; 
              
            // todas as distancias ficam a um
            Arrays.fill(distance, -1); 
  
            Queue<Integer> queue = new LinkedList<>(); 
  
            queue.add(firstNode); 
              
            // distancia de u a u é 0
            distance[firstNode] = 0; 
            while (!queue.isEmpty()) { 
                int topNode = queue.poll(); 
                  
                // loop por todos os nodos adjacentes
                for(int i = 0; i < adj[topNode].size(); ++i) { 
                    int vertice = adj[topNode].get(i); 
                      
                  
                    if(distance[vertice] == -1) { 
                        queue.add(vertice); 
                        distance[vertice] = distance[topNode] + 1; 
                    } 
                } 
            } 
            
  
            int maxDis = 0; 
            int nodeIdx = 0; 
              
            // nodo mais distante e index
            for(int i = 0; i < V; ++i) { 
                if(distance[i] > maxDis) { 
                    maxDis = distance[i]; 
                    nodeIdx = i; 
                } 
            } 
  
            return new Pair<Integer, Integer>(nodeIdx, maxDis); 
		}
        		

        public Pair<Integer, Integer> bfsToUser(int u1, int u2) { 
            int[] dis = new int[V]; 
              

            Arrays.fill(dis, -1); 
  
            Queue<Integer> q = new LinkedList<>(); 
  
            q.add(u1); 
              
 
            dis[u1] = 0; 
            while (!q.isEmpty()) { 
                int t = q.poll();

                for(int i = 0; i < adj[t].size(); ++i) { 
                    int v = adj[t].get(i); 

                    if(dis[v] == -1) { 
                        q.add(v); 

                        dis[v] = dis[t] + 1; 
                    } 
                } 
            } 
  
            int maxDis = 0; 
            int nodeIdx = 0; 


            for(int i = 0; i < V; ++i) { 
                if(dis[i] > maxDis && i == u2) { 
                    maxDis = dis[i]; 
                    nodeIdx = i; 
                } 
            } 
  
            return new Pair<Integer, Integer>(nodeIdx, maxDis); 
        } 
          
        // o maior caminho
        public int longestPathLength() { 
            Pair<Integer, Integer> t1, t2; 
              
            // um dos finais do maior caminho
            t1 = bfs(0); 
              
            // segundo bfs para encontrar o maior caminho real 
            t2 = bfs(t1.getValue()); 
  
            return t1.getValue(); 
		} 
		
		public int longestPathBetweenUsers(User u1, User u2) {
			return bfsToUser(u1.getUserID(), u2.getUserID()).getValue();
		}
    } 
}
