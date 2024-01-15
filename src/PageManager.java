package src;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.function.BiConsumer;

import javax.swing.JComponent;

class ComponentsContainer {
    protected Hashtable<String, JComponent> components = new Hashtable<>();

    public JComponent getComponent(String componentName) {
        return components.get(componentName);
    }

    public void setComponent(String name, JComponent component) {
        components.put(name, component);
    }
}

public class PageManager {

    static protected Hashtable<String, ComponentsContainer> pages = new Hashtable<>();

    public static void createPage(String pageName) {
        pages.put(pageName, new ComponentsContainer());
    }

    public static void addComponent(String pageName, String componentName, JComponent component) {
        ComponentsContainer container = pages.get(pageName);
        container.setComponent(componentName, component);
    }

    public static JComponent getComponent(String pageName, String componentName) {
        ComponentsContainer components = pages.get(pageName);
        return components.getComponent(componentName);
    }

    public static void createPage(String pageName, ComponentsContainer components) {
        pages.put(pageName, components);
    }

    public static ComponentsContainer getComponents(String pageName) {
        return pages.get(pageName);
    }

    public static void setAllVisible(String pageName, Boolean visible) {
        ComponentsContainer container = pages.get(pageName);
        container.components.forEach((key, value) -> {
            value.setVisible(visible);
        });
    }

    public static void switchPageTo(String pageName) {
        ComponentsContainer toPageContainer = pages.get(pageName);
        if (toPageContainer == null)
            return;

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
    }

    public static void main(String[] args) {

    }
}
