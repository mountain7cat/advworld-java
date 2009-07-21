package advworld.util;

import advworld.player.*;
import advworld.monsters.*;
import java.util.*;
import advworld.Game;


public abstract class Command {
	
	private static HashMap<String, Command> table = new HashMap<String,Command>();
	public static HashMap<String,Command> intialize(){
		//add new commands here
		table.put("go", new Command(){public void run(String arg[]){go(arg);}});
		table.put("attack", new Command(){public void run(String arg[]){attack(arg);}});
		table.put("status", new Command(){public void run(String arg[]){status(arg);}});
		table.put("help", new Command(){public void run(String arg[]){help(arg);}});
		table.put("quit", new Command(){public void run(String arg[]){quit(arg);}});
		return table;
	}
	
	public abstract void run(String arg[]);
	
	//All command functions implemeneted below
	private static void go(String arg[]){
		System.out.println("go called");
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
		System.out.println("attack called");
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
		System.out.println("status called");
		if(arg.length == 1){
			Game.thePlayer.status();
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
			Set s = table.keySet();
			Iterator iter = s.iterator();
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
	
}
