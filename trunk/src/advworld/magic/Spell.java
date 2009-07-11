package advworld.magic;

import java.io.*;
import java.util.*;

public class Spell {

	private static HashMap<String, Spell> spells;
	
	private Spell() {
		// set up stuff
	}
	
	/**
	 * Take the File object and parses the information to create a global map of
	 *  spells indexed by name.
	 * @param file
	 */
	public void createSpells(File file) {
		
	}
	
	public HashMap<String, Spell> spells() {
		return spells;
	}
	
}
