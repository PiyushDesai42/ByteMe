package algo;
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class NavMesh {
    private boolean[][] grid;
    private int width, height;
 
    public NavMesh(String imagePath) {
        try {
            // Load the image
        BufferedImage image = ImageIO.read(new File(imagePath));
        width = image.getWidth();
        height = image.getHeight();
        grid = new boolean[height][width];
 
        // Define walkable and blocked pixels
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                // Example: consider white pixels as walkable, black as blocked
                if (isWalkable(rgb)) {
                    grid[y][x] = true;  // walkable
                } else {
                    grid[y][x] = false; // blocked
                }
            }
        }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
 
    private boolean isWalkable(int rgb) {
        // Define a pixel as walkable based on its color
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
 
        // For example, walkable if the pixels are of road
        return r < 170 && g < 170 && b < 170;  // Adjust this condition based on your needs
    }
 
    public boolean isWalkable(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;  // out of bounds
        }
        return grid[y][x];
    }
 
    public int getWidth() {
        return width;
    }
 
    public int getHeight() {
        return height;
    }
}