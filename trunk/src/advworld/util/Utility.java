package advworld.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import advworld.Game;
import advworld.level.Location;
import advworld.level.World;

/**
 * All utility functions for the project here.
 * @author Sam
 *
 */
public class Utility {
	
	/**
	 * prints debug message, only when Game.DEBUG is true would it
	 * print.
	 */
	public static void debug(Object msg){
		if(Game.DEBUG)
			print(msg);
	}
	/*
	 * wrapper so we don't have to type System.out.println
	 */
	public static void print(Object msg){
		System.out.println(msg);
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
			String line, correctWorldFileRegex = "correct regex";
			while((line=f.readLine())!=null){
				if(line.matches(correctWorldFileRegex)){
					String[] tokens = line.split(" ");
					if(tokens[0]=="LOCATION"){
						String locName=tokens[1], locType=tokens[2],parentName;
						String[] childrenName;
						 
						Class<?> clas = Class.forName("advworld.level."+locType);
						Location loc = (Location)clas.getConstructor(new Class[]{String.class}).newInstance(new String(locName));
						
						//Method m = clas.
						//World loc = new World(locName);
						hm.put(locName,loc);
						if(setupWorld_JustWorld()){
							toplevel.addChild(loc);
						}else if(setupWorld_WorldWithParent()){//assumes parent already instantiated
							parentName = tokens[3];
							//AllLocations.get()
						}else if(setupWorld_WorldWithParentAndChildren()){
							
						}
					} else if(tokens[0]=="CONNECTIONS"){
						
					}
				} else
					throw new AdvworldException("Improper world description: "+ line);
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
