package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.AbstractEvent;

public abstract class SelectModelEvent<M extends Model> extends AbstractEvent<SelectModelEventHandler<M>> {
    private final M model;

    private final int rowIndex;

    public SelectModelEvent(int rowIndex, M model) {
        this.rowIndex = rowIndex;
        this.model = model;
    }

    @Override
    protected void dispatch(SelectModelEventHandler<M> handler) {
        handler.onSelect(rowIndex, model);
    }
}
