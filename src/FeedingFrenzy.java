package src;

import java.io.IOException;

import src.framework.DataStorager;
import src.framework.PageManager;
import src.framework.SwingFramer;
import src.game.GameDriver;
import src.pages.Achievement;
import src.pages.GameMain;
import src.pages.Index;
import src.pages.Loading;

public class FeedingFrenzy {

    public final static int upperSiderBarHeight = 27;

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

        PageManager.switchPageTo("Index");
        // GameMain.gameLaunch();
        // GameDriver.loadGameDriver();

        frame.setVisible(true);
    }

}
