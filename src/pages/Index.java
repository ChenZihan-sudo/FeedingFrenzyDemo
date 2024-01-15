package src.pages;

import javax.swing.*;

import src.FeedingFrenzy;
import src.PageManager;
import src.SwingFramer;
import src.utils.DataManager;
import src.utils.DataStorager;
import src.utils.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Index extends PageBase {

    public Index() throws IOException {
        super();
        String className = Utils.getClassName(this);
        Initialization(className);
    }

    @Override
    public void pageComponentInitialize() throws IOException {

        // Main title
        BufferedImage mt_bi = DataStorager.getImage("MAIN_TITLE");
        HashMap<String, Double> mt_map = Utils.getImageOffset(
                DataStorager.APP_WIDTH / 2, 0.5 * (DataStorager.APP_HEIGHT / 2), mt_bi);
        JLabel mainTitle = frame.setBackground(mt_bi, mt_map.get("width"), mt_map.get("height"));
        PageManager.addComponent(pageName, "mainTitle", mainTitle);

        // Game start
        JButton gameStartBtn = frame.setImageButton(
                DataStorager.getImage("GAME_START_BTN"),
                DataStorager.getImage("GAME_START_BTN_PRESS"),
                470, 300, // *
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PageManager.switchPageTo("GameMain");
                    }
                });
        PageManager.addComponent(pageName, "gameStartBtn", gameStartBtn);

        // My achivement
        JButton achivementBtn = frame.setImageButton(
                DataStorager.getImage("ACHIVEMENT_BTN"),
                DataStorager.getImage("ACHIVEMENT_BTN_PRESS"),
                490, 400, // *
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Button touched");
                    }
                });
        PageManager.addComponent(pageName, "achivementBtn", achivementBtn);

        // Help team
        JButton helpTeamBtn = frame.setImageButton(
                DataStorager.getImage("HELP_TEAM_BTN"),
                DataStorager.getImage("HELP_TEAM_BTN_PRESS"),
                510, 470, // *
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("helpTeam touched.");
                    }
                });
        PageManager.addComponent(pageName, "helpTeamBtn", helpTeamBtn);

        // Main background
        JLabel mainBackground = frame.setBackground(DataStorager.getImage("MAIN_BG0"), 0, 0);
        PageManager.addComponent(pageName, "mainBackground", mainBackground);
    }

    public static void main(String[] args) throws IOException {

        // tests
        // DataStorager ds = new DataStorager();
        // BufferedImage mt_bi = DataStorager.getImage("MAIN_TITLE");
        // HashMap<String, Integer> mt_map = Utils.getImageOffset(0, 0, mt_bi);
        // System.out.println(mt_map.get("height"));
        // System.out.println(mt_map.get("width"));

        // JPanel panel = new JPanel();
        // panel.setBounds(100, 200, 150, 150);
        // panel.setForeground(null);
        // frame.add(panel);
    }

}