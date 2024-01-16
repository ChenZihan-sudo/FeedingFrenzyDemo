
package test;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class video {
    public ImageIcon i = new ImageIcon("Resource/yuanshen.gif");

    public static void main(String[] s) {
        JFrame frame = new JFrame();
        JLabel lable = new JLabel(new video().i);
        frame.add(lable);
        frame.setBounds(100, 100, 500, 375);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
