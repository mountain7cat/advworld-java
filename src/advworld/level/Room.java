package advworld.level;

import java.util.Iterator;
import java.util.Vector;

import advworld.util.Utility;

public class Room extends Location{
	
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

	public String description() {
		return super.description()+"This is a Room\n";
	}

	public Vector<Path> exits() {
		return null;
	}
}
