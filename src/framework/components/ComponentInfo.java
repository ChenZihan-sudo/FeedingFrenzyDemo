package src.framework.components;

import java.util.Hashtable;

public class ComponentInfo {
    /**
     * StatusKey:
     * 1. Default
     * 2. PageSwitch: default is statusKey Default
     */

    private Hashtable<String, Boolean> visibleStatus = new Hashtable<>();

    public ComponentInfo(Boolean _default) {
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
}