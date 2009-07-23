package advworld.level;

public class Connection {
	// Member variables
	private Location loc1 = null;
	private Location loc2 = null;
	private boolean loc1_to_loc2 = true;
	private boolean loc2_to_loc1 = true;
	
	// Default constructor
	public Connection(){
		loc1 = null;
		loc2 = null;
	}
	
	public Connection(Location loc1, Location loc2){
		this.loc1 = loc1;
		this.loc2 = loc2;
	}
	
	public Connection(Location loc1, Location loc2, boolean loc1_to_loc2, boolean loc2_to_loc1){
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.loc1_to_loc2 = loc1_to_loc2;
		this.loc2_to_loc1 = loc2_to_loc1;
	}
	
	public Location getLoc1() {
		return loc1;
	}
	public void setLoc1(Location loc1) {
		this.loc1 = loc1;
	}
	public Location getLoc2() {
		return loc2;
	}
	public void setLoc2(Location loc2) {
		this.loc2 = loc2;
	}
	public boolean isLoc1_to_loc2() {
		return loc1_to_loc2;
	}
	public void setLoc1_to_loc2(boolean loc1ToLoc2) {
		loc1_to_loc2 = loc1ToLoc2;
	}
	public boolean isLoc2_to_loc1() {
		return loc2_to_loc1;
	}
	public void setLoc2_to_loc1(boolean loc2ToLoc1) {
		loc2_to_loc1 = loc2ToLoc1;
	}

	// toString method
	public String toString(){
		return "Connection between "+loc1.getName()+" and "+loc2.getName();
	}
}
