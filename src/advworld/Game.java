package advworld;
import java.io.*;
import java.util.*;

import advworld.level.*;
import advworld.player.*;
import advworld.util.*;

public class Game {

	/**
	 * Private parameters for the game
	 */
	public static String TOPLEVEL_WORLD = "topLevelWorld";
	private static HashMap<String, Location> AllLocations;
	private static Location toplevel;
	private static HashMap<String,Command> commands;
	public static StandardPlayer thePlayer;

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Prints the src/changelog.txt to the console.
	 */
	private static void printChangeLog(){
	    try {
	        BufferedReader in = new BufferedReader(new FileReader("src/changelog.txt"));
	        String str;
	        System.out.println("----");
	        while ((str = in.readLine()) != null) {
	            System.out.println(str);
	        }
	        System.out.println("----");
	        in.close();
	    } catch (IOException e) {
	    	System.out.println(e.toString());
	    }
	}
	
	public static void main(String[] args) {
		
		try {
			initialize();
		} catch (Exception e){
			System.out.println("initialization failed: "+e.getMessage());
			//System.exit(1);
		}


		String s = null;
		if (args.length != 0) {
			System.out.println(args.length);
			System.exit(1);
		}
		while(true){
			System.out.print("> ");
			try {
				s = br.readLine();
				String[] tokens = s.split(" ", 2);
				if(commands.containsKey(tokens[0])){
					commands.get(tokens[0]).run(tokens);
				} else {
					System.out.println(tokens[0] + " is an invalid command.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
	}

	/**
	 * Initializes the game
	 */
	private static void initialize()throws AdvworldException{
		//Welcome Message - will parse from a file later
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~ Welcome to Adventure Game! ~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		//print Change Log
		printChangeLog();
		
		//initialize player
		thePlayer = new StandardPlayer();
		
		//load command list
		commands = Command.intialize();
		
		//initialize variables
		AllLocations = Utility.setupWorld("src/world.txt");
		toplevel = AllLocations.get(TOPLEVEL_WORLD);	
		
		
	}


}
