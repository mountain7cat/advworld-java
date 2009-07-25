package advworld.level;

import java.util.Iterator;
import java.util.Vector;

import advworld.util.Utility;

public class Continent extends Location {

	private String type = "Continent";
	
	public Continent(String name){
		super(name);
	}

	public Continent(String name, World Parent){
		super(name,Parent);
	}
	
	public Continent(String name, World Parent, Iterator<Country> children){
		super(name,Parent,(Location) children);
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
