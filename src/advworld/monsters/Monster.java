package advworld.monsters;

import advworld.player.*;

public abstract class Monster {
	protected Mob theMob;

	protected int HP, MP, level, expGiven;
	protected String name, attack, description;
	protected MobNode node;

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
