package advworld.level;

import java.util.Iterator;
import java.util.Vector;

import advworld.util.Utility;

public class State extends Location{

	private String type = "State";
	
	public State(String name){
		super(name);
	}

	public State(String name, Country Parent){
		super(name,Parent);
	}
	
	public State(String name, Country Parent, Iterator<City> children){
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
