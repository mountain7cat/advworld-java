package advworld.util;

import advworld.player.Player;
import java.util.HashMap;
import java.util.Map;

public abstract class Command {
	
	public static HashMap<String,Command> intialize(){
		HashMap<String, Command> table = new HashMap<String,Command>();
		
		//add new commands here
		table.put("go", new Command(){public void run(String arg[]){go(arg);}});
		
		return table;
	}
	
	public abstract void run(String arg[]);
	
	//All command functions implemeneted below
	static void go(String arg[]){
		System.out.println("go called");
		for(int i = 0; i < arg.length ; i++){
			System.out.print(arg[i] + " ");
		}
		System.out.println();
	}
}
