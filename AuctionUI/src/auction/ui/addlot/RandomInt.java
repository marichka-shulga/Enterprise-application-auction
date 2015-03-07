package auction.ui.addlot;

import java.util.Random;

public abstract class RandomInt {
	
	private static final int MIN = 500;
	private static final int MAX = 10000;
	
	public static int randInt() {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((MAX - MIN) + 1) + MIN;
	    return randomNum;
	}

}
