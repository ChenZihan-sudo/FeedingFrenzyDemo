package src.pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;

import src.framework.DataStorager;
import src.framework.PageBase;
import src.framework.PageManager;
import src.utils.Utils;

public class Index extends PageBase {

    public Index() throws IOException {
        super();
        String className = Utils.getClassName(this);
        Initialization(className);
    }

    @Override
    public void pageInitialize() throws IOException {

        // Main title
        BufferedImage mt_bi = DataStorager.getImage("MAIN_TITLE");
        HashMap<String, Double> mt_map = Utils.getImageOffset(
                DataStorager.APP_WIDTH / 2, 0.5 * (DataStorager.APP_HEIGHT / 2), mt_bi);
        JLabel mainTitle = frame.setBackground(mt_bi, mt_map.get("width"), mt_map.get("height"));
        PageManager.addComponent(1, pageName, "mainTitle", mainTitle);

        // Game start
        JButton gameStartBtn = frame.setImageButton(
                DataStorager.getImage("GAME_START_BTN"),
                DataStorager.getImage("GAME_START_BTN_PRESS"),
                470, 300, // *
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PageManager.switchPageTo("Loading");
                        try {
                            GameMain.gameLaunch();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
        PageManager.addComponent(1, pageName, "gameStartBtn", gameStartBtn);

        // My achivement
        JButton achivementBtn = frame.setImageButton(
                DataStorager.getImage("ACHIVEMENT_BTN"),
                DataStorager.getImage("ACHIVEMENT_BTN_PRESS"),
                490, 400, // *
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PageManager.switchPageTo("Achievement");
                    }
                });
        PageManager.addComponent(1, pageName, "achivementBtn", achivementBtn);

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
        PageManager.addComponent(1, pageName, "helpTeamBtn", helpTeamBtn);

        // Decorates
        JLabel mihoyo = frame.setBackground(DataStorager.getImage("MIHOYO"), 450, 520);
        PageManager.addComponent(1, pageName, "mihoyo", mihoyo);

        JLabel decorate = frame.setBackground(DataStorager.getImage("DECORATE_ICON"), 1060, 20);
        PageManager.addComponent(1, pageName, "decorate", decorate);

        // Main background
        JLabel mainBackground = frame.setBackground(DataStorager.getImage("MAIN_BG0"), 0, 0);
        PageManager.addComponent(0, pageName, "mainBackground", mainBackground);
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