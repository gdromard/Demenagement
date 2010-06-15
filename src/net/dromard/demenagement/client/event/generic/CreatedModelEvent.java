package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.AbstractEvent;

public abstract class CreatedModelEvent<M extends Model> extends AbstractEvent<CreatedModelEventHandler<M>> {
    private final M model;

    public CreatedModelEvent(M model) {
        this.model = model;
    }

    @Override
    protected void dispatch(CreatedModelEventHandler<M> handler) {
        handler.onCreated(model);
    }
}
