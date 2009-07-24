package advworld.level;

/**
 * 
 * @author Sam
 *
 */
public class Path {
	// Member variables
	private Location from;
	private Location to;
	private boolean locked;
	
	/*
	 * create unlocked path for Location from ==> Location to
	 */
	public Path(Location from, Location to){
		this.from = from;
		this.to = to;
		this.locked = false;
		this.from.addExit(this);
	}
	
	/*
	 * create path for Location from ==> Location to, specifying lock status
	 */
	public Path(Location from, Location to, boolean locked){
		this.from = from;
		this.to = to;
		this.locked = locked;
		this.from.addExit(this);
	}
	
	/*
	 * getters for the locations. Note that there is no setters
	 * departure and arrival location is NOT mutating after creation
	 */
	public Location getFrom(){
		return from;
	}
	public Location getTo(){
		return to;
	}
	
	/*
	 * locking/unlock and checking mechanism
	 */
	public void unlock(){
		locked = false;
	}
	public void lock(){
		locked = true;
	}
	public boolean isLocked(){
		return locked;
	}
	
	// toString method
	public String toString(){
		return "Exit from"+from.getName()+" to "+to.getName()+(isLocked()?". It is locked.":".");
	}
}
