package advworld.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

import advworld.Game;
import advworld.level.Location;

/**
 * All utility functions for the project here.
 * @author Sam
 *
 */
public class Utility {
	
	private static Pattern[] regexExpressions = {
		Pattern.compile("LOCATION\\s++\\w++\\s++\\w++(?:\\s++PARENT\\s++\\w++)?(?:\\s+CHILDREN(?:\\s++\\w++)++)?"),
		Pattern.compile("CONNECTION\\s++\\w++\\s++\\w++"),
		Pattern.compile("OBJECT\\s++\\w++\\s++\\w++\\s++\\w++"),
		Pattern.compile("MONSTER\\s++\\w++\\s++\\w++\\s++\\w++"),
		Pattern.compile("NPC\\s++\\w++\\s++\\w++\\s++\\w++"),
	};
	
	private static final int LOCATION = 0;
	private static final int CONNECTION = 1;
	private static final int OBJECT = 2;
	private static final int MONSTER = 3;
	private static final int NPC = 4;
	
	/**
	 * prints debug message, only when Game.DEBUG is true would it
	 * print.
	 */
	public static void debug(Object msg){
		if(Game.DEBUG)
			println(msg);
	}
	/*
	 * wrapper so we don't have to type System.out.println
	 */
	public static void print(Object msg) {
		System.out.print(msg);
	}
	
	public static void println(Object msg){
		print(msg + "\n");
	}
	
	/**
	 * set up the world in the game with the world description file
	 * @param worldFilePath The path of the world description file
	 * @throws AdvworldException
	 */
	public static HashMap<String,Location> setupWorld(String worldFilePath) throws AdvworldException{
		//create dummy world container first
		HashMap<String,Location> hm = new HashMap<String,Location>();
		Location toplevel = new Location(Game.TOPLEVEL_WORLD);
		hm.put(Game.TOPLEVEL_WORLD, toplevel);
		BufferedReader f;
		try {
			f = new BufferedReader(new FileReader(worldFilePath));
			String line;
			while((line=f.readLine())!=null){
				String[] tokens = line.split(" ");
				if(regexExpressions[LOCATION].matcher(line).find()){
					debug("LOCATION being intiailized...");
					String locName=tokens[1], locType=tokens[2],parentName;
					String[] childrenName;
					 
					Class<?> clas = Class.forName("advworld.level."+locType);
					Location loc = (Location)clas.getConstructor(String.class).newInstance(locName);
					
					//Method m = clas.
					//World loc = new World(locName);
					hm.put(locName,loc);
					if(setupWorld_JustWorld()){
						toplevel.addChild(loc);
					} else if(setupWorld_WorldWithParent()){//assumes parent already instantiated
						parentName = tokens[3];
							//AllLocations.get()
					} else if(setupWorld_WorldWithParentAndChildren()){
						
					}
				} else if(regexExpressions[CONNECTION].matcher(line).find()){
					debug("CONNECTION being initialized...");
					
				} else if(regexExpressions[OBJECT].matcher(line).find()){
					debug("OBJECT being initialized...");
					
				} else if(regexExpressions[MONSTER].matcher(line).find()){
					debug("MONSTER being initialized...");
					
				} else if(regexExpressions[NPC].matcher(line).find()){
					debug("NPC being initialized...");
					
				} else throw new AdvworldException("Improper world description: " + line);
		}
	
		} catch (Exception e) { //generic conversion to advworld exception class
			throw new AdvworldException(e.getMessage());
		}
		return hm;
	}
	public static boolean setupWorld_JustWorld(){
		return true;
	}
	public static boolean setupWorld_WorldWithParent(){
		return true;
	}
	public static boolean setupWorld_WorldWithParentAndChildren(){
		return true;
	}	
}
