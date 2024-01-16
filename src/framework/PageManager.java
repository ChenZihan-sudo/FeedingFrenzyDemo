package src.framework;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.function.BiConsumer;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

class ComponentsContainer {
    // Page data and its components are stored in here.

    protected Hashtable<String, JComponent> components = new Hashtable<>();
    protected PageBase pageBase = null;

    public void setComponent(String name, JComponent component) {
        components.put(name, component);
    }

    public JComponent getComponent(String componentName) {
        return components.get(componentName);
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
    static protected Hashtable<String, ComponentsContainer> pages = new Hashtable<>();

    public PageManager() {
    };

    /**
     * @param pageName Default is class name of that page.
     */
    public static void createPage(String pageName, PageBase pageBase) {
        ComponentsContainer componentsContainer = new ComponentsContainer();
        componentsContainer.setPageBase(pageBase);
        pages.put(pageName, componentsContainer);
    }

    /**
     * @param pageName   Default is class name of that page.
     * @param components Should set pageBase in parameter components before call
     *                   this.
     */
    public static void createPage(String pageName, ComponentsContainer components) {
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
        ComponentsContainer container = pages.get(pageName);
        JLayeredPane layeredPane = container.getPageBase().getLayeredPane();
        layeredPane.add(component, Integer.valueOf(zIndex));
        container.setComponent(componentName, component);
    }

    public static JComponent getComponent(String pageName, String componentName) {
        ComponentsContainer components = pages.get(pageName);
        return components.getComponent(componentName);
    }

    public static ComponentsContainer getAllComponents(String pageName) {
        return pages.get(pageName);
    }

    /**
     * Set all components visible
     * 
     * @param pageName Default is class name of that page.
     * @param visible
     */
    public static void setAllVisible(String pageName, Boolean visible) {
        ComponentsContainer container = pages.get(pageName);
        container.components.forEach((key, value) -> {
            value.setVisible(visible);
        });
    }

    /**
     * Switch to one of page in ./pages
     * 
     * @param pageName Default is class name of that page.
     */
    public static ComponentsContainer switchPageTo(String pageName) {
        ComponentsContainer toPageContainer = pages.get(pageName);
        if (toPageContainer == null)
            return null;

        toPageContainer.components.forEach((key, value) -> {
            value.setVisible(true);
        });

        pages.forEach((key, value) -> {
            ComponentsContainer container = value;
            if (!container.equals(toPageContainer)) {
                container.components.forEach((componentName, component) -> {
                    component.setVisible(false);
                });
            }
        });

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
