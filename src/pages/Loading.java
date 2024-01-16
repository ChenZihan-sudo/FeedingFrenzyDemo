package src.pages;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import src.framework.DataStorager;
import src.framework.PageBase;
import src.framework.PageManager;
import src.utils.Utils;

public class Loading extends PageBase {

    public Loading() throws IOException {
        super();
        String className = Utils.getClassName(this);
        Initialization(className);
    }

    TimerTask tasktime_2main = new TimerTask() {
        @Override
        public void run() {
            PageManager.switchPageTo("GameMain");
        }
    };

    @Override
    public void pageInitialize() throws IOException {

        JLabel yuanshenQidong = new JLabel();
        ImageIcon imageIcon = new ImageIcon("Resource/yuanshen.gif");
        yuanshenQidong.setIcon(imageIcon);
        yuanshenQidong.setBounds(0, -30, imageIcon.getIconWidth(),
                imageIcon.getIconHeight());
        PageManager.addComponent(0, pageName, "Y", yuanshenQidong);

        // Return to index
        JButton returnIndexBtn = frame.setImageButton(
                DataStorager.getImage("BACK_BTN"),
                DataStorager.getImage("BACK_PRESS_BTN"),
                20, 520, // *
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        PageManager.switchPageTo("Index");
                    }
                });
        PageManager.addComponent(1, pageName, "returnIndexBtn", returnIndexBtn);

        // Skip
        JButton skipToGameBtn = frame.setImageButton(
                DataStorager.getImage("SKIP_BTN"),
                DataStorager.getImage("SKIP_PRESS_BTN"),
                980, 20,
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        PageManager.switchPageTo("GameMain");
                    }
                });
        PageManager.addComponent(1, pageName, "skipToGameBtn", skipToGameBtn);

        // Wait for anime
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("Timer runned.");
                PageManager.switchPageTo("GameMain");
            }
        }, 14000);
    }

    @Override
    public void pageLoad() {

    }
}
