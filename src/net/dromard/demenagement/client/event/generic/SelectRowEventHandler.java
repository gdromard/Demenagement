package net.dromard.demenagement.client.event.generic;

import com.google.gwt.event.shared.EventHandler;

public interface SelectRowEventHandler extends EventHandler {
    public void onSelect(int rowIndex);
}
