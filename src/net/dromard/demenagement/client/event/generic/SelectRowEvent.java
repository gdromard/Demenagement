package net.dromard.demenagement.client.event.generic;

import net.dromard.mvp.client.AbstractEvent;

public abstract class SelectRowEvent extends AbstractEvent<SelectRowEventHandler> {
    private final int rowIndex;

    public SelectRowEvent(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    protected void dispatch(SelectRowEventHandler handler) {
        handler.onSelect(rowIndex);
    }
}
