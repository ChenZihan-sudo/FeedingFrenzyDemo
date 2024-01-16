package src;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import src.framework.DataStorager;
import src.framework.PageManager;
import src.framework.SwingFramer;
import src.game.GameDriver;
import src.pages.Achievement;
import src.pages.GameMain;
import src.pages.Index;
import src.pages.Loading;

public class FeedingFrenzy {

    protected static SwingFramer frame = new SwingFramer();

    public static SwingFramer getFrame() {
        return frame;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        // * Initialize in here.
        DataStorager ds = new DataStorager();
        PageManager pm = new PageManager();
        GameDriver gd = new GameDriver();

        frame.createWindow(DataStorager.APP_NAME, DataStorager.getImage("ICON"));

        // * Regist pages in here.
        Index indexFrame = new Index();
        Loading loading = new Loading();
        GameMain gameMain = new GameMain();
        Achievement achievement = new Achievement();

        // * Switch to index page.
        // PageManager.switchPageTo("Index");

        PageManager.switchPageTo("GameMain");
        // GameDriver.loadGameDriver();

        frame.setVisible(true);
    }

}
