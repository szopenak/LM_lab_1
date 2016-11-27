package main;



import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import main.State;
public class Automat {
	
	static List<State> states = new ArrayList<State>();
	static Scanner reader = new Scanner(System.in);
	static int actualState = 0;
	static StringBuilder history = new StringBuilder();
	
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new ShutDownHook(reader));
		// TODO Auto-generated method stub
		populateStates();
		System.out.println("Insert a coin 1, 2, 5 or type 'quit' to exit");
		
		while (true) {

			int coin = getNextCoin();

			history.append(String.valueOf(coin)+", ");
			int x = states.get(actualState).nextState(coin);
			
			actualState=x;
			
			System.out.println("State "+String.valueOf(x) );
			if (actualState == 7) {
				System.out.println("Coins were thrown : "+history);
				System.out.println("OK, 7 PLN thrown");
			}
			else if (actualState == 0) {
				System.out.println("Too much! Giving back your money");
				history = new StringBuilder();
				System.out.println("Insert a coin 1, 2 or 5");
			}
			else {
				System.out.println("Coins thrown : "+history);
				System.out.println("Insert a coin 1, 2 or 5");
			}	
		}	
	}
	
	static void populateStates() {
		states.add(new State(1,2,5));
		states.add(new State(2,3,6));
		states.add(new State(3,4,7));
		states.add(new State(4,5,0));
		states.add(new State(5,6,0));
		states.add(new State(6,7,0));
		states.add(new State(7,0,0));
		states.add(new State(0,0,0));

		for (int i=0; i<8; i++) {
			states.get(i).setId(i);
		}
	}
	
	
	static class ShutDownHook extends Thread {
		private Scanner sc;
		public void run() {
			sc.close();
			System.out.println("System terminated!");
		}
		public ShutDownHook (Scanner sc) {
			this.sc = sc;
		}
	}
	
	static int getNextCoin() {
		boolean valid = false;
		int read = 0;
		while (!valid) {
			if (reader.hasNextInt()) {
				read = reader.nextInt();
				if ((read == 1)|(read==2)|(read==5)) {
					valid = true;
				}
				else {
					System.out.println("Wrong coin!");
					continue;
				}
				
			}
			else if (reader.hasNext()) {
				String var = reader.next();
				if (var.equals("quit")) {System.exit(0);}
					System.out.println("Non-integer passed!");
					continue;
			}

		}
		return read;
	}
	
}

