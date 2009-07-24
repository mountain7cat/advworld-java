package advworld.level;

import java.util.Iterator;
import java.util.Vector;

import advworld.util.Utility;

public class Building extends Location{

	public Building(String name){
		super(name);
	}

	public Building(String name, Town Parent){
		super(name,Parent);
	}
	
	public Building(String name, Town Parent, Iterator<Room> children){
		super(name,Parent,(Location)children);
	}
	
	public void action(String command) {
		//does nothing so far
	}

	public String description() {
		return super.description()+
			"This is a Building";
	}

	public Vector<Path> exits() {
		return super.exits();
	}
}
