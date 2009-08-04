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
			System.out.println("Go where?");
		} else if(arg.length == 2){
			Path p;
			String destination = arg[1];
			Utility.debug(Game.theParty.getLocation().getName());
			Iterator<Path> paths = Game.theParty.getLocation().exits().iterator();
			if(Game.theParty.getLocation().getName().equals(destination)){
				Utility.println("You are already at "+destination);
				Game.theParty.getLocation().description();
			} else {
				while(paths.hasNext()){
					p = paths.next();
					String out = "Out of " + p.getFrom().getName();
					String out1 = "out of " + p.getFrom().getName();
					if(Game.theParty.getLocation().getParent().exits().size() != 0 &&
							(destination.equals("out") || out.equals(destination) || out1.equals(destination)) ){
						Utility.println("You moved from " + p.getFrom().getName() + " to " + Game.theParty.getLocation().getParent().getName() + ".");
						Game.theParty.setLocation(Game.theParty.getLocation().getParent());
						Game.theParty.getLocation().description();
						return;
					} else if(p.getTo().getName().equals(destination) || p.getTo().getTypeName().equals(destination)){
						if(!p.getFrom().getName().equals(Game.theParty.getLocation().getName())){
							//shouldn't hit this case, where a path's from is not the current location
							Utility.debug("GO function - Malformed path: FIX IT!!!");
							return;
						} else if (p.isLocked()){
							System.out.println(p.getTo().lockedDescription());
							Game.theParty.getLocation().description();
							return;
						}
						//perform moving function
						Game.theParty.setLocation(p.getTo());
						Utility.println("You moved from " + p.getFrom().getName() + " to " + p.getTo().getName() + ".");
						Game.theParty.getLocation().description();
						return;
					}
				}
				Utility.println(destination + " is not an exit.");
				Game.theParty.getLocation().description();
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
			System.out.println("Attack who?");
		} else {
			Iterator<Monster> iter = Game.theParty.getLocation().getMyMonsters().iterator();
			String target = arg[1];
			while(iter.hasNext()){
				Monster mon = (Monster) iter.next();
				if(target.equals(mon.getName()) || target.equals(mon.getDescription())){
					Game.theParty.getLeader().attack(mon);
					return;
				}
			}
			System.out.println("There is no " + target +" in this " + Game.theParty.getLocation().getType() + ".");
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
			Iterator<Monster> iter = Game.theParty.getLocation().getMyMonsters().iterator();
			String target = arg[1];
			while(iter.hasNext()){
				Monster mon = iter.next();
				if(target.equals(mon.getName())){
					mon.status();
					return;
				}
			}
			System.out.println("There is no " + target + " in this " + Game.theParty.getLocation().getType() + ".");
			return;
		}
	}	
	
	private static void help(String arg[]){
		Utility.debug("help called");
		if(arg.length == 1){
			System.out.println("List of Commands:");
			Set<String> treeSet = new TreeSet<String>();
			Set<String> s = table.keySet();
			Iterator<String> iter = s.iterator();
			while(iter.hasNext()){
				treeSet.add(iter.next());
			}
			for(String cmd : treeSet){
				System.out.println(" -" + cmd);
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
			Game.theParty.getLocation().description();
			if(Game.theParty.getLocation().hasItems())
				Game.theParty.getLocation().listItems();
			if(Game.theParty.getLocation().hasMonsters())
				Game.theParty.getLocation().listMonsters();
		} else {
			Utility.debug(arg[0]);
			Utility.debug(arg[1]);
			String[] tokens = arg[1].split(" ",2);
			if(tokens[0].equals("at") && tokens.length == 1){
				System.out.println("Look at what?");
			} else if(tokens[0].equals("at") && tokens[1].length() > 0){
				Iterator<Thing> iter = Game.theParty.getLocation().getMyObjects().iterator();
				String name = tokens[1];
				Utility.debug(name);
				Game.DEBUG = false;
				while(iter.hasNext()){
					Thing obj = (Thing)iter.next();
					if(obj.getName().equals(name)){
						System.out.println("~Name: " + obj.getName());
						System.out.println("~Type: " + obj.getType());
						System.out.println("~Description: " + obj.getDescription());
						return;
					}
				}
				System.out.println(name +" is not in this " + Game.theParty.getLocation().getType() + ".");
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
			Iterator<Thing> iter = Game.theParty.getLeader().getInventory().iterator();
			String title = arg[1];
			while(iter.hasNext()){
				Thing obj = (Thing)iter.next();
				if(obj.getName().equals(title)){
					if(obj.isType("Book")){
						Book book = (Book) obj;
						System.out.println(title + " reads...");
						System.out.println("     " + book.getContents());
						System.out.println("End of " + book.getName());
						return;
					} else {
						System.out.println(title + " is not a book.");
						return;
					}
				}
			}
			System.out.println("You do not have " + title +".");
			return;
		} else {
			System.out.println("Incorrect command.");
		}
	}
	
	private static void take(String arg[]){
		if(arg.length == 1){
			System.out.println("Take what?");
		} else if(arg.length > 1){
			Iterator<Thing> iter = Game.theParty.getLocation().getMyObjects().iterator();
			String objName = arg[1];
			while(iter.hasNext()){
				Thing obj = (Thing)iter.next();
				if(obj.getName().equals(objName)){
					if(obj.isPickUpAble()){
						Game.theParty.getLeader().addItem(obj);
						Game.theParty.getLocation().removeObject(obj);
						return;
					} else {
						System.out.println("You can not pick up "  + objName + ".");
						return;
					}
				}
			}
			System.out.println(objName + " is not in this " + Game.theParty.getLocation() + ".");
			return;
		} else {
			System.out.println("Incorrect command.");
		}
	}
	
	private static void inventory(String arg[]){
		if(arg.length == 1){
			Iterator<Thing> iter = Game.theParty.getLeader().getInventory().iterator();
			if(!iter.hasNext()){
				System.out.println("Inventory: ");
			} else {
				System.out.print("Inventory: ");
				while(iter.hasNext()){
					Thing obj	= (Thing) iter.next();
					System.out.print(obj.getType() + ": " + obj.getName());
					System.out.print((iter.hasNext()?", ":"\n"));
				}
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
			Iterator<Thing> iter = Game.theParty.getLeader().getInventory().iterator();
			String objName = arg[1];
			while(iter.hasNext()){

				Thing obj = (Thing)iter.next();
				if(obj.getName().equals(objName)){
					Game.theParty.getLeader().removeItem(obj);
					Game.theParty.getLocation().addObject(obj);
					System.out.println("You dropped " + objName + " on the ground.");
					return;
				}
			}
			System.out.println("You do not have " + objName + " in your inventory.");
			return;
		} else {
			System.out.println("Incorrect command.");
		}
	}
	private static void println(Object msg) {
		Utility.println(msg);
	}
	
	
}
