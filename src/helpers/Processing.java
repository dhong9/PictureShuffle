package helpers;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Contains additional functionalities for the Processing library
 * @author Daniel Hong
 *
 */
public class Processing {
	
	/**
	 * Lightens (or darkens) the image by a specified amount
	 * @param canvas
	 * @param image the image to fade
	 * @param amount how much light to add
	 * @return
	 */
	public static PImage lightenImage(PApplet canvas, PImage image, float amount) {
		int dimension = image.width * image.height; // Get image dimension
		image.loadPixels();
		for (int i = 0; i < dimension; i++) {
			int color = image.pixels[i];
			
			// Get RGB values from the color
			float red = canvas.red(color);
			float green = canvas.green(color);
			float blue = canvas.blue(color);
			
			// Fade the image by amount
			red += amount;
			green += amount;
			blue += amount;
			
			// If the image is already white, then leave it
			red = red > 255 ? 255 : red;
			green = green > 255 ? 255 : green;
			blue = blue > 255 ? 255 : blue;
			
			image.pixels[i] = canvas.color(red, green, blue);
		}
		canvas.updatePixels(); // Update the image
		return image;
	}

}
