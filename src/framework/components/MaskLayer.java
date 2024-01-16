package src.framework.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class MaskLayer extends JComponent {
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        System.out.printf("Input visible %b\n", aFlag);
    }
}
