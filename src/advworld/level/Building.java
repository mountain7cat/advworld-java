package advworld.level;

import java.util.Iterator;
import java.util.Vector;

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
		return "This is a Building";
	}

	public Vector<Path> exits() {
		return null;
	}
}
