package net.dromard.demenagement.client.handlers;

import com.google.gwt.user.client.ui.RootPanel;

public class ModuleHandler {
    public void load() {
        final DemenagementPanelHandler demenagementPanelHandler = new DemenagementPanelHandler();
        RootPanel.get().add(demenagementPanelHandler.buildList());
    }
}
