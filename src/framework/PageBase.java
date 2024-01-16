package src.framework;

import java.io.IOException;

import javax.swing.JLayeredPane;

import src.FeedingFrenzy;

public class PageBase {

    protected String pageName;
    protected SwingFramer frame;
    protected JLayeredPane layeredPane;

    public PageBase() {
        this.layeredPane = new JLayeredPane();
    }

    protected void Initialization(String pageName) throws IOException {
        this.pageName = pageName;
        frame = FeedingFrenzy.getFrame();
        PageManager.createPage(pageName, this);
        pageComponentInitialize();
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public SwingFramer getFrame() {
        return frame;
    }

    public String getPageName() {
        return pageName;
    }

    // Override this.
    public void pageComponentInitialize() throws IOException {
    }

    // Override this.
    public void pageReleaser() {
    }
}