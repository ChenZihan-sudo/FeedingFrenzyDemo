package src.pages;

import java.io.IOException;

import src.FeedingFrenzy;
import src.PageManager;
import src.SwingFramer;

public class PageBase {

    public String pageName;
    protected SwingFramer frame;

    PageBase() {}

    protected void Initialization(String pageName) throws IOException {
        this.pageName = pageName;
        frame = FeedingFrenzy.getFrame();
        PageManager.createPage(pageName);
        pageComponentInitialize();
    }

    public void pageComponentInitialize() throws IOException {
    }
}
