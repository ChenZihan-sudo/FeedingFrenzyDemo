package src.framework;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.function.BiConsumer;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

class ComponentInfo {
    /**
     * StatusKey:
     * 1. Default
     * 2. PageSwitch: default is statusKey Default
     */

    private Hashtable<String, Boolean> visibleStatus = new Hashtable<>();

    ComponentInfo(Boolean _default) {
        setDefaultVisibleStatus(_default);
        setVisibleStatus("PageSwitch", _default);
    }

    public void setDefaultVisibleStatus(Boolean visible) {
        visibleStatus.put("Default", visible);
    }

    public void setVisibleStatus(String statusKey, Boolean visible) {
        visibleStatus.put(statusKey, visible);
    }

    public Boolean getVisibleStatus(String statusKey) throws Exception {
        if (visibleStatus.get(statusKey) == null)
            throw new Exception("warn: statusKey not found.");
        return visibleStatus.get(statusKey);
    }

    // void setVisibleProxy(String statusKey, Boolean aFlag) {
    // visibleStatus.get(statusKey);
    // setVisible(visibleStatus.get(statusKey));
    // }
}

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
            // ComponentInfo cinfo = container.getComponentInfo(key);
            // if (cinfo != null)
            //     try {
            //         value.setVisible(cinfo.getVisibleStatus("PageSwitch"));
            //     } catch (Exception e) {
            //         e.printStackTrace();
            //     }
            value.setVisible(true);
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
        PageContainer toPageContainer = setAllVisible(pageName, true);
        if (toPageContainer == null)
            return null;
        activedPage = toPageContainer.getPageBase().getPageName();

        // Switch the layeredPane to current page
        JLayeredPane layeredPane = toPageContainer.getPageBase().getLayeredPane();
        if (layeredPane != null) {
            SwingFramer frame = toPageContainer.getPageBase().getFrame();
            frame.setContentPane(layeredPane);
        }

        return toPageContainer;
    }

    public static void main(String[] args) {

    }
}
