package helpers;

import java.util.concurrent.ThreadLocalRandom;

public class Helper {
	
	public static int randInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max); // +1 to make max inclusive
	}

}
