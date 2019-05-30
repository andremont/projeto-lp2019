package ismt.application.engine;

import java.util.Date;

public class Message {
	
	private int messageID;
	private Date date;
	private String title;
	private String text;
	private String sender;
	private int senderID;
	private String receiver;
	private int receiverID;
	
	
	public Message() {
		super();
	}
	
	/**
	 * @param messageID
	 * @param date
	 * @param title
	 * @param text
	 * @param sender
	 * @param senderID
	 * @param receiver
	 * @param receiverID
	 */
	public Message(int messageID, Date date, String title, String text, String sender, int senderID, String receiver,
			int receiverID) {
		super();
		this.messageID = messageID;
		this.date = date;
		this.title = title;
		this.text = text;
		this.sender = sender;
		this.senderID = senderID;
		this.receiver = receiver;
		this.receiverID = receiverID;
	}
	
	/**
	 * @return the messageID
	 */
	public int getMessageID() {
		return messageID;
	}
	/**
	 * @param messageID the messageID to set
	 */
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
	 * @return the senderID
	 */
	public int getSenderID() {
		return senderID;
	}
	/**
	 * @param senderID the senderID to set
	 */
	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}
	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}
	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	/**
	 * @return the receiverID
	 */
	public int getReceiverID() {
		return receiverID;
	}
	/**
	 * @param receiverID the receiverID to set
	 */
	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}
}
