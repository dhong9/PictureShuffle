package helpers;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A generic helper file
 * @author Daniel Hong
 *
 */
public class Helper {
	
	public static int randInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max); // +1 to make max inclusive
	}
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
