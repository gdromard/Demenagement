package net.dromard.demenagement.client.event.generic;

import com.google.gwt.event.shared.EventHandler;

public interface UnselectRowEventHandler extends EventHandler {
    public void onUnselect(int rowIndex);
}
