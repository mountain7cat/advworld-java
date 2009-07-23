package advworld.level;

import java.util.Iterator;
import java.util.Vector;

public class Country extends Location{

	public Country(String name){
		super(name);
	}

	public Country(String name, Continent Parent){
		super(name,Parent);
	}
	
	public Country(String name, Continent Parent, Iterator<State> children){
		super(name,Parent,(Location)children);
	}
	
	public void action(String command) {
		//does nothing so far
	}

	public String description() {
		return "This is a Country";
	}

	public Vector<Connection> exits() {
		return null;
	}
}
