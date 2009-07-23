package advworld.util;

import java.util.*;
import advworld.Game;
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
		return table;
	}
	
	public abstract void run(String arg[]);
	
	//All command functions implemented below
	private static void go(String arg[]){
		Utility.debug("go called");
		if(arg.length <= 1){
			System.out.println("Need direction.");
		} else {
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
				println("");
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
	
	private static void println(Object msg) {
		Utility.println(msg);
	}
	
}
