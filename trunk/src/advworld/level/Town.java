package advworld.level;

import java.util.Iterator;
import java.util.Vector;

import advworld.util.Utility;

public class Town extends Location{

	private String type = "Town";
	
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
		return super.description();
	}

	public Vector<Path> exits() {
		return super.exits();
	}
	
	public String getType(){
		return type;
	}
}
