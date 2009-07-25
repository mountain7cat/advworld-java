package advworld;
import java.io.*;
import java.util.*;

import things.*;

import advworld.level.*;
import advworld.objects.Book;
import advworld.player.*;
import advworld.util.*;

public class Game {

	/**
	 * Private parameters for the game
	 */
	public static String TOPLEVEL_WORLD = "topLevelWorld";
	public static boolean DEBUG = false;
	public static Party theParty;
	
	public static Location startlevel;
	
	private static HashMap<String, Location> AllLocations;
	private static Location toplevel;
	private static HashMap<String,Command> commands;

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Prints the src/changelog.txt to the console.
	 */
	private static void printChangeLog(){
	    try {
	        BufferedReader in = new BufferedReader(new FileReader("src/changelog.txt"));
	        String str;
	        println("----");
	        int lineNumberMax = 10;
	        int i = 0;
	        while ((str = in.readLine()) != null && i < lineNumberMax) {
	            println(str);
	            i++;
	        }
	        println("...");
	        println("To see full change log, go to changelog.txt");
	        println("----");
	        println("");
	        in.close();
	    } catch (IOException e) {
	    	println(e.toString());
	    }
	}
	
	public static void main(String[] args) {		
		
		try {
			initialize();
		} catch (Exception e){
			println("initialization failed: "+e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		String s = null;
		if (args.length != 0) {
			println(args.length);
			System.exit(1);
		}
		
		println(startlevel==null);
		println(startlevel.description());
		
		while(true){
			print("> ");
			try {
				s = br.readLine();
				String[] tokens = s.split(" ", 2);
				if(commands.containsKey(tokens[0])){
					commands.get(tokens[0]).run(tokens);
				} else {
					println(tokens[0] + " is an invalid command.");
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
		println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		println("~~ Welcome to Adventure Game! ~~");
		println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		//print Change Log
		printChangeLog();
		
		//initialize variables
		AllLocations = Utility.setupWorld("src/testworld.txt");
		toplevel = AllLocations.get(TOPLEVEL_WORLD);	
		
		//load command list
		commands = Command.initialize();
		
		//initialize player
		theParty = new Party(new StandardPlayer(), startlevel);
		
		//initialize things
		initializeThings();
				
	}
	
	private static void initializeThings(){
		Book machine = new Book("Computer Organization and Design", startlevel, "Book used for CS61c", "blah blah blah... more cs stuff...");
	}

	//being REAL lazy, don't even want to type "Utility."
	private static void print(Object msg){
		Utility.print(msg);
	}	
	private static void println(Object msg){
		Utility.println(msg);
	}
	private static void debug(Object msg){
		Utility.debug(msg);
	}
}
