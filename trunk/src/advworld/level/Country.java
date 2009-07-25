package advworld.level;

import java.util.Iterator;
import java.util.Vector;

import advworld.util.Utility;

public class Country extends Location{

	private String type = "Country";
	
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
		return super.description();
	}
	
	public String getType(){
		return type;
	}

	public Vector<Path> exits() {
		return super.exits();
	}
}
