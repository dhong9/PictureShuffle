package helpers;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A generic helper file
 * @author Daniel Hong
 *
 */
public class Helper {
	
	/**
	 * Generate a random integer between min (inclusive) and max (exclusive)
	 * @param min the lower bound
	 * @param max the upper bound
	 * @return
	 */
	public static int randInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max); // +1 to make max inclusive
	}

}
