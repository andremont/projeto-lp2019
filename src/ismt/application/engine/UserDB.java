package ismt.application.engine;

import java.util.ArrayList;

public class UserDB {
	/**
	 * @param users
	 */
	public UserDB(ArrayList<User> users) {
		super();
		this.users = users;
	}
	
	public UserDB() {
		super();
		this.users = new ArrayList<User>();
	}

	private ArrayList<User> users;

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	/**
	 * @param users the users to set
	 */
	public void insertUser(User user) {
		this.users.add(user);
	}
	
	/**
	 * @param users the users to set
	 */
	public void removeUser(User user) {
		this.users.remove(user);
	}
}
