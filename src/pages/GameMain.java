package src.pages;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import src.framework.DataStorager;
import src.framework.PageBase;
import src.framework.PageManager;
import src.framework.components.ComponentInfo;
import src.framework.components.MaskLayer;
import src.game.Fish;
import src.game.GameDriver;
import src.game.MouseMotionCallback;
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
    public void pageInitialize() throws IOException {

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
        PageManager.addComponent(100, pageName, "returnIndexBtn", returnIndexBtn);

        // Mask layer
        MaskLayer maskLayer = frame.setMaskLayer();
        PageManager.addComponent(200, pageName, "maskLayer", maskLayer, new ComponentInfo(false));

        // Background item
        JLabel back_item_1 = frame.setBackground(DataStorager.getImage("MAIN_BG1_1"), 0, 470);
        PageManager.addComponent(5, pageName, "backItem1", back_item_1);
        JLabel back_item_2 = frame.setBackground(DataStorager.getImage("MAIN_BG1_2"), 520, 540);
        PageManager.addComponent(5, pageName, "backItem2", back_item_2);
        JLabel back_item_3 = frame.setBackground(DataStorager.getImage("MAIN_BG1_3"), 900, 464);
        PageManager.addComponent(5, pageName, "backItem3", back_item_3);
    }

    BufferedImage fishImage = DataStorager.getImage("FISH_SELF0", "FISH_EAT0");
    Fish fishSelf = null;

    @Override
    public void pageLoad() throws IOException {

        System.out.println("game page loaded.");

        GameDriver.loadGameDriver();

        // Set self fish motion
        GameDriver.setMotionCb(new MouseMotionCallback() {
            @Override
            public void call(Point p) {
                fishSelf.setFishPosition(p);
            }
        });
        gameLaunch();
    }

    // JLabel fishLabel;
    // BufferedImage fishImage = DataStorager.getImage("FISH_SELF0", "FISH_EAT0");
    // int width = fishImage.getWidth() / 2;
    // int height = fishImage.getHeight() / 2;

    // public void setFishPosition(Point p) {
    // fishLabel.setBounds((int) p.getX(), (int) p.getY() - 27, fishImage.getWidth()
    // / 2, fishImage.getHeight() / 2);
    // fishLabel.repaint();
    // }

    public void gameLaunch() throws IOException {
        gameLaunched = true;

        fishSelf = new Fish("fishSelf", fishImage);

        final int oppsiteFish = 100;

        // fishLabel = frame.setLabelPaint(new LabelPaintCallback() {
        // @Override
        // public void call(Graphics g) throws IOException {
        // g.drawImage(fishImage, 0, 0, width, height, null);
        // }
        // });

        // PageManager.addComponent(10, pageName, "fishLabel", fishLabel);
    }

    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void pageRelease() {
        gameLaunched = false;
        GameDriver.removeGameDriver();
    }

    public static void main(String[] args) {
    }
}
