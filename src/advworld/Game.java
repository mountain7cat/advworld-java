package advworld;
import java.io.*;
import java.util.*;

import advworld.level.*;
import advworld.util.AdvworldException;
import advworld.util.Command;

public class Game {

	/**
	 * Private parameters for the game
	 */
	private static World world;
	private static HashMap<String,Location> locations;
	private static HashMap<String,Command> commands;

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
				String[] tokens = s.split(" ");
				if(commands.containsKey(tokens[0])){
					commands.get(tokens[0]).run(new String[]{"a","b"});
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

		
		//load command list
		commands = Command.intialize();
		//initialize variables
		setupWorld("some file path");
	}

	/**
	 * set up the world in the game with the world description file
	 * @param worldFilePath The path of the world description file
	 * @throws AdvworldException
	 */
	private static void setupWorld(String worldFilePath) throws AdvworldException{
		world = new World("mainWorld");
		BufferedReader f;
		try {
			f = new BufferedReader(new FileReader(worldFilePath));
			String line;
			while((line=f.readLine())!=null){

				if(true/*regular expressions that check if the line is written correctly*/){
					String[] tokens = line.split(" ");
					if(tokens[0]=="LOCATION"){
						World loc = new World(tokens[1]);
						locations.put(tokens[1], loc);
						if(tokens.length<3)
							world.addChild(loc);
						else {
							locations.get(tokens[3]).addChild(loc);
						}
					}
				} else
					throw new AdvworldException("Improper world description: "+ line);
			}

		} catch (Exception e) { //generic conversion to advworld exception class
			throw new AdvworldException(e.getMessage());
		}
	}
}
