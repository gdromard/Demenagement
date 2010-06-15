package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.AbstractEvent;

public abstract class DeleteModelEvent<M extends Model> extends AbstractEvent<DeleteModelEventHandler<M>> {
    private final M model;

    public DeleteModelEvent(M model) {
        this.model = model;
    }

    @Override
    protected void dispatch(DeleteModelEventHandler<M> handler) {
        handler.onDelete(model);
    }
}
