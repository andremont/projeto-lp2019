package ismt.application.scene;

import java.io.IOException;

import ismt.application.engine.User;
import ismt.application.engine.Utils;
import ismt.application.scene.SceneBase;
import ismt.application.engine.Connection;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	@Override
	public Scene buildScene(Stage primaryStage, Scene sceneMain) {
		
		// Build pane
		StackPane root_stack = new StackPane();
		
		// Build labels
		final Text initial_instructions = new Text("Exemplo");
		final Label label = new Label("Users list");
        	label.setFont(new Font("Arial", 20));

		/** Build specific task scene 
		 * 
		 * 
		 * 
		 */
        
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
		root_stack.getChildren().addAll(border_pane, initial_instructions);
		
		// Create new scene with default size and filled pane
		Scene tempScene = new Scene(root_stack, APP_WIDTH, APP_HEIGHT);
		
		// Eliminate  background specifications of the StackPane to make the fill of the Scene visible
		root_stack.setBackground(null);
		tempScene.getStylesheets().add(resourceFolder + "/play.css");

		return tempScene;
	}

	private Graph CreateGraph() {
		
		ArrayList<Connection> connections = new ArrayList<Connection>();
		

		Graph g = new Graph(connections.size());

		for (Connection conn : connections) {
			g.addEdge(conn.getUserID1(), conn.getUserID2());
		}

		return g;
	}

	private int MaxDistanceAnyUser() {
		Graph g = CreateGraph();

		return g.longestPathLength();
	}

	private int MaxDistanceBetween2Users(User u1, User u2) {
		Graph g = CreateGraph();

		return g.longestPathBetweenUsers(u1, u2);
	}


	public static class Graph { 
        int V; // No. of vertices  
        LinkedList<Integer>[] adj; //Adjacency List  
          
        // Constructor  
        public Graph(int V) { 
            this.V = V; 
            // Initializing Adjacency List 
            adj = new LinkedList[V]; 
            for(int i = 0; i < V; ++i) { 
                adj[i] = new LinkedList<Integer>(); 
            } 
        } 
          
        // function to add an edge to graph  
        public void addEdge(int s, int d) { 
            adj[s].add(d); // Add d to s's list.  
            adj[d].add(s); // Since the graph is undirected  
        } 
          
          
        // method returns farthest node and its distance from node u  
        public Pair<Integer, Integer> bfs(int u) { 
            int[] dis = new int[V]; 
              
            // mark all distance with -1  
            Arrays.fill(dis, -1); 
  
            Queue<Integer> q = new LinkedList<>(); 
  
            q.add(u); 
              
            // distance of u from u will be 0  
            dis[u] = 0; 
            while (!q.isEmpty()) { 
                int t = q.poll(); 
                  
                // loop for all adjacent nodes of node-t  
                for(int i = 0; i < adj[t].size(); ++i) { 
                    int v = adj[t].get(i); 
                      
                    // push node into queue only if  
                    // it is not visited already  
                    if(dis[v] == -1) { 
                        q.add(v); 
                        // make distance of v, one more  
                        // than distance of t  
                        dis[v] = dis[t] + 1; 
                    } 
                } 
            } 
  
            int maxDis = 0; 
            int nodeIdx = 0; 
              
            // get farthest node distance and its index  
            for(int i = 0; i < V; ++i) { 
                if(dis[i] > maxDis) { 
                    maxDis = dis[i]; 
                    nodeIdx = i; 
                } 
            } 
  
            return new Pair<Integer, Integer>(nodeIdx, maxDis); 
		} 
		
		// method returns farthest node and its distance from node u  
        public Pair<Integer, Integer> bfsToUser(int u1, int u2) { 
            int[] dis = new int[V]; 
              
            // mark all distance with -1  
            Arrays.fill(dis, -1); 
  
            Queue<Integer> q = new LinkedList<>(); 
  
            q.add(u1); 
              
            // distance of u1 from u1 will be 0  
            dis[u1] = 0; 
            while (!q.isEmpty()) { 
                int t = q.poll(); 
                  
                // loop for all adjacent nodes of node-t  
                for(int i = 0; i < adj[t].size(); ++i) { 
                    int v = adj[t].get(i); 
                      
                    // push node into queue only if  
                    // it is not visited already  
                    if(dis[v] == -1) { 
                        q.add(v); 
                        // make distance of v, one more  
                        // than distance of t  
                        dis[v] = dis[t] + 1; 
                    } 
                } 
            } 
  
            int maxDis = 0; 
            int nodeIdx = 0; 
              
            // get farthest node distance and its index  
            for(int i = 0; i < V; ++i) { 
                if(dis[i] > maxDis && i == u2) { 
                    maxDis = dis[i]; 
                    nodeIdx = i; 
                } 
            } 
  
            return new Pair<Integer, Integer>(nodeIdx, maxDis); 
        } 
          
        // method prints longest path of given tree  
        public int longestPathLength() { 
            Pair<Integer, Integer> t1, t2; 
              
            // first bfs to find one end point of  
            // longest path  
            t1 = bfs(0); 
              
            // second bfs to find actual longest path  
            t2 = bfs(t1.getValue()); 
  
            return t1.getValue(); 
		} 
		
		public int longestPathBetweenUsers(User u1, User u2) {
			return bfsToUser(u1.getUserID(), u2.getUserID()).getValue();
		}
    } 
}
