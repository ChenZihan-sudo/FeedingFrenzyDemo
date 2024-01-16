package src.pages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;

import src.framework.DataStorager;
import src.framework.PageBase;
import src.framework.PageManager;
import src.framework.components.MaskLayer;
import src.game.GameDriver;
import src.utils.Utils;

public class GameMain extends PageBase {

    public static String pageName = "GameMain";
    public static Boolean gameLaunched = false;

    public GameMain() throws IOException, InterruptedException {
        super();
        String className = Utils.getClassName(this);
        Initialization(className);
    }

    @Override
    public void pageComponentInitialize() throws IOException {

        // Main background
        JLabel mainBackground = frame.setBackground(DataStorager.getImage("MAIN_BG1"), 0, 0);
        PageManager.addComponent(0, pageName, "mainBackground", mainBackground);

        // Return to index
        JButton returnIndexBtn = frame.setImageButton(
                DataStorager.getImage("RETURN_BTN"),
                DataStorager.getImage("RETURN_PRESS_BTN"),
                0, 550, // *
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PageManager.switchPageTo("Index");
                    }
                });
        PageManager.addComponent(1, pageName, "returnIndexBtn", returnIndexBtn);

        // Mask layer
        MaskLayer maskLayer = frame.setMaskLayer();
        PageManager.addComponent(2, pageName, "maskLayer", maskLayer);
    }

    @Override
    public void pageReleaser() {
        GameDriver.removeGameDriver();
    }

    public static void gameLaunch() throws IOException {

        // Disable mask layer
        MaskLayer maskLayer = (MaskLayer) PageManager.getComponent(pageName, "maskLayer");
        maskLayer.setVisible(false);

        gameLaunched = true;

    }

    public static void main(String[] args) {

    }
}
