package advworld.objects;

import advworld.level.*;

public abstract class Objects {
	
	protected String myName;
	protected Location myLocation;
	protected String myDescription;
	
	protected boolean pickUpAble;
	
	protected String type;
	
	public Objects(String name, Location loc, String desc){
		myName = name;
		myLocation = loc;
		myDescription = desc;
		loc.addObject(this);
		pickUpAble = false;
	}
	
	public String getName(){
		return myName;
	}
	
	public Location getLocation(){
		return myLocation;
	}
	
	public String getDescription(){
		return myDescription;
	}
	
	public String getType(){
		return type;
	}
	
	public boolean isType(String type){
		return this.type.equals(type);
	}
	
	public boolean isPickUpAble(){
		return pickUpAble;
	}
}
