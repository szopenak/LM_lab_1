package main;

import java.util.ArrayList;
import java.util.List;

public class State {
	private List<Integer> paths;
	private int id;;

	public State(int one, int two, int five) {
		// TODO Auto-generated constructor stub
		paths = new ArrayList<Integer>();
		paths.add(one);
		paths.add(two);
		paths.add(five);
	}

	@Override
	public String toString() {
		return "State"+ String.valueOf(id);
	}
	int nextState(int coin){
		int result = 0;
		switch (coin) {
		case 1:
			result = paths.get(0);
			break;
		case 2:
			result = paths.get(1);
			break;
		case 5:
			result = paths.get(2);
			break;
		default:
			result = Integer.MAX_VALUE;
			System.out.println("ERROR! Wrong coin passed!");
			break;
		}
		return result;
	}
		
	public void setId (int id) {
		this.id=id;
	}
}
