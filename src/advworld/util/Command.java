package advworld.util;

import java.util.*;
import advworld.Game;
import advworld.level.Connection;
import advworld.player.Player;


public abstract class Command {
	
	private static HashMap<String, Command> table = new HashMap<String,Command>();
	public static HashMap<String,Command> initialize(){
		//add new commands here
		table.put("go", new Command(){public void run(String arg[]){go(arg);}});
		table.put("attack", new Command(){public void run(String arg[]){attack(arg);}});
		table.put("status", new Command(){public void run(String arg[]){status(arg);}});
		table.put("help", new Command(){public void run(String arg[]){help(arg);}});
		table.put("quit", new Command(){public void run(String arg[]){quit(arg);}});
		table.put("look", new Command(){public void run(String arg[]){look(arg);}});
		return table;
	}
	
	public abstract void run(String arg[]);
	
	//All command functions implemented below
	private static void go(String arg[]){
		Utility.debug("go called");
		if(arg.length <= 1){
			System.out.println("Need direction.");
		} else if(arg.length == 2){
			Vector<Connection> connections = Game.theParty.getlocation().exits();
			for(int i = 0; i < connections.size(); i++){
				Connection exit = connections.get(i);
				//Utility.debug("Looking in " + exit.toString());
				//Utility.debug("arg[1] is " + arg[1]);
				//Utility.debug("loc1 is " + exit.getLoc1().getName() + ". loc2 is " + exit.getLoc2().getName());
				String destination = arg[1];
				//Utility.debug("destination is " + destination);
				String loc1Name = exit.getLoc1().getName();
				String loc2Name = exit.getLoc2().getName();
				if(destination.equals(loc2Name) && !destination.equals(Game.theParty.getlocation().getName())){
					if(!exit.isLoc1_to_loc2()){
						System.out.println(exit.getLoc2().getName() + " is locked.");
						Game.theParty.getlocation().description();
						return;
					}
					Game.theParty.setLocation(exit.getLoc2());
					System.out.println("You moved from " + exit.getLoc1().getName() + " to " + exit.getLoc2().getName() + ".");
					Game.theParty.getlocation().description();
					return;
				} else if (destination.equals(loc1Name) && !destination.equals(Game.theParty.getlocation().getName())){
					if(!exit.isLoc2_to_loc1()){
						System.out.println(exit.getLoc1().getName() + " is locked.");
						Game.theParty.getlocation().description();
						return;
					}
					Game.theParty.setLocation(exit.getLoc1());
					System.out.println("You moved from " + exit.getLoc2().getName() + " to " + exit.getLoc1().getName() + ".");
					Game.theParty.getlocation().description();
					return;					
				}
			}
			System.out.println(arg[1] + " is not a valid room.");
			Game.theParty.getlocation().description();
		} else {
			System.out.println("Too many arguments.");
			System.out.print("ECHO: ");
			for(int i = 0; i < arg.length ; i++){
				System.out.print(arg[i] + " ");
			}
			System.out.println();
		}
	}
	
	private static void attack(String arg[]){
		Utility.debug("attack called");
		if(arg.length <= 1){
			System.out.println("Need target.");
		} else {
			System.out.print("ECHO: ");
			for(int i = 0; i < arg.length ; i++){
				System.out.print(arg[i] + " ");
			}
			System.out.println();
		}
	}
	
	private static void status(String arg[]){
		println("status called");
		if(arg.length == 1){
			Iterator<Player> iter = Game.theParty.activesIterator();
			while (iter.hasNext()) {
				iter.next().status();
				if(iter.hasNext()){
					println("");	
				}
			}
		} else {
			// Should search current room for other players/monsters and return status of that person/monster
			System.out.print("ECHO: ");
			for(int i = 0; i < arg.length ; i++){
				System.out.print(arg[i] + " ");
			}
			System.out.println();
		}
	}	
	
	private static void help(String arg[]){
		System.out.println("help called");
		if(arg.length == 1){
			System.out.println("List of Commands:");
			Set<String> s = table.keySet();
			Iterator<String> iter = s.iterator();
			while(iter.hasNext()){
				System.out.println(" -" + iter.next());
			}
		} else {
			// Should search current room for other players/monsters and return status of that person/monster
			System.out.print("ECHO: ");
			for(int i = 0; i < arg.length ; i++){
				System.out.print(arg[i] + " ");
			}
			System.out.println();
		}
	}
	
	private static void quit(String arg[]){
		System.out.println("quit called");
		if(arg.length == 1){
			System.out.println("Good Bye!");
			System.exit(0);
		} else {
			System.out.print("ECHO: ");
			for(int i = 0; i < arg.length ; i++){
				System.out.print(arg[i] + " ");
			}
			System.out.println();
		}
	}
	
	private static void look(String arg[]){
		System.out.println("look called");
		if(arg.length == 1){
			Game.theParty.getlocation().description();
		} else {
			// used to look at other objects or players
			System.out.print("ECHO: ");
			for(int i = 0; i < arg.length ; i++){
				System.out.print(arg[i] + " ");
			}
			System.out.println();
		}
	}
	
	private static void println(Object msg) {
		Utility.println(msg);
	}
	
}
