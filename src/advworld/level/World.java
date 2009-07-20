package advworld.level;

import java.util.*;

/**
 * implementation of the Location class with a general World
 * @author Sam
 *
 */
public class World extends Location{
		
	public World(String name){
		super();
	}

	public World(String name, Location Parent){
		super();
	}
	
	public World(String name, Location Parent, Iterator<Location> children){
		super();
	}
	
	public void action(String command) {
		//does nothing so far
	}

	public String description() {
		return "This is a world";
	}

	public Vector<Exit> exits() {
		return null;
	}
}
