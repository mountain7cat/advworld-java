package advworld.objects;

import advworld.level.*;

public abstract class Objects {
	
	private String myName;
	private Location myLocation;
	private String myDescription;
	
	public Objects(){
		myName = null;
		myLocation = null;
		myDescription = null;
	}
	
	public Objects(String name, Location loc, String desc){
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
