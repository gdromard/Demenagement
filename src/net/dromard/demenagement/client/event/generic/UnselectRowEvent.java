package net.dromard.demenagement.client.event.generic;

import net.dromard.mvp.client.AbstractEvent;

public abstract class UnselectRowEvent extends AbstractEvent<UnselectRowEventHandler> {
    private final int rowIndex;

    public UnselectRowEvent(int model) {
        this.rowIndex = model;
    }

    @Override
    protected void dispatch(UnselectRowEventHandler handler) {
        handler.onUnselect(rowIndex);
    }
}
