package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.AbstractEvent;

public abstract class CreateModelEvent<M extends Model> extends AbstractEvent<CreateModelEventHandler<M>> {
    private final M model;

    public CreateModelEvent(M model) {
        this.model = model;
    }

    @Override
    protected void dispatch(CreateModelEventHandler<M> handler) {
        handler.onCreate(model);
    }
}
