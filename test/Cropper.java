package test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File; // Add missing import
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cropper {

    public static void main(String[] args) throws IOException {

        File file = new File("Resource/Texture/Splash/ttkp.00114.png");
        BufferedImage image = ImageIO.read(file);
        BufferedImage bufferedImage = image.getSubimage(450, 700, 568, 320);
        ImageIO.write(bufferedImage, "png", new File("./test.png"));
    }
}
