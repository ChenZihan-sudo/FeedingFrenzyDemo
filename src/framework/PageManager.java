package src.framework;

import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

import src.framework.components.ComponentInfo;

class PageContainer {

    // Page data and its components are stored in here.
    private PageBase pageBase = null;
    private Hashtable<String, JComponent> components = new Hashtable<>();
    private Hashtable<String, ComponentInfo> infos = new Hashtable<>();

    public Hashtable<String, JComponent> getComponents() {
        return components;
    }

    public void setComponent(String name, JComponent component, ComponentInfo info) {
        components.put(name, component);
        infos.put(name, info);
    }

    public JComponent getComponent(String componentName) {
        return components.get(componentName);
    }

    public ComponentInfo getComponentInfo(String componentName) {
        return infos.get(componentName);
    }

    public void setPageBase(PageBase pageBase) {
        this.pageBase = pageBase;
    }

    public PageBase getPageBase() {
        return pageBase;
    }
}

public class PageManager {

    static protected String activedPage = null;
    static protected Hashtable<String, PageContainer> pages = new Hashtable<>();

    public PageManager() {
    };

    /**
     * @param pageName Default is class name of that page.
     */
    public static void createPage(String pageName, PageBase pageBase) {
        PageContainer componentsContainer = new PageContainer();
        componentsContainer.setPageBase(pageBase);
        pages.put(pageName, componentsContainer);
    }

    /**
     * @param pageName   Default is class name of that page.
     * @param components Should set pageBase in parameter components before call
     *                   this.
     */
    public static void createPage(String pageName, PageContainer components) {
        pages.put(pageName, components);
    }

    /**
     * Add component into page
     * 
     * @param pageName      Default is class name of that page.
     * @param componentName component name you named
     * @param zIndex        z index of layer
     */
    public static void addComponent(int zIndex, String pageName, String componentName, JComponent component) {
        PageContainer container = pages.get(pageName);
        JLayeredPane layeredPane = container.getPageBase().getLayeredPane();
        layeredPane.add(component, Integer.valueOf(zIndex));
        container.setComponent(componentName, component, new ComponentInfo(true));
    }

    /**
     * Add component into page
     * 
     * @param pageName      Default is class name of that page.
     * @param componentName component name you named
     * @param zIndex        z index of layer
     */
    public static void addComponent(int zIndex, String pageName, String componentName, JComponent component,
            ComponentInfo info) {
        PageContainer container = pages.get(pageName);
        JLayeredPane layeredPane = container.getPageBase().getLayeredPane();
        layeredPane.add(component, Integer.valueOf(zIndex));
        container.setComponent(componentName, component, info);
    }

    public static JComponent getComponent(String pageName, String componentName) {
        PageContainer components = pages.get(pageName);
        return components.getComponent(componentName);
    }

    public static PageContainer getAllComponents(String pageName) {
        return pages.get(pageName);
    }

    /**
     * Set all components visible
     * 
     * @param pageName Default is class name of that page.
     * @param visible
     */
    public static PageContainer setAllVisible(String pageName, Boolean visible) {
        PageContainer container = pages.get(pageName);
        if (container == null)
            return null;
        container.getComponents().forEach((key, value) -> {
            value.setVisible(false);
        });
        return container;
    }

    /**
     * Switch to one of page in ./pages
     * 
     * @param pageName Default is class name of that page.
     */
    public static PageContainer switchPageTo(String pageName) {

        // Hide actived page and release resource
        if (activedPage != null) {
            PageContainer lastPage = setAllVisible(activedPage, false);
            lastPage.getPageBase().pageRelease();
        }

        // Show page components
        PageContainer toPageContainer = pages.get(pageName);
        if (toPageContainer == null)
            return null;
        activedPage = toPageContainer.getPageBase().getPageName();

        // Set page switch status visible
        toPageContainer.getComponents().forEach((key, value) -> {
            ComponentInfo cinfo = toPageContainer.getComponentInfo(key);
            if (cinfo != null)
                try {
                    System.out.printf("组件状态：%b\n", cinfo.getVisibleStatus("PageSwitch"));
                    value.setVisible(cinfo.getVisibleStatus("PageSwitch"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });

        // Load page
        try {
            toPageContainer.getPageBase().pageLoad();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Switch the layeredPane to current page
        JLayeredPane layeredPane = toPageContainer.getPageBase().getLayeredPane();
        if (layeredPane != null) {
            SwingFramer frame = toPageContainer.getPageBase().getFrame();
            frame.setContentPane(layeredPane);
        }

        return toPageContainer;
    }

    public static PageBase getActivedPageBase() {
        PageContainer page = pages.get(activedPage);
        return page.getPageBase();
    }

    public static void main(String[] args) {

    }
}
