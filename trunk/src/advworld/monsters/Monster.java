package advworld.monsters;

import advworld.Game;
import advworld.level.*;
import advworld.player.*;

// needs to be abstract class, temporarily set to regular class
public class Monster {
	protected int HP, HP_MAX,level, exp, freq, dam;
	protected String name, attack, description;
	protected MobNode node;
	protected Location loc;
	
	public Monster(String myName, String myAttack, int myLevel, int myHP, int damage,
			int expGiven, int frequency, String myDescription, MobNode myNode) {
		name = myName;
		attack = myAttack;
		level = myLevel;
		HP = HP_MAX = myHP;
		dam = damage;
		exp = expGiven;
		freq = frequency;
		description = myDescription;
		node = myNode;
	}

	public String getName(){
		return name;
	}
	
	public String getAttack(){
		return attack;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getHP(){
		return HP;
	}
	
	/**
	 * returns false if monster's new hp is negative or zero
	 * @param newHP
	 * @return
	 */
	public boolean setHP(int newHP){
		HP = newHP;
		return newHP > 0;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getExp(){
		return exp;
	}
	
	public int getFreq(){
		return freq;
	}
	
	public int getDam(){
		return dam;
	}
	
	public Location getLoc(){
		return loc;
	}
	
	public void addMonster(Location loc){
		this.loc = loc;
		loc.addMonster(this);
	}
	
	public void removeMonster(Location loc){
		loc.removeMonster(this);
	}
	/**
	 * Does damage to specified player p in the game. The value return by
	 * this function is time passed to do attack.
	 * @param p player
	 * @return time passed
	 */
	public void attack (Player p) {
		System.out.println(name + " " + attack + " you!");
		int newHP = p.getHP() - dam;
		if(p.setHP(newHP)){
			System.out.println("You loss " + dam + " HP.");
		} else {
			System.out.println(name + " killed you!");
			Game.gameover();
		}
	}
	
	public void status() {
		System.out.println("Name:  " + name);
		System.out.println("Level: " + level);
		System.out.println("HP:    " + HP + "/" + HP_MAX);
		System.out.println("Description:   " + description);
	}
}
