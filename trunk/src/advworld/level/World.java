package advworld.level;

import java.util.*;

import advworld.util.Utility;

/**
 * implementation of the Location class with a general World
 * @author Sam
 *
 */
public class World extends Location{
		
	public World(String name){
		super(name);
	}

	public World(String name, Location Parent){
		super(name,Parent);
	}
	
	public World(String name, Location Parent, Iterator<Location> children){
		super(name,Parent,children);
	}
	
	public void action(String command) {
		//does nothing so far
	}

	public String description() {
		return super.description()+"This is a world\n";
	}

	public Vector<Path> exits() {
		return null;
	}
}
