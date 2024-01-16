package src.game;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import src.FeedingFrenzy;
import src.framework.SwingFramer;

public class GameDriver {

    protected static SwingFramer frame;
    protected static KeyListener keyListener;
    protected static MouseMotionListener mouseMotionListener;

    public GameDriver() {
        frame = FeedingFrenzy.getFrame();

        keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // WASD
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    System.out.println("pressedW");
                }
                // UP DOWN LEFT RIGHT
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("pressedW");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                // WASD
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    System.out.println("pressedW");
                }
                // UP DOWN LEFT RIGHT
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("pressedW");
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("pressedW");
                }
            }
        };

        mouseMotionListener = new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Point point = e.getPoint();
                System.out.println("Mouse Position: " + point);
            }

        };
    }

    public static void loadGameDriver() {
        frame.addKeyListener(keyListener);
        frame.addMouseMotionListener(mouseMotionListener);
        
    }

    public static void removeGameDriver() {
        frame.removeKeyListener(keyListener);
        frame.removeMouseMotionListener(mouseMotionListener);
    }

}
