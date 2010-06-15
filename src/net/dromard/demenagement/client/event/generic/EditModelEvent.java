package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.AbstractEvent;

public abstract class EditModelEvent<M extends Model> extends AbstractEvent<EditModelEventHandler<M>> {
    private final M model;

    public EditModelEvent(M model) {
        this.model = model;
    }

    @Override
    protected void dispatch(EditModelEventHandler<M> handler) {
        handler.onEdit(model);
    }
}
