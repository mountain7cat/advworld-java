package advworld.level;

import java.util.Iterator;
import java.util.Vector;

import advworld.util.Utility;

public class Room extends Location{
	
	private String type = "Room";
	
	public Room(String name){
		super(name);
	}

	public Room(String name, Building Parent){
		super(name,Parent);
	}
	
	public Room(String name, Building Parent, Iterator<Location> children){
		super(name,Parent,children);
	}
	
	public void action(String command) {
		//does nothing so far
	}

	public void description() {
		super.description();
	}

	public String lockDescription(){
		return super.lockedDescription();
	}
	
	public Vector<Path> exits() {
		return super.exits();
	}
	
	public String getType(){
		return type;
	}
}
