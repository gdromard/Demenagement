package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.AbstractEvent;

public abstract class UpdatedModelEvent<M extends Model> extends AbstractEvent<UpdatedModelEventHandler<M>> {
    private final M model;

    public UpdatedModelEvent(M model) {
        this.model = model;
    }

    @Override
    protected void dispatch(UpdatedModelEventHandler<M> handler) {
        handler.onUpdated(model);
    }
}
