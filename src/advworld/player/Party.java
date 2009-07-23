package advworld.player;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
	
	public boolean addToParty(Player p) {
		for (int i = 1; i < sizeofActives; i++)
			if (actives[i] == null) {
				actives[i] = p;
				break;
			}
		
		return members.add(p);
	}
	
	public boolean setStaticLeaderFlag (boolean flag) {
		boolean previous = leaderStatic;
		leaderStatic = flag;
		return previous;
	}
	
	public Player leader() {
		return actives[0];
	}
	
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
	
	public boolean member(Player p) {
		return members.contains(p);
	}
	
	public int posInActiveParty(Player p) {
		for (int i = 0; i < sizeofActives; i++) {
			if (actives[i].equals(p))
				return i;
		} return noPos;
	}
	
	public Iterator<Player> activesIterator() {
		return new Iterator<Player>() {
			private int next = 0;
			public boolean hasNext() {
				return next != sizeofActives || actives[next] != null;
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
	
}
