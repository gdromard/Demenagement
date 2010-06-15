package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.AbstractEvent;

public abstract class DeletedModelEvent<M extends Model> extends AbstractEvent<DeletedModelEventHandler<M>> {
    private final M model;

    public DeletedModelEvent(M model) {
        this.model = model;
    }

    @Override
    protected void dispatch(DeletedModelEventHandler<M> handler) {
        handler.onDeleted(model);
    }
}
