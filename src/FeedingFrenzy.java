package src;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import src.pages.GameMain;
import src.pages.Index;
import src.pages.Loading;
import src.utils.DataStorager;

public class FeedingFrenzy {

    protected static SwingFramer frame = new SwingFramer();

    public static SwingFramer getFrame() {
        return frame;
    }

    public static void main(String[] args) throws IOException {

        // Initialize
        DataStorager ds = new DataStorager();
        PageManager pm = new PageManager();

        frame.createWindow(DataStorager.APP_NAME, DataStorager.getImage("ICON"));

        // Pages
        Index indexFrame = new Index();
        Loading loading = new Loading();
        GameMain gameMain = new GameMain();

        frame.setVisible(true);
    }
}
