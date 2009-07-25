package advworld.util;

import java.io.*;
import java.util.*;
import advworld.Game;
import advworld.level.Location;
import advworld.level.Path;
import advworld.player.Party;
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
	private static void go(String arg[]) {
		Utility.debug("go called");
		if(arg.length <= 1){
			System.out.println("Need direction.");
		} else if(arg.length == 2){
			Path p;
			String destination = arg[1];
			Utility.debug(Game.theParty.getlocation().getName());
			Iterator<Path> paths = Game.theParty.getlocation().exits().iterator();
			if(Game.theParty.getlocation().getName().equals(destination)){
				Utility.println("You are already at "+destination);
				System.out.println(Game.theParty.getlocation().description());
			} else {
				while(paths.hasNext()){
					p = paths.next();
					if(p.getTo().getName().equals(destination) || p.getTo().getTypeName().equals(destination)){
						if(!p.getFrom().getName().equals(Game.theParty.getlocation().getName())){
							//shouldn't hit this case, where a path's from is not the current location
							Utility.debug("GO function - Malformed path: FIX IT!!!");
							return;
						} else if (p.isLocked()){
							System.out.println(p.getTo().lockedDescription());
							System.out.println(Game.theParty.getlocation().description());
							return;
						}
						//perform moving function
						Game.theParty.setLocation(p.getTo());
						Utility.println("You moved from " + p.getFrom().getName() + " to " + p.getTo().getName() + ".");
						System.out.println(Game.theParty.getlocation().description());
						return;
					}
				}
				Utility.println(destination + " is not an exit.");
				System.out.println(Game.theParty.getlocation().description());
			}
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
		Utility.debug("status called");
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
		Utility.debug("help called");
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
		Utility.debug("quit called");
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
		Utility.debug("look called");
		if(arg.length == 1){
			System.out.println(Game.theParty.getlocation().description());
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
