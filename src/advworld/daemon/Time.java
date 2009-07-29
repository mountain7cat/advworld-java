package advworld.daemon;

import java.util.*;

import advworld.Game;
import advworld.util.*;

public class Time implements Runnable{

	private static final int DAWN = 6;
	private static final int MORNING = 9;
	private static final int AFTERNOON = 12;
	private static final int DUSK = 18;
	private static final int NIGHT = 21;
	
	public void run() {
		Calendar calendar = Game.calendar;
		int minutes = 60;
		try {
			synchronized(calendar) {
				while (true) {
					calendar.wait(minutes * 1000);
					switch(calendar.get(Calendar.HOUR_OF_DAY)) {
						case DAWN:
							minutes = 60;
						case 7:
						case 8:
							minutes = (60 - calendar.get(Calendar.MINUTE))/2 + 1;
							System.out.println("It is dawn.");
							break;
						case MORNING:
							minutes = 60;
						case 10:
						case 11:
							minutes = (60 - calendar.get(Calendar.MINUTE))/2 + 1;
							System.out.println("It is morning.");
							break;
						case AFTERNOON:
							minutes = 60;
						case 13:
						case 14:
						case 15:
						case 16:
						case 17:
							minutes = (60 - calendar.get(Calendar.MINUTE))/2 + 1;
							System.out.println("It is afternoon.");
							break;
						case DUSK:
							minutes = 60;
						case 19:
						case 20:
							minutes = (60 - calendar.get(Calendar.MINUTE))/2 + 1;
							System.out.println("It is dusk.");
							break;
						case NIGHT:
							minutes = 60;
						case 22:
						case 23:
						case 0:
						case 1:
						case 2:
						case 3:
						case 4:
						case 5:
							minutes = (60 - calendar.get(Calendar.MINUTE))/2 + 1;
							System.out.println("It is night.");
							break;
						default:
							throw new AdvworldException();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
