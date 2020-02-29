import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Demo to rasterize an image and change pixel color values
 * 
 * @author ravi
 *
 */
public class PixelManipDemo {

	public static void main(String[] args) throws Exception {
	
		// You might need to change file paths!
		BufferedImage img = colorImage(ImageIO.read(new File("../images/lion-grscr.jpg")));
		ImageIO.write(img, "png", new File("modified-lion.png"));
		
	}

	
	private static BufferedImage colorImage(BufferedImage image) {
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		WritableRaster raster = image.getRaster(); // Rasterize!

		// Iterate over the image dimensions
		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				
				// Get each pixel
				int[] pixels = raster.getPixel(xx, yy, (int[]) null);
				
				// Check if each pixel's RGB is within the green threshold
				if(     pixels[0] >= 0 && pixels[0] <= 80 && 
						pixels[1] >= 100 && pixels[1] <= 255 &&
						pixels[2] >= 0 && pixels[2] <= 255) {
					
					// Change all green pixels to black!
					pixels[0] = 0;
					pixels[1] = 0;
					pixels[2] = 0;

				}
				
				// Set the raster's pixel value
				raster.setPixel(xx, yy, pixels);
			}
		}
		return image;
	}
}
