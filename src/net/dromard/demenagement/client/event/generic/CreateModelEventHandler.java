package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;

import com.google.gwt.event.shared.EventHandler;

public interface CreateModelEventHandler<M extends Model> extends EventHandler {
    public void onCreate(M model);
}
