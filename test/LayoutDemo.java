package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LayoutDemo {
    public static void main(String[] args) {
        LayoutDemo gridLayoutDemo = new LayoutDemo();
        try {
            gridLayoutDemo.createUI();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("File read exception");
            e.printStackTrace();
        }
    }

    public void createUI() throws IOException {
        JFrame frame = new JFrame("Grid Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel mainPanel = new MainPanel("Resource/resources/cc.png");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        mainPanel.add(topPanel);
        mainPanel.add(Box.createVerticalStrut(500));

        BottomPanel bottomPanel = new BottomPanel();
        mainPanel.add(bottomPanel);

        frame.setSize(820, 620);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @SuppressWarnings("serial")
    class MainPanel extends JPanel {
        private Image background;

        public MainPanel(String fileName) throws IOException {
            background = ImageIO.read(new File(fileName));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, this);
        }
    }

    @SuppressWarnings("serial")
    class BottomPanel extends JPanel {
        public BottomPanel() {
            LeftPanel leftPanel = new LeftPanel();
            leftPanel.setOpaque(false);
            leftPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            RightPanel rightPanel = new RightPanel();
            rightPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            rightPanel.setOpaque(false);
            add(leftPanel, BorderLayout.WEST);
            add(rightPanel, BorderLayout.EAST);
            setOpaque(false);
        }
    }

    @SuppressWarnings("serial")
    class LeftPanel extends JPanel {
        public LeftPanel() {
            setLayout(new GridLayout(1, 5, 5, 10));
            setBorder(new EmptyBorder(0, 5, 0, 55));
            setOpaque(false);
            JButton playButton = new JButton("Play");
            playButton.setBackground(Color.green);
            playButton.setPreferredSize(new Dimension(140, 40));
            add(playButton);
            JButton shapesButton = new JButton("Custom Shapes");
            shapesButton.setBackground(Color.orange);
            add(shapesButton);
            JButton helpButton = new JButton("Help");
            helpButton.setBackground(Color.red);
            add(helpButton);
            JButton scoresButton = new JButton("HighScores");
            scoresButton.setBackground(new Color(120, 81, 169));
            add(scoresButton);
            JButton aboutButton = new JButton("About");
            aboutButton.setBackground(Color.yellow);
            add(aboutButton);
        }
    }

    @SuppressWarnings("serial")
    class RightPanel extends JPanel {
        public RightPanel() {
            setBorder(new EmptyBorder(0, 0, 0, 0));
            JButton button = new JButton(new ImageIcon("Resource/Texture/Icon/Icon57.png"));
            button.setBorder(new EmptyBorder(0, 0, 0, 0));
            ;
            add(button, BorderLayout.CENTER);
        }
    }
}