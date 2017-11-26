package helpers;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Contains additional functionalities for the Processing library
 * @author Daniel Hong
 *
 */
public class Processing {
	
	public static PImage lightenImage(PApplet canvas, PImage image, float amount) {
		int dimension = image.width * image.height;
		image.loadPixels();
		for (int i = 0; i < dimension; i++) {
			int color = image.pixels[i];
			
			float red = canvas.red(color);
			float green = canvas.green(color);
			float blue = canvas.blue(color);
			
			red += amount;
			green += amount;
			blue += amount;
			
			red = red > 255 ? 255 : red;
			green = green > 255 ? 255 : green;
			blue = blue > 255 ? 255 : blue;
			
			image.pixels[i] = canvas.color(red, green, blue);
		}
		canvas.updatePixels();
		return image;
	}

}
