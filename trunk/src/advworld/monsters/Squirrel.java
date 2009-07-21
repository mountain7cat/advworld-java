package advworld.monsters;

public class Squirrel extends Monster {
	public Squirrel() {
//      name     attack   level hp  damage  exp  frequency  description
//   {"squirrel","gnaw",  3,    13, 5,      45,  1,         "grotesque, irradiated squirrel"}
		
		super("squirrel", "gnaw", 3, 13, 5, 45, 1, "grotesque, irradiated squirrel", null);
	}
}
