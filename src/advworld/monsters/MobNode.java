package advworld.monsters;

public class MobNode {
	private Monster monster;
	private MobNode next, prev;
	
	public MobNode() {
		monster = null;
		next = null;
		prev = null;
	}
	
	public MobNode(Monster m) {
		monster = m;
		next = null;
		prev = null;
	}
	
	public Monster getMonster() {
		return monster;
	}
	
	public MobNode next() {
		return next;
	}
	
	public MobNode prev() {
		return prev;
	}
}
