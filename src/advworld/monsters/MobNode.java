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
	 * monster() returns the Monster in this MobNode.
	 * 
	 * @return the Monster in the MobNode.
	 */
	public Monster monster() {
		return monster;
	}
	
	/**
	 * next() returns the next field of this MobNode.
	 * 
	 * @return the next MobNode.
	 */
	public MobNode next() {
		return next;
	}
	
	/**
	 * setNext() sets the next field of this MobNode.
	 */
	public void setNext(MobNode node) {
		next = node;
		node.prev = this;
	}
	
	/**
	 * prev() returns the prev field of this MobNode.
	 * 
	 * @return the previous MobNode.
	 */
	public MobNode prev() {
		return prev;
	}
	
	/**
	 * setPrev() sets the prev field of this MobNode.
	 */
	public void setPrev(MobNode node) {
		prev = node;
		node.next = this;
	}
}
