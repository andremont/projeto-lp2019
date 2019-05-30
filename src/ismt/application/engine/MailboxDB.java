package ismt.application.engine;

import java.util.ArrayList;

public class MailboxDB{
	
	private ArrayList<Mailbox> mailboxes;

	/**
	 * @param 
	 */
	public MailboxDB() {
		super();
		this.mailboxes = new ArrayList<Mailbox>();
	}
	/**
	 * @param mailboxes
	 */
	public MailboxDB(ArrayList<Mailbox> mailboxes) {
		super();
		this.mailboxes = mailboxes;
	}

	/**
	 * @return the mailboxes
	 */
	public ArrayList<Mailbox> getMailboxes() {
		return mailboxes;
	}

	/**
	 * @param mailboxes the mailboxes to set
	 */
	public void setMailboxes(ArrayList<Mailbox> mailboxes) {
		this.mailboxes = mailboxes;
	}
	
	/**
	 * @param mailboxes the mailboxes to set
	 */
	public void setMailboxes() {
		this.mailboxes = new ArrayList<Mailbox>();
	}
	

	/**
	 * @param mailbox to insert
	 */
	public void insertMailbox(Mailbox _mailbox) {
		this.mailboxes.add(_mailbox);
	}
	
	/**
	 * @param mailbox to remove
	 */
	public void removeMailbox(Mailbox _mailbox) {
		this.mailboxes.remove(_mailbox);
	}
}
