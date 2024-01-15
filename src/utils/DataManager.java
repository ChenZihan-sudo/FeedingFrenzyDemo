package src.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

class ImageData {

    // String resourceName;
    private String resourcePath;
    private Boolean isFullImage = true;
    private Hashtable<String, ArrayList<Integer>> croppingData = new Hashtable<>();

    public ImageData(String resourcePath, Boolean isFullImage) {
        this.resourcePath = resourcePath;
        this.isFullImage = isFullImage;
    }

    public ImageData(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * @param name cropped image name
     * @param data x,y,w,h
     */
    void addCroppingData(String subImageName, int... datas) {
        ArrayList<Integer> croppedSizes = new ArrayList<Integer>();
        for (Integer data : datas) {
            croppedSizes.add(data);
        }
        croppingData.put(subImageName, croppedSizes);
    }

    ArrayList<Integer> getCroppingData(String subImageName) {
        return croppingData.get(subImageName);
    }

    BufferedImage ImageCropper(String subImageName) throws IOException {
        BufferedImage image = ImageIO.read(new File(resourcePath));
        ArrayList<Integer> croppingData = getCroppingData(subImageName);
        if (croppingData == null || croppingData.size() != 4)
            return null;
        BufferedImage croppedImage = image.getSubimage(
                croppingData.get(0),
                croppingData.get(1),
                croppingData.get(2),
                croppingData.get(3));
        return croppedImage;
    }

    /** Will Get Full Image */
    BufferedImage getImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(resourcePath));
        return image;
    }

    /** Will Get Sub Image */
    BufferedImage getImage(String subImageName) throws IOException {
        if (isFullImage)
            return getImage();
        return ImageCropper(subImageName);
    }

}

public class DataManager {

    static protected Hashtable<String, ImageData> ImageDB = new Hashtable<>();

    protected static void addImageResource(String resourceName, ImageData imageData) {
        ImageDB.put(resourceName, imageData);
    }

    protected static ImageData getImageResource(String resourceName) {
        return ImageDB.get(resourceName);
    }

    // static public String INDEX_BACKGROUND =

    // static void LoadResource() {
    // ArrayList<ImageCroppedData> data = new ArrayList<ImageCroppedData>();
    // }
}
