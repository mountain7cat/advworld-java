package advworld.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;

import advworld.Game;
import advworld.level.*;

/**
 * All utility functions for the project here.
 * @author Sam
 *
 */
public class Utility {

	private static Pattern[] regexExpressions = {
		Pattern.compile("LOCATION(\\s++\\w++){2}(\\s++PARENT\\s++\\w++)?(\\s++CHILDREN(\\s++\\w++)++)?"),
		Pattern.compile("CONNECTION(\\s++\\w++){2}"),
		Pattern.compile("OBJECT(\\s++\\w++){3}"),
		Pattern.compile("MONSTER(\\s++\\w++){3}"),
		Pattern.compile("NPC(\\s++\\w++){3}"),
		Pattern.compile("EXIT(\\s++\\w++){2}(\\s++LOCK\\s++)?")
	};

	private static final int LOCATION = 0;
	private static final int CONNECTION = 1;
	private static final int OBJECT = 2;
	private static final int MONSTER = 3;
	private static final int NPC = 4;
	private static final int EXIT = 5;

	/**
	 * prints debug message, only when Game.DEBUG is true would it
	 * print.
	 */
	public static void debug(Object msg){
		if(Game.DEBUG)
			println("DEBUG: "+msg);
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
		hm.put(Game.TOPLEVEL_WORLD,new Location(Game.TOPLEVEL_WORLD));

		BufferedReader f;
		try {
			f = new BufferedReader(new FileReader(worldFilePath));
			String line;
			while((line=f.readLine())!=null){
				if(regexExpressions[LOCATION].matcher(line).find()){
					setupLocation(hm,line);
				} else if(regexExpressions[CONNECTION].matcher(line).find()){
					setupConnection(hm,line);
				} else if(regexExpressions[EXIT].matcher(line).find()){
					setupExit(hm,line);
				}else if(regexExpressions[OBJECT].matcher(line).find()){
					setupObject(hm,line);
				} else if(regexExpressions[MONSTER].matcher(line).find()){
					debug("MONSTER being initialized...");

				} else if(regexExpressions[NPC].matcher(line).find()){
					debug("NPC being initialized...");

				} else throw new AdvworldException("Improper world description: " + line);
			}

		} catch (Exception e) { //generic conversion to advworld exception class
			throw new AdvworldException("Exception in intializing world: "+e.getMessage());
		}
		return hm;
	}
	
	/*
	 * SETUP METHODS
	 * String correctLine is a correctly written line for setup, no error checking here
	 */
	public static void setupLocation(HashMap<String,Location> hm, String line)
	  throws ClassNotFoundException, IllegalArgumentException, SecurityException,
	  		InstantiationException, IllegalAccessException, InvocationTargetException,
	  		NoSuchMethodException, AdvworldException{
		debug("LOCATION being initialized...");
		boolean startlevelInitialized = false;
		String[] tokens = line.trim().split("\\s");
		String locName=tokens[1], locType=tokens[2],parentName;
		String[] childrenName;
		
		Class<?> clas = Class.forName("advworld.level."+locType);
		Location loc = (Location)(clas.getConstructor(String.class).newInstance(locName));
		
		if (tokens[tokens.length-1].equals("[start]")) {
			if (startlevelInitialized == true) {
				throw new AdvworldException("Cannot have more than one start location");
			} else {
				startlevelInitialized = true;
				Game.startlevel = loc;
			}
			//take out the start label
			tokens = line.split("\\[start\\]")[0].trim().split("\\s");
		}
		debug("loc is "+loc.getName());
		hm.put(locName,loc);
		
		if(setupLocation_JustWorld(tokens)){
			debug("just world");
			hm.get(Game.TOPLEVEL_WORLD).addChild(loc);
		} else if(setupLocation_WorldWithJustParent(tokens)){//assumes parent already instantiated
			debug("world with parent "+tokens[4]);
			parentName = tokens[4];
			hm.get(tokens[4]).addChild(loc);
		} else if(setupLocation_WorldWithParentAndChildren(tokens)){
			debug("world with parent and children");
			parentName = tokens[3];
			hm.get(tokens[3]).addChild(loc);
			for(int i=5;i<tokens.length;i++)
				loc.addChild(hm.get(tokens[i]));
		} else
			//shouldn't hit this case
			throw new AdvworldException("Improper World Description: "+line);
	}
	public static void setupConnection(HashMap<String,Location> hm, String line){
		String[] tokens = line.trim().split("\\s");
		debug("CONNECTION being initilaized");
		Path c1 = new Path(hm.get(tokens[1]), hm.get(tokens[2]),false);
		Path c2 = new Path(hm.get(tokens[2]), hm.get(tokens[1]),false);
		
		hm.get(tokens[1]).addExit(c1);
		hm.get(tokens[2]).addExit(c2);
	}
	public static void setupExit(HashMap<String,Location> hm, String line){
		String[] tokens = line.trim().split("\\s");
		Path c = new Path(hm.get(tokens[1]), hm.get(tokens[2]),(tokens.length==4&&tokens[3]=="LOCK"));
		
		hm.get(tokens[1]).addExit(c);
	}
	public static void setupObject(HashMap<String,Location> hm,String line){
		debug("OBJECT being initialized...");
	}
	
	
	public static boolean setupLocation_JustWorld(String[] tokens){
		return tokens.length==3;
	}
	public static boolean setupLocation_WorldWithJustParent(String[] tokens){
		return tokens.length==5;
	}
	public static boolean setupLocation_WorldWithParentAndChildren(String[] tokens){
		return tokens.length > 6;
	}	
}
