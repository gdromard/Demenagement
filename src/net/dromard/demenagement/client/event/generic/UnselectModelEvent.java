package net.dromard.demenagement.client.event.generic;

import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.AbstractEvent;

public abstract class UnselectModelEvent<M extends Model> extends AbstractEvent<UnselectModelEventHandler<M>> {
    private final M model;

    private final int rowIndex;

    public UnselectModelEvent(int rowIndex, M model) {
        this.rowIndex = rowIndex;
        this.model = model;
    }

    @Override
    protected void dispatch(UnselectModelEventHandler<M> handler) {
        handler.onUnselect(rowIndex, model);
    }
}
