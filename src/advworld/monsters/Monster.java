package advworld.monsters;

import advworld.level.*;
import advworld.player.*;

public abstract class Monster {
	protected int HP, level, exp, freq, dam;
	protected String name, attack, description;
	protected MobNode node;
	
	public Monster(String myName, String myAttack, int myLevel, int myHP, int damage,
			int expGiven, int frequency, String myDescription, MobNode myNode) {
		name = myName;
		attack = myAttack;
		level = myLevel;
		HP = myHP;
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
	
	public void addMonster(Location loc){
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
	public int attack (Player p) {
		return level;
	}
}
