package src.pages;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JLabel;

import src.PageManager;
import src.utils.DataStorager;
import src.utils.Utils;

public class Loading extends PageBase {

    public Loading() throws IOException {
        super();
        String className = Utils.getClassName(this);
        Initialization(className);
    }

    @Override
    public void pageComponentInitialize() throws IOException {
        BufferedImage mt_bi = DataStorager.getImage("MAIN_TITLE");
        HashMap<String, Double> mt_map = Utils.getImageOffset(
                DataStorager.APP_WIDTH / 2, 0.5 * (DataStorager.APP_HEIGHT / 2), mt_bi);
        JLabel mainTitle = frame.setBackground(mt_bi, mt_map.get("width"), mt_map.get("height"));
        PageManager.addComponent(pageName, "mainTitle", mainTitle);
    }
}