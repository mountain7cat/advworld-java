package advworld.player;

import java.util.*;

import advworld.level.Location;
import advworld.monsters.*;
import advworld.objects.Objects;

public abstract class Player {
	
	protected Party theParty;
	
	protected int HP_MAX, MP_MAX, HP, MP, level, exp, damage; 
	
	protected String name;
	
	protected Vector<Objects> inventory = new Vector<Objects>();
	
	protected Objects holding;
	
	
	/**
	 * Does damage to specified monster m in the game. The value return by
	 * this function is time passed to do attack.
	 * @param m monster
	 * @return time passed
	 */
	public void attack (Monster m) {
		System.out.println("You attacked \"" + m.getName() + "\"!");
		int newHP = m.getHP() - damage;
		if(m.setHP(newHP)){
			System.out.println(m.getName() + " loses " + damage + " HP.");
			m.attack(this);
		} else {
			System.out.println("You killed the " + m.getName() + "!");
			m.getLoc().removeMonster(m);
			gainEXP(m.getExp());
		}
	}
	
	public void gainEXP(int gained){
		exp = exp + gained;
		System.out.println("You gained " + gained + " experience points!");
		if(exp / 10 > level-1){
			int lvl = exp / 10;
			gainlevel(lvl);
		}
	}
	
	private void gainlevel(int lvl){
		System.out.println("You gained " + lvl + " level.");
		level += lvl;
		System.out.println("You are now level "+level+".");
		HP_MAX = HP = level * 10;
		damage = level;
	}
	
	public int getHP(){
		return HP;
	}
	
	public boolean setHP(int newHP){
		HP = newHP;
		return newHP > 0;
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
