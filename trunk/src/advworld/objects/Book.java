package advworld.objects;

import java.util.HashMap;

import advworld.level.Location;
import advworld.util.Command;

public class Book extends Thing{

	private String contents;
	
	public Book(String name, Location loc, String desc){
		super(name, loc);
		this.setDescription(desc);
		pickUpAble = true;
		equip_able = false;
		type = "Book";
		contents = "Empty";
	}
	
	public Book(String name, Location loc, String desc, String contents){
		super(name,loc);
		this.setDescription(desc);
		this.contents = contents;
		pickUpAble = true;
		type = "Book";
		equip_able = false;
	}
	
	public String getContents(){
		return contents;
	}
	
}
