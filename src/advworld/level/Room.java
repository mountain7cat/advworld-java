package advworld.level;

import java.util.*;

public interface Room {
	
	public void action(String command);
	
	public String description();
	
	public HashMap<String, Room> exits();
	
}
