package src.utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Utils {

    public static HashMap<String, Double> getImageOffset(double x, double y, BufferedImage image) {
        HashMap<String, Double> map = new HashMap<>();

        double width = x - image.getWidth() / 2;
        double height = y - image.getHeight() / 2;
        map.put("width", width);
        map.put("height", height);
        return map;
    }

    public static String getClassName(Object obj){
        return obj.getClass().getSimpleName();
    }

    public static BufferedImage revertImage(BufferedImage image) {
        // Create a new image
        BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // Horizontal flip
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                flippedImage.setRGB(image.getWidth() - x - 1, y, pixel);
            }
        }

        return flippedImage;
    }
}
