package ismt.application.engine;

public class Connection {
	/**
	 * 
	 */
	public Connection() {
		super();
	}
	/**
	 * @param userID1
	 * @param userID2
	 * @param connectionWeigth
	 * @param relation
	 */
	public Connection(int userID1, int userID2, int connectionWeigth, Relationship relation) {
		super();
		this.setUserID1(userID1);
		this.setUserID2(userID2);
		this.setConnectionWeigth(connectionWeigth);
		this.setRelation(relation);
	}
	
	private int userID1, userID2, connectionWeigth;
	private Relationship relation;
	
	/**
	 * @return the userID1
	 */
	public int getUserID1() {
		return userID1;
	}
	/**
	 * @param userID1 the userID1 to set
	 */
	public void setUserID1(int userID1) {
		this.userID1 = userID1;
	}
	/**
	 * @return the userID2
	 */
	public int getUserID2() {
		return userID2;
	}
	/**
	 * @param userID2 the userID2 to set
	 */
	public void setUserID2(int userID2) {
		this.userID2 = userID2;
	}
	/**
	 * @return the connectionWeigth
	 */
	public int getConnectionWeigth() {
		return connectionWeigth;
	}
	/**
	 * @param connectionWeigth the connectionWeigth to set
	 */
	public void setConnectionWeigth(int connectionWeigth) {
		this.connectionWeigth = connectionWeigth;
	}
	/**
	 * @return the relation
	 */
	public Relationship getRelation() {
		return relation;
	}
	/**
	 * @param relation the relation to set
	 */
	public void setRelation(Relationship relation) {
		this.relation = relation;
	}

}
