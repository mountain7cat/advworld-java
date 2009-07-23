package advworld.level;

import java.util.Iterator;
import java.util.Vector;

public class Continent extends Location {

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
		return "This is a Continent";
	}

	public Vector<Connection> exits() {
		return null;
	}
}
