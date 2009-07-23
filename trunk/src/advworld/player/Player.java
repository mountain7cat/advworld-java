package advworld.player;

import advworld.level.Location;
import advworld.monsters.*;

public abstract class Player {
	
	protected Party theParty;
	
	protected int HP_MAX, MP_MAX, HP, MP, level, exp; 
	
	
	/**
	 * Does damage to specified monster m in the game. The value return by
	 * this function is time passed to do attack.
	 * @param m monster
	 * @return time passed
	 */
	public int attack (Monster m) {
		return level;
	}
	
	public int dawdle() {
		// do something to theParty.location;
		return 0;
	}
	
	public int waiting() {
		// do something to theParty.location;
		return 0;
	}
	
	public int status() {
		System.out.println("Level: " + level);
		System.out.println("HP:    " + HP + "/" + HP_MAX);
		System.out.println("MP:    " + MP + "/" + MP_MAX);
		System.out.println("Exp:   " + exp);
		return 0;
	}
}
