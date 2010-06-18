package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;

import com.google.gwt.event.shared.EventHandler;

public interface ListChangedEventHandler extends EventHandler {
    public void onListChanged(Class<? extends Model> clazz);
}
