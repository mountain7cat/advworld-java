package advworld.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import advworld.level.Location;

public class StandardPlayer extends Player {
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public StandardPlayer() {
		try {
			System.out.println("What is your name?");
			String s = br.readLine();
			name = s;
			System.out.println("Hi " + s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		level = 1;
		HP_MAX = HP = 10;
		MP_MAX = MP= 0;
		exp = 0;
	}
	
	public void status() {
		System.out.println("Name:  " + name);
		super.status();
		// do other stuff
	}

}
