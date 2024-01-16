package src.framework;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import src.framework.components.MaskLayer;

public class SwingFramer extends JFrame {

    public SwingFramer() {
        super();
    }

    public void createWindow(String title, BufferedImage appIcon) throws IOException {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(appIcon);

        setDefaultLookAndFeelDecorated(true);

        setSize(DataStorager.APP_WIDTH, DataStorager.APP_HEIGHT);
        setMinimumSize(new Dimension(DataStorager.APP_WIDTH, DataStorager.APP_HEIGHT));
        setMaximumSize(new Dimension(DataStorager.APP_WIDTH, DataStorager.APP_HEIGHT));
        setResizable(false);
        setFocusable(true);

        setLocationRelativeTo(rootPane);
    }

    public JLabel setBackground(Image image, double x, double y) {
        JLabel labelBG = new JLabel();
        ImageIcon imageIcon = new ImageIcon(image);
        labelBG.setIcon(imageIcon);
        labelBG.setBounds((int) x, (int) y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        // getLayeredPane().add(labelBG);
        return labelBG;
    }

    public JButton setImageButton(Image icon, Image iconPressed, double x, double y, ActionListener l) {
        JButton btn = new JButton();
        ImageIcon imageIcon = new ImageIcon(icon);
        ImageIcon imageIconPressed = new ImageIcon(iconPressed);
        btn.setBorderPainted(false);
        btn.setBounds((int) x, (int) y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        btn.setIcon(imageIcon);
        btn.setPressedIcon(imageIconPressed);
        btn.setBorder(null);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        if (l != null)
            btn.addActionListener(l);
        getLayeredPane().add(btn);
        return btn;
    }

    public MaskLayer setMaskLayer() {
        MaskLayer maskLayer = new MaskLayer();
        maskLayer.setBounds(0, 0, getWidth(), getHeight());
        getLayeredPane().add(maskLayer);
        return maskLayer;
    }

    // @Override
    // public void paint(Graphics g) {
    // try {
    // g.drawImage(DataStorager.getImage("MAIN_BG0"), 0, 0, null);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    // @Override
    // public void paintComponents(Graphics g) {
    // // TODO Auto-generated method stub
    // super.paintComponents(g);
    // }
}
