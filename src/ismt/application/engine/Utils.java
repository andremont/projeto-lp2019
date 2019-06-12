package ismt.application.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

public class Utils {

	private static final String MAILBOX_JSON = "mailboxdb.json";
	private static final String USERS_JSON = "users.json";

	/******************
	 *  User utils 
	 *  
	 ******************/

	/** Validates user credentials  */
	public static boolean validateUser(String username, String password) 
	{ 
		boolean valid = false;
		InputStream fileReader = null;

		try {
			fileReader = new FileInputStream(USERS_JSON);
			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject usersObject = jsonReader.readObject();
			jsonReader.close();

			if(usersObject != null)
				valid = searchUsername(username, password, usersObject);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return valid;
	}

	/** Search user **/
	private static boolean searchUsername(String username, String password, JsonObject usersObject) {

		boolean match = false;

		for (java.util.Map.Entry<String, JsonValue> valueEntry : usersObject.entrySet() ) 
		{
			JsonValue user = valueEntry.getValue();

			if (!user.asJsonObject().containsKey("username") || !user.asJsonObject().containsKey("password"))
				return false;
			else
			{
				String tempUsername = user.asJsonObject().getString("username");

				if(tempUsername.equals(username)) {
					String originalPass = user.asJsonObject().getString("password");

					if (originalPass.equals(password)) 
						return true;
				}
			}
		}
		return match;
	}

	/** Get UserDB from file **/
	private static UserDB buildUserFromFile(String type, JsonObject userObject) {
		UserDB userDB = new UserDB();
		JsonArray userDBArray = userObject.getJsonArray(type);

		// Get each user info
		for(int i=0; i < userDBArray.size(); i++){
			JsonObject user = userDBArray.get(i).asJsonObject();
			User tempUser = new User();

			if (user.containsKey("userid"))
				tempUser.setUserID(Integer.parseInt(user.getString("userid")));
			if (user.containsKey("username"))
				tempUser.setUsername(user.getString("username"));
			if (user.containsKey("password"))
				tempUser.setPassword(user.getString("password"));
			if (user.containsKey("name"))
				tempUser.setName(user.getString("name"));
			if (user.containsKey("title"))
				tempUser.setTitle(user.getString("title"));
			if (user.containsKey("email"))
				tempUser.setEmail(user.getString("email"));
			if (user.containsKey("description"))
				tempUser.setDescription(user.getString("description"));
			if (user.containsKey("connections"))
				tempUser.setConnections(extractConnectionsFromJson(user));
			userDB.insertUser(tempUser);
		}
		return userDB;
	}

	/** Get connection array from user **/
	private static ArrayList<Connection> extractConnectionsFromJson(JsonObject user) {
		ArrayList<Connection> tempConnections = new ArrayList<Connection>();
		JsonArray jsonConnections = user.get("connections").asJsonArray();

		for(int j=0; j < jsonConnections.size(); j++){
			Connection tempConnection = new Connection();
			JsonObject jsonConnection = jsonConnections.get(j).asJsonObject();
			
			if (jsonConnection.containsKey("userid1"))// contains("userid1"))
				tempConnection.setUserID1(jsonConnection.getInt("userid1"));
			if (jsonConnection.containsKey("userid2"))
				tempConnection.setUserID2(jsonConnection.getInt("userid2"));
			if (jsonConnection.containsKey("connectionweight"))
				tempConnection.setConnectionWeigth(jsonConnection.getInt("connectionweight"));
			if (jsonConnection.containsKey("relation"))
				tempConnection.setRelation(getEnumFromString(Relationship.class, jsonConnection.getString("relation")));

			tempConnections.add(tempConnection);
		}
		return tempConnections;
	}

	/** Print all users in array */
	public static void printAllUsers(ArrayList<User> users)
	{
		for(User newUser : users)
			System.out.println(newUser.getUserID() + " | " + newUser.getName() + " | " + newUser.getEmail() + " | \n" );
	}

	/** Converts all users in user to a String */
	public static String getAllUsers(ArrayList<User> users)
	{
		String tempString = "";

		for(User newUser : users)
			tempString += newUser.getUserID() + " | " + newUser.getName() + " | " + newUser.getEmail() + "\n";

		return tempString;
	}

	/** Return User class from file **/
	public static User GetUser(String user)
	{
		InputStream fileReader = null;
		User newUser = new User();

		try {
			fileReader = new FileInputStream(USERS_JSON);

			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject userObject = jsonReader.readObject().get(user).asJsonObject();
			jsonReader.close();
			fileReader.close();

			if(userObject != null)
				if(userObject != JsonArray.NULL)
				{
					newUser.setName(user);

					if (userObject.containsKey("userid"))
						newUser.setUserID(userObject.getInt("userid"));
					if (userObject.containsKey("username"))
						newUser.setUsername(userObject.getString("username"));
					if (userObject.containsKey("password"))
						newUser.setPassword(userObject.getString("password"));
					if (userObject.containsKey("title"))
						newUser.setTitle(userObject.getString("title"));
					if (userObject.containsKey("email"))
						newUser.setEmail(userObject.getString("email"));
					if (userObject.containsKey("description"))
						newUser.setDescription(userObject.getString("description"));
					if (userObject.containsKey("connections"))
						newUser.setConnections(extractConnectionsFromJson(userObject));
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newUser;
	}

	/** Get all users **/
	public static UserDB getAllUsers() throws IOException
	{
		UserDB db = new UserDB();
		ArrayList<User> users = new ArrayList<User>();
		InputStream fileReader = new FileInputStream(USERS_JSON);

		try{
			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject userObject = jsonReader.readObject();
			fileReader.close();
			ArrayList<String> userNames = new ArrayList<String>();
			userNames.addAll(userObject.keySet()); 

			// Get each player info 
			for(int i=0; i < userNames.size(); i++){
				String userName = userNames.get(i);
				users.add(GetUser(userName));
			}
			db.setUsers(users);
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			fileReader.close();
		}
		return db;
	}

	/** Saves input user to file, including connections **/
	public static boolean SaveUser(User user)
	{
		boolean success = false;
		InputStream fileReader = null;

		try {
			fileReader = new FileInputStream(USERS_JSON);

			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject usersObject = jsonReader.readObject();
			jsonReader.close();
			fileReader.close();
			JsonObjectBuilder usersBuilder = Json.createObjectBuilder();

			if(usersObject != null)
				if(usersObject != JsonArray.NULL)
					usersObject.entrySet().forEach(e -> usersBuilder.add(e.getKey(), e.getValue()));

			// Remove if exists
			usersBuilder.remove(user.getName());

			// Add all user's connections
			JsonObjectBuilder userBuilder = Json.createObjectBuilder();
			userBuilder.add("connections", buildConnectionArray(user.getConnections())); 

			// Add all player's properties and attributes
			userBuilder.add("userid", user.getUserID())
			.add("email", user.getEmail() + "")
			.add("username", user.getUsername() + "")
			.add("title", user.getTitle() + "")
			.add("password", user.getPassword() + "")
			.add("description", user.getDescription() + "");

			usersBuilder.add(user.getName(), userBuilder);

			// Write to file
			OutputStream os = new FileOutputStream(USERS_JSON);
			Map<String, Boolean> config = new HashMap<>();
			config.put(JsonGenerator.PRETTY_PRINTING, true);
			JsonWriterFactory jwf = Json.createWriterFactory(config);
			JsonWriter jsonWriter = jwf.createWriter(os);
			jsonWriter.writeObject(usersBuilder.build());
			jsonWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			success = true;
		}
		return success;
	}

	/** Builds and returns an array built in Json */
	private static JsonArrayBuilder buildConnectionArray(ArrayList<Connection> connections) {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		for(Connection connection : connections){
			JsonObjectBuilder jpb = Json.createObjectBuilder().
					add("userid1", connection.getUserID1()).
					add("userid2", connection.getUserID2()).
					add("connectionweight", connection.getConnectionWeigth()).
					add("relation", connection.getRelation().toString());
			arrayBuilder.add(jpb);
		}
		return arrayBuilder;
	}

	/******************
	 *  Mailbox utils 
	 *  
	 ******************/

	/** Get Mailboxes from file **/
	private static MailboxDB buildMailboxFromFile(ArrayList<String> userNames, JsonObject mailObject) {
		MailboxDB mailboxdb = new MailboxDB();

		// Get each mailbox info
		for(int i=0; i < mailObject.size(); i++){
			JsonObject user = mailObject.get(userNames.get(i)).asJsonObject();
			Mailbox tempMailbox = new Mailbox();

			if (user.containsKey("userid"))
				tempMailbox.setUserID(Integer.parseInt(user.get("userid").toString()));
			if (user.containsKey("messageNumber"))
				tempMailbox.setMessageNumber(Integer.parseInt(user.get("messageNumber").toString()));
			if (user.containsKey("messages"))
				tempMailbox.setMessages(extractMessagesFromJson(user));
			mailboxdb.insertMailbox(tempMailbox);
		}
		return mailboxdb;
	}

	/** Get message array from user **/
	private static ArrayList<Message> extractMessagesFromJson(JsonObject user) {
		ArrayList<Message> tempMessages = new ArrayList<Message>();
		JsonArray jsonMessages = user.get("messages").asJsonArray();

		try {
			for(int j=0; j < jsonMessages.size(); j++){
				Message tempMessage = new Message();
				JsonObject jsonMessage = jsonMessages.getJsonObject(j);

				if (jsonMessage.containsKey("messageID"))
					tempMessage.setMessageID(Integer.parseInt(jsonMessage.getValue("messageID").toString()));
				if (jsonMessage.containsKey("date"))
					tempMessage.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonMessage.getValue("date").toString()));
				if (jsonMessage.containsKey("title"))
					tempMessage.setTitle(jsonMessage.getValue("title").toString());
				if (jsonMessage.containsKey("sender"))
					tempMessage.setSender(jsonMessage.getValue("sender").toString());
				if (jsonMessage.containsKey("senderID"))
					tempMessage.setSenderID(Integer.parseInt(jsonMessage.getValue("senderID").toString()));
				if (jsonMessage.containsKey("receiver"))
					tempMessage.setReceiver(jsonMessage.getValue("receiver").toString());
				if (jsonMessage.containsKey("receiverID"))
					tempMessage.setReceiverID(Integer.parseInt(jsonMessage.getValue("receiverID").toString()));

				tempMessages.add(tempMessage);
			}
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempMessages;
	}

	/** Get all mailboxes **/
	public static MailboxDB getAllMailboxes() throws IOException
	{
		MailboxDB db = new MailboxDB();
		InputStream fileReader = new FileInputStream(MAILBOX_JSON);

		try{
			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject userObject = jsonReader.readObject();
			fileReader.close();
			ArrayList<String> userNames = new ArrayList<String>();
			userNames.addAll(userObject.keySet()); 
			db = buildMailboxFromFile(userNames, userObject);
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			fileReader.close();
		}
		return db;
	}

	/** Saves input mailbox to file, including messages **/
	public static boolean SaveMailbox(Mailbox mailbox)
	{
		boolean success = false;
		InputStream fileReader = null;

		try {
			fileReader = new FileInputStream(MAILBOX_JSON);

			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject mailboxObject = jsonReader.readObject();
			jsonReader.close();
			fileReader.close();
			JsonObjectBuilder mailboxesBuilder = Json.createObjectBuilder();

			if(mailboxObject != null)
				if(mailboxObject != JsonArray.NULL)
					mailboxObject.entrySet().forEach(e -> mailboxesBuilder.add(e.getKey(), e.getValue()));

			// Remove if exists
			mailboxesBuilder.remove(mailbox.getUserName());	

			// Add all user's messages
			JsonObjectBuilder mailboxBuilder = Json.createObjectBuilder();
			mailboxBuilder.add("messages", buildMessageArray(mailbox.getMessages())); 

			// Add all mailbox's properties and attributes
			mailboxBuilder.add("userid", mailbox.getUserID())
			.add("messageNumber", mailbox.getMessageNumber() + "")
			.add("username", mailbox.getUserName() + "");

			mailboxesBuilder.add(mailbox.getUserName(), mailboxBuilder);

			// Write to file
			OutputStream os = new FileOutputStream(MAILBOX_JSON);
			Map<String, Boolean> config = new HashMap<>();
			config.put(JsonGenerator.PRETTY_PRINTING, true);
			JsonWriterFactory jwf = Json.createWriterFactory(config);
			JsonWriter jsonWriter = jwf.createWriter(os);
			jsonWriter.writeObject(mailboxBuilder.build());
			jsonWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			success = true;
		}
		return success;
	}

	/** Builds and returns an array built in Json */
	private static JsonArrayBuilder buildMessageArray(ArrayList<Message> messages) {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		for(Message message : messages){
			JsonObjectBuilder jpb = Json.createObjectBuilder().
					add("messageID", message.getMessageID()).
					add("date", message.getDate().toString()).
					add("title", message.getTitle()).
					add("sender", message.getSender().toString()).
					add("senderID", message.getSenderID() + "").
					add("receiver", message.getReceiver().toString()).
					add("receiverID", message.getReceiverID() + "");
			arrayBuilder.add(jpb);
		}
		return arrayBuilder;
	}
	
	/** Print all mailboxes in array */
	public static void printAllMailboxes(ArrayList<Mailbox> mailboxes)
	{
		for(Mailbox newMailbox : mailboxes)
			System.out.println(newMailbox.getUserID() + " | " + newMailbox.getUserName() + " | " + newMailbox.getMessageNumber() + " | \n" );
	}

	/** Converts all mailboxes in mailboxdb to a String */
	public static String getAllnewMailboxes(ArrayList<Mailbox> mailboxes)
	{
		String tempString = "";

		for(Mailbox newMailbox : mailboxes)
			tempString += newMailbox.getUserID() + " | " + newMailbox.getUserName() + " | " + newMailbox.getMessageNumber() + "\n";

		return tempString;
	}

	/******************
	 *  Other utils 
	 *  
	 ******************/

	/**
	 * A common method for all enums since they can't have another base class
	 * @param <T> Enum type
	 * @param c enum type. All enums must be all caps.
	 * @param string case insensitive
	 * @return corresponding enum, or null
	 */
	public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
		if( c != null && string != null ) {
			try {
				return Enum.valueOf(c, string.trim().toUpperCase());
			} 
			catch(IllegalArgumentException ex) {
			}
		}
		return null;
	}
}
