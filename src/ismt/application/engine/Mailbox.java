package ismt.application.engine;

import java.util.ArrayList;

public class Mailbox {
	
	private ArrayList<Message> messages;
	private int messageNumber;
	private int userID;
	private String userName;
	
	public Mailbox() {
		super();
		this.messages = new ArrayList<Message>();
		this.messageNumber = 0;
		this.userID = 0;
	}
	/**
	 * @param messages
	 * @param messageNumber
	 * @param userID
	 */
	public Mailbox(ArrayList<Message> messages, int messageNumber, int userID) {
		super();
		this.messages = messages;
		this.messageNumber = messageNumber;
		this.userID = userID;
	}
	
	/**
	 * @return the messages
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	/**
	 * @return the messageNumber
	 */
	public int getMessageNumber() {
		return messageNumber;
	}
	/**
	 * @param messageNumber the messageNumber to set
	 */
	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
