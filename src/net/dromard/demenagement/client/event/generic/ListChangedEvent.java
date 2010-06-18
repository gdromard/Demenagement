package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.AbstractEvent;

public abstract class ListChangedEvent extends AbstractEvent<ListChangedEventHandler> {
    private final Class<? extends Model> clazz;

    public ListChangedEvent(Class<? extends Model> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void dispatch(ListChangedEventHandler handler) {
        handler.onListChanged(clazz);
    }
}
