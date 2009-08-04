package advworld.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Pattern;

import advworld.Game;
import advworld.level.*;
import advworld.objects.Thing;

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
	
	private static String[] classPackages = {
		"advworld.level.",//location
		"",//connection
		"advworld.objects.",//object
		"advworld.monsters.",//monster
		"",//NPC
		""//exits
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
					setupMonster(hm,line);
				} else if(regexExpressions[NPC].matcher(line).find()){
					setupNPC(hm,line);
				} else throw new AdvworldException("Improper world description: " + line);
			}

		} catch (Exception e) { //generic conversion to advworld exception class
			throw new AdvworldException("Exception in intializing world: "+e.toString());
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
		
		Class<?> clas = Class.forName(classPackages[LOCATION]+locType);
		Location loc;
		if(hm.containsKey(locName)){
			loc = hm.remove(locName);
			debug("loc "+loc.getName()+" removed.");
		}
		else
			loc = (Location)(clas.getConstructor(String.class).newInstance(locName));
		
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
		debug("loc "+loc.getName()+" added.");
		hm.put(locName,loc);
		
		if(setupLocation_JustWorld(tokens)){
			debug("just world");
			hm.get(Game.TOPLEVEL_WORLD).addChild(loc);
		} else if(setupLocation_WorldWithJustParent(tokens)){//assumes parent already instantiated
			debug("world with parent "+tokens[4]);
			parentName = tokens[4];
			hm.get(tokens[4]).addChild(loc);
		} else if(setupLocation_WorldWithParentAndChildren(tokens)){
			debug("world with parent and children, parent is "+tokens[4]);
			parentName = tokens[4];
			hm.get(parentName).addChild(loc);
			for(int i=6;i<tokens.length;i++){
				loc.addChild(hm.get(tokens[i]));
			}
			debug("end of iteration");
		} else
			//shouldn't hit this case
			throw new AdvworldException("Improper World Description: "+line);
	}
	public static void setupConnection(HashMap<String,Location> hm, String line){
		String[] tokens = line.trim().split("\\s");
		debug("CONNECTION being initialized: "+tokens[1]+" "+tokens[2]);
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
	public static void setupObject(HashMap<String,Location> hm,String line) throws ClassNotFoundException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		debug("OBJECT being initialized...");
		String[] tokens = line.split("\\s");
		debug("1");
		String objectName = tokens[1], objectType = tokens[2], roomAssoc = tokens[3];
		debug(objectName +","+objectType+","+roomAssoc);
		debug("2:"+classPackages[OBJECT]+objectType);
		Class<?> clas = Class.forName(classPackages[OBJECT]+objectType);
		debug("3");
		clas.getConstructor(String.class, Location.class);
		debug("3.5");
		Thing o = (Thing)(clas.getConstructor(String.class, Location.class).newInstance(objectName,hm.get(roomAssoc)));
		debug("4");
	}
	public static void setupNPC(HashMap<String,Location> hm,String line){
		debug("NPC being initialized...");
	}
	public static void setupMonster(HashMap<String,Location> hm,String line){
		debug("Monster being initialized...");
	}
	
	private static boolean setupLocation_JustWorld(String[] tokens){
		return tokens.length==3;
	}
	private static boolean setupLocation_WorldWithJustParent(String[] tokens){
		return tokens.length==5;
	}
	private static boolean setupLocation_WorldWithParentAndChildren(String[] tokens){
		return tokens.length > 6;
	}	
}
