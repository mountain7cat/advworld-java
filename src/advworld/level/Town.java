package advworld.level;

import java.util.Iterator;
import java.util.Vector;

import advworld.util.Utility;

public class Town extends Location{

	public Town(String name){
		super(name);
	}

	public Town(String name, City Parent){
		super(name,Parent);
	}
	
	public Town(String name, City Parent, Iterator<Building> children){
		super(name,Parent,(Location)children);
	}
	
	public void action(String command) {
		//does nothing so far
	}

	public String description() {
		return super.description()+"This is a Town\n";
	}

	public Vector<Path> exits() {
		return null;
	}
}
