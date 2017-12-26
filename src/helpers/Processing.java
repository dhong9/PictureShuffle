package helpers;

import processing.core.PApplet;

/**
 * Contains additional functionalities for the Processing library
 * @author Daniel Hong
 *
 */
public class Processing {
	
	public static int rand(PApplet canvas, int high) {
		return (int) canvas.random(high);
	}

}
