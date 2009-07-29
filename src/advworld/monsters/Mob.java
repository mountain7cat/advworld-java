package advworld.monsters;

import java.util.*;

import advworld.util.Utility;

public class Mob {
	
	private Vector<Monster> mob;
	final static int MONSTER_RARITY = 5;
	
	/**
	 * Mob constructor. Takes a parameter m, which is a Monster.
	 * 
	 * @param m is a Monster that will be in the head MobNode.
	 */
	public Mob(Monster m) {
		this();
		mob.add(m);
	}
	
	/**
	 * Empty Mob constructor.
	 */
	public Mob() {
		mob = new Vector<Monster>();
	}
	
	/**
	 * returns whether or not this Mob is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return mob.size() == 0;
	}
	
	/**
	 * returns the number of Monsters in this Mob
	 * 
	 * @return int size
	 */
	public int num() {
		return mob.size();
	}
	
	/**
	 *  prints the names of all Monsters in this Mob
	 *  used for the "look" command
	 */
	public void print_monsters() {
		int size = mob.size();
		
		System.out.print("There ");
		if (size > 1) {
			System.out.print("are ");
		} else {
			System.out.print("is ");
		}
		System.out.print(size + " monster");
		if (size > 1) {
			System.out.println("s here:");
		} else {
			System.out.println(" here:");
		}
		
		Iterator<Monster> iter = mob.iterator();
		while(iter.hasNext())
			Utility.println(iter.next().name);
	}
	
	/**
	 * returns the first Monster in this Mob with given name. If no such Monster
	 * exists, return null.
	 * 
	 * @param name of Monster to be found
	 */
	public Monster find(String name) {
		Iterator<Monster> iter = mob.iterator();
		while(iter.hasNext()) {
			Monster m = iter.next();
			if (m.name == name)
				return m;
		}
		return null;
	}
	
	/**
	 * adds a randomly generated Monster to this Mob
	 * the random monster generation must generate monsters with relative
	 * frequencies proportional to the frequencies listed in monsters[] for each
	 * type. no monster with level greater than the player's current level should
	 * be generated.
	 */
	public void spawn() {
		/* // number of monster types
	int num_types = sizeof(monsters) / sizeof(monster_t);
	// monster count
	int count = 0;
	int i;
	for (i = 0; i < num_types; i++) { // take the frequency of monster into account
		if (monsters[i].level <= the_player.level) {
			count += monsters[i].frequency;
		}
	}
	
	int random_number = rand() % count;
	
	int j;
	for (j = 0; j < num_types; j++) {
		random_number = random_number - monsters[j].frequency;
		if (random_number < 0) {
			// allocate a new monster_t
			monster_t *new_monster = (monster_t *) malloc(sizeof(monster_t));
			
			new_monster->name = monsters[j].name;
			new_monster->attack = monsters[j].attack;
			new_monster->level = monsters[j].level;
			new_monster->hp = monsters[j].hp;
			new_monster->damage = monsters[j].damage;
			new_monster->exp = monsters[j].exp;
			new_monster->frequency = monsters[j].frequency;
			new_monster->description = monsters[j].description;
			
			append_monster(mob_handle, new_monster);
			return;
		}
	} */
	}
	
	/**
	 * adds a Monster m to this Mob
	 * 
	 * @param Monster m is the Monster to be added
	 */
	public void append(Monster m) {
		mob.add(m);
	}
	
	/**
	 * deletes the corresponding monster from this Mob. If the monster
	 * does not exist in this Mob, the function does nothing.
	 * 
	 * @param Monster m is the Monster to be deleted
	 */
	public void delete(Monster m) {
		mob.removeElement(m);
	}
	
}
