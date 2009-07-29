package advworld.level;

import java.util.Iterator;
import java.util.Vector;

import advworld.util.Utility;

public class City extends Location{

	private String type = "City";
	
	public City(String name){
		super(name);
	}

	public City(String name, State Parent){
		super(name,Parent);
	}
	
	public City(String name, State Parent, Iterator<Town> children){
		super(name,Parent,(Location)children);
	}
	
	public void action(String command) {
		//does nothing so far
	}

	public void description() {
		super.description();
	}

	public Vector<Path> exits() {
		return super.exits();
	}
	
	public String getType(){
		return type;
	}
}
