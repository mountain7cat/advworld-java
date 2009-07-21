package advworld.monsters;

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
