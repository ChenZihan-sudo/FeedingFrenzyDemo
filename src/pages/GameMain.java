package src.pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;

import src.PageManager;
import src.utils.DataStorager;
import src.utils.Utils;

public class GameMain extends PageBase {

    public GameMain() throws IOException {
        super();
        String className = Utils.getClassName(this);
        Initialization(className);
    }

    @Override
    public void pageComponentInitialize() throws IOException {

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
        PageManager.addComponent(pageName, "returnIndexBtn", returnIndexBtn);

        // Main background
        JLabel mainBackground = frame.setBackground(DataStorager.getImage("MAIN_BG1"), 0, 0);
        PageManager.addComponent(pageName, "mainBackground", mainBackground);
    }

    public static void main(String[] args) {

    }
}
