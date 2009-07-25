package things;

import advworld.level.Location;

public class Book extends Thing{

	private String myName;
	private Location myLocation;
	private String myDescription;
	
	private String contents;
	
	public Book(){
		super();
	}
	
	public Book(String name, Location loc, String desc){
		super();
		contents = "empty";
	}
	
	public Book(String name, Location loc, String desc, String cont){
		super();
		contents = cont;
	}
	
	// still not sure how to control commands maybe use look command to prompt to new commands?
	public String action(String command){
		if(command.equals("read")){
			return contents;
		} else {
			return "Incorrect command";
		}
	}
}
