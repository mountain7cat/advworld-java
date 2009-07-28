package advworld.monsters;

public class Squirrel extends Monster {
	public Squirrel() {
//      name     attack   level hp  damage  exp  frequency  description
//   {"squirrel","gnaw",  3,    13, 4,      45,  1,         "grotesque, irradiated squirrel"}
		
		super("squirrel", "bites", 3, 8, 4, 45, 1, "grotesque, irradiated squirrel", null);
	}
}
