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

    // public static ratioPositionAdjuster(double x,double y){

    // }
}
