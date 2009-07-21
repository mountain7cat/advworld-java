package advworld.util;

import advworld.player.Player;
import java.util.HashMap;
import java.util.Map;

public abstract class Command {
	
	public static HashMap<String,Command> intialize(){
		HashMap<String, Command> table = new HashMap<String,Command>();
		
		//add new commands here
		table.put("go", new Command(){public void run(String arg[]){go(arg);}});
		table.put("attack", new Command(){public void run(String arg[]){attack(arg);}});
		return table;
	}
	
	public abstract void run(String arg[]);
	
	//All command functions implemeneted below
	static void go(String arg[]){
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
	
	static void attack(String arg[]){
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
}
