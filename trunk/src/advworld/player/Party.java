package advworld.player;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import advworld.Game;
import advworld.level.*;
import advworld.util.AdvworldException;
import advworld.util.Utility;

public class Party {

	private Location location;
	private Set<Player> members;
	private Player[] actives;
	private boolean leaderStatic;
	
	private static final int sizeofActives = 3;
	private static final int noPos = -1;
	
	/**
	 * Party Constructor
	 * 
	 * @param leader
	 * @param location
	 */
	public Party(Player leader, Location location) {
		this.location = location;
		members = new HashSet<Player>();
		members.add(leader);
		actives = new Player[sizeofActives];
		actives[0] = leader;
		for (int i = 1; i < sizeofActives; i++)
			actives[i] = null;
		leaderStatic = true;
	}
	
	/**
	 * Add the Player p, to the party
	 * @param p
	 * @return false if member already exist
	 */
	public boolean addToParty(Player p) {
		for (int i = 1; i < sizeofActives; i++)
			if (actives[i] == null) {
				actives[i] = p;
				break;
			}
		
		return members.add(p);
	}
	
	/**
	 * Change the staticleader flag
	 * @param flag
	 * @return
	 */
	public boolean setStaticLeaderFlag (boolean flag) {
		boolean previous = leaderStatic;
		leaderStatic = flag;
		return previous;
	}
	
	/**
	 * returns the party leader
	 * @return
	 */
	public Player getLeader() {
		return actives[0];
	}
	
	/**
	 * returns the party's location
	 * @return
	 */
	public Location getLocation(){
		return location;
	}
	
	public Set<Player> getMembers(){
		return members;
	}
	
	public Player[] getActives(){
		return actives;
	}
	/**
	 * Changes the party's location to loc
	 * @param loc
	 */
	public void setLocation(Location loc){
		Utility.debug("loc name " + loc.getName() + " exits size " + loc.exits().size() + "exits " + loc.exits().toString());
		if(loc.exits().size() == 2){
			Utility.debug("loc.exits().get(0).getTo().getName is " +loc.exits().get(0).getTo().getName());
			Utility.debug("loc.exits().get(1).getTo().getName is " +loc.exits().get(1).getTo().getName());
			if(loc.exits().get(0).getTo().getName().equals(location.getName())){
				Utility.debug(" location = loc.exits().get(1).getTo()");
				location = loc.exits().get(1).getTo();
				return;
			} else if (loc.exits().get(1).getTo().getName().equals(location.getName())){
				Utility.debug(" location = loc.exits().get(0).getTo()");
				location = loc.exits().get(0).getTo();
				return;
			}
		} else {
			Utility.debug("location = loc");
			location = loc;
		}
	}
	/**
	 * changes the player p to the a new position
	 * @param p
	 * @param position
	 * @return
	 * @throws AdvworldException
	 */
	public boolean setMemberToActivePos(Player p, int position) throws AdvworldException {
		if (member(p)) {
			int pos = posInActiveParty(p);
			if ((position == 0 || pos == 0) && leaderStatic) {
				println("Cannot change leader");
				return false;
			}
			
			if (pos == noPos) {
				actives[position] = p;
			} else if (position != pos) {
				Player temp = actives[position];
				actives[position] = p;
				actives[pos] = temp;
			}
			return true;
		} else throw new AdvworldException();
	}
	
	/**
	 * return true if player is part of the party
	 * @param p
	 * @return
	 */
	public boolean member(Player p) {
		return members.contains(p);
	}
	
	/**
	 * returns the position in the party of the player p
	 * @param p
	 * @return
	 */
	public int posInActiveParty(Player p) {
		for (int i = 0; i < sizeofActives; i++) {
			if (actives[i].equals(p))
				return i;
		} return noPos;
	}
	
	/**
	 * Creates a player iterator
	 * @return
	 */
	public Iterator<Player> activesIterator() {
		return new Iterator<Player>() {
			private int next = 0;
			public boolean hasNext() {
				return next != sizeofActives && actives[next] != null;
			}

			public Player next() {
				int current = next;
				next++;
				return actives[current];
			}

			public void remove() {
				for (int i = next - 1; i < sizeofActives - 1; i++) {
					actives[i] = actives[i+1];
				}
				actives[sizeofActives - 1] = null;
			}
			
		};
	}

	private static void println(Object msg) {
		Utility.println(msg);
	}
	
	public boolean getLeaderStatic(){
		return leaderStatic;
	}
}
