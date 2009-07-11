package advworld.player;

public class StandardPlayer extends Player {
	
	

	public StandardPlayer() {
		level = 1;
		HP_MAX = HP = 10;
		MP_MAX = MP= 0;
		exp = 0;
	}
	
	public int status() {
		super.status();
		// do other stuff
		return 0;
	}

}
