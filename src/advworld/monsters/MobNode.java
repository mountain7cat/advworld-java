package advworld.monsters;

public class MobNode {
	private Monster monster;
	private MobNode next, prev;

	/**
	 * MobNode constructor.
	 * 
	 * @param m is a Monster.
	 */
	public MobNode(Monster m) {
		monster = m;
		next = null;
		prev = null;
	}
	
	/**
	 * Null constructor.
	 */
	public MobNode() {
		this(null);
	}
	
	/**
	 * monster() returns the Monster in the MobNode.
	 * 
	 * @return the Monster in the MobNode.
	 */
	public Monster monster() {
		return monster;
	}
	
	/**
	 * next() returns the next MobNode.
	 * 
	 * @return the next MobNode.
	 */
	public MobNode next() {
		return next;
	}
	/**
	 * prev() returns the previous MobNode.
	 * 
	 * @return the previous MobNode.
	 */
	public MobNode prev() {
		return prev;
	}
}
