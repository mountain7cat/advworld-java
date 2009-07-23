package advworld.level;

import java.util.Iterator;
import java.util.Vector;

public class State extends Location{

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
		return "This is a State";
	}

	public Vector<Connection> exits() {
		return null;
	}
}
