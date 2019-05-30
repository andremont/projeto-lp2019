package ismt.application.engine;

import java.util.ArrayList;

public class User {
		

	private int userID;
	private String username;
	private String password;
	private String name;
	private String email;
	private String title;
	private String description;
	private ArrayList<Connection> connections;
	private int connectionNumber;
	private Mailbox mailbox;
	
	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param userID
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param title
	 * @param description
	 * @param connections
	 */
	public User(int userID, String username, String password, String name, String email, String title, String description,
			ArrayList<Connection> connections, Mailbox mailbox) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.title = title;
		this.description = description;
		this.connections = connections;
		this.connectionNumber = connections.size();
		this.setMailbox(mailbox);
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the name
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the connections
	 */
	
	public ArrayList<Connection> getConnections() {
		return connections;
	}
	
	/**
	 * @param connections the connections to set
	 */
	public void setConnections(ArrayList<Connection> connections) {
		this.connections = connections;
		this.connectionNumber = connections.size();
	}
	
	/**
	 * @param Add connection
	 */
	public void addConnection(Connection connection) {
		this.connections.add(connection);
	}
	
	public int getConnectionNumber() {
		return connectionNumber;
	}

	public void setConnectionNumber(int connectionNumber) {
		this.connectionNumber = connectionNumber;
	}

	/**
	 * @param Count connections
	 */
	public int countConnections() {
		return this.connectionNumber;
	}

	/**
	 * @return the mailbox
	 */
	public Mailbox getMailbox() {
		return mailbox;
	}

	/**
	 * @param mailbox the mailbox to set
	 */
	public void setMailbox(Mailbox mailbox) {
		this.mailbox = mailbox;
	}
}