package advworld.objects;

import advworld.level.*;

public abstract class Thing {
	
	protected String myName;
	protected Location myLocation;
	protected String myDescription;
	
	protected boolean pickUpAble;
	protected boolean equip_able;
	
	protected String type;
	
	public Thing(String name, Location loc){
		myName = name;
		myLocation = loc;
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
	
	public void setDescription(String desc){
		myDescription = desc;
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
