package advworld;
import java.io.*;

public class Game {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		String s = null;
		if (args.length != 0) {
			System.out.println(args.length);
			System.exit(1);
		}
		while(true){
			System.out.print("> ");
			try {
				s = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(s);
		}
	}
}
