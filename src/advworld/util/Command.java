package advworld.util;

import java.io.*;
import java.util.*;
import advworld.Game;
import advworld.level.Location;
import advworld.level.Path;
import advworld.monsters.Monster;
import advworld.objects.*;
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
		table.put("read", new Command(){public void run(String arg[]){read(arg);}});
		table.put("take", new Command(){public void run(String arg[]){take(arg);}});
		table.put("inventory", new Command(){public void run(String arg[]){inventory(arg);}});
		table.put("drop", new Command(){public void run(String arg[]){drop(arg);}});
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
		if(arg.length == 1){
			System.out.println("Attack what?");
		} else {
			Iterator<Monster> iter = Game.theParty.getlocation().getMyMonsters().iterator();
			String target = arg[1];
			while(iter.hasNext()){
				Monster mon = (Monster) iter.next();
				if(target.equals(mon.getName())){
					Game.theParty.getleader().attack(mon);
					return;
				}
			}
			System.out.println("There is no \"" + target +"\" in this " + Game.theParty.getlocation().getType() + ".");
			return;
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
			Iterator<Monster> iter = Game.theParty.getlocation().getMyMonsters().iterator();
			String target = arg[1];
			while(iter.hasNext()){
				Monster mon = iter.next();
				if(target.equals(mon.getName())){
					mon.status();
					return;
				}
			}
			System.out.println("There is no " + target + " in this " + Game.theParty.getlocation().getType() + ".");
			return;
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
		Game.DEBUG = true;
		Utility.debug("look called" + arg.length);
		if(arg.length == 1){
			System.out.println(Game.theParty.getlocation().description());
			Game.theParty.getlocation().listItems();
			System.out.println("");
			Game.theParty.getlocation().listMonsters();
			System.out.println("");
		} else {
			Utility.debug(arg[0]);
			Utility.debug(arg[1]);
			String[] tokens = arg[1].split(" ",2);
			if(tokens[0].equals("at") && tokens.length == 1){
				System.out.println("Look at what?");
			} else if(tokens[0].equals("at") && tokens[1].length() > 0){
				Iterator<Objects> iter = Game.theParty.getlocation().getMyObjects().iterator();
				String name = tokens[1];
				Utility.debug(name);
				Game.DEBUG = false;
				while(iter.hasNext()){
					Objects obj = (Objects)iter.next();
					if(obj.getName().equals(name)){
						System.out.println("~Name: " + obj.getName());
						System.out.println("~Type: " + obj.getType());
						System.out.println("~Description: " + obj.getDescription());
						return;
					}
				}
				System.out.println("\"" + name +"\"" + " is not in this " + Game.theParty.getlocation().getType() + ".");
				return;
			} else {
				System.out.println("Incorrect Command");
			}
		}
	}
	
	private static void read(String arg[]){
		if(arg.length == 1){
			System.out.println("Read what?");
		} else if(arg.length > 1){
			Iterator<Objects> iter = Game.theParty.getleader().getInventory().iterator();
			String title = arg[1];
			while(iter.hasNext()){
				Objects obj = (Objects)iter.next();
				if(obj.getName().equals(title)){
					if(obj.isType("Book")){
						Book book = (Book) obj;
						System.out.println("\"" + title +"\"" + " reads...");
						System.out.println("     " + book.getContents());
						System.out.println("End of " + book.getName());
						return;
					} else {
						System.out.println("\"" + title +"\"" + " is not a book.");
						return;
					}
				}
			}
			System.out.println("You do not have " + "\"" + title +"\"" + ".");
			return;
		} else {
			System.out.println("Incorrect command.");
		}
	}
	
	private static void take(String arg[]){
		if(arg.length == 1){
			System.out.println("Read what?");
		} else if(arg.length > 1){
			Iterator<Objects> iter = Game.theParty.getlocation().getMyObjects().iterator();
			String objName = arg[1];
			while(iter.hasNext()){
				Objects obj = (Objects)iter.next();
				if(obj.getName().equals(objName)){
					if(obj.isPickUpAble()){
						Game.theParty.getleader().addItem(obj);
						Game.theParty.getlocation().removeObject(obj);
						return;
					} else {
						System.out.println("You can not pick up " + "\"" + objName +"\"" + ".");
						return;
					}
				}
			}
			System.out.println("\"" + objName +"\"" + " is not in this " + Game.theParty.getlocation() + ".");
			return;
		} else {
			System.out.println("Incorrect command.");
		}
	}
	
	private static void inventory(String arg[]){
		if(arg.length == 1){
			Iterator<Objects> iter = Game.theParty.getleader().getInventory().iterator();
			System.out.print("Inventory:");
			while(iter.hasNext()){
				System.out.print(iter.next().getType() + ": " + iter.next().getName());
				System.out.print((iter.hasNext()?", ":"\n"));
			}
			return;
		} else {
			System.out.println("Incorrect command.");
		}
	}
	
	private static void drop(String arg[]){
		if(arg.length == 1){
			System.out.println("Drop what?");
		} else if(arg.length > 1){
			Iterator<Objects> iter = Game.theParty.getleader().getInventory().iterator();
			String objName = arg[1];
			while(iter.hasNext()){
				Objects obj = (Objects)iter.next();
				if(obj.getName().equals(objName)){
					Game.theParty.getleader().removeItem(obj);
					Game.theParty.getlocation().addObject(obj);
					System.out.println("You dropped " + "\"" + objName +"\"" + " on the ground.");
					return;
				}
			}
			System.out.println("You do not have " + "\"" + objName +"\"" + " in your inventory.");
			return;
		} else {
			System.out.println("Incorrect command.");
		}
	}
	private static void println(Object msg) {
		Utility.println(msg);
	}
	
	
}
