package advworld.player;

import java.util.*;

import advworld.level.Location;
import advworld.monsters.*;
import advworld.objects.Objects;

public abstract class Player {
	
	protected Party theParty;
	
	protected int HP_MAX, MP_MAX, HP, MP, level, exp; 
	
	protected String name;
	
	protected Vector<Objects> inventory = new Vector<Objects>();
	
	protected Objects holding;
	
	
	/**
	 * Does damage to specified monster m in the game. The value return by
	 * this function is time passed to do attack.
	 * @param m monster
	 * @return time passed
	 */
	public int attack (Monster m) {
		return level;
	}
	
	public void addItem(Objects item){
		inventory.add(item);
		System.out.println("Added " + item.getName() + " to inventory.");
	}
	
	public void removeItem(Objects item){
		inventory.remove(item);
		System.out.println("Removed " + item.getName() + " from inventory.");
	}
	
	public Vector<Objects> getInventory(){
		return inventory;
	}
	
	public int dawdle() {
		// do something to theParty.location;
		return 0;
	}
	
	public int waiting() {
		// do something to theParty.location;
		return 0;
	}
	
	public void status() {
		System.out.println("Level: " + level);
		System.out.println("HP:    " + HP + "/" + HP_MAX);
		System.out.println("MP:    " + MP + "/" + MP_MAX);
		System.out.println("Exp:   " + exp);
	}
}
