package things;

import advworld.level.*;

public class Thing {
	
	private String myName;
	private Location myLocation;
	private String myDescription;
	
	public Thing(){
		myName = null;
		myLocation = null;
		myDescription = null;
	}
	
	public Thing(String name, Location loc, String desc){
		myName = name;
		myLocation = loc;
		myDescription = desc;
	}
	
	public String getName(){
		return myName;
	}
	
	public Location getLocation(){
		return myLocation;
	}
	
	public String myDescription(){
		return myDescription;
	}
	
}
