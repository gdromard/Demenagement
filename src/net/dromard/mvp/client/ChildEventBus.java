package net.dromard.mvp.client;

import com.google.gwt.event.shared.EventHandler;

public class ChildEventBus<H extends EventHandler, ParentH extends EventHandler, ParentEventBus extends EventBus<ParentH>> extends EventBus<H> {
    private final ParentEventBus parentEventBus;

    public ChildEventBus(ParentEventBus parentBus) {
        this.parentEventBus = parentBus;
    }

    public ParentEventBus getParentBus() {
        return parentEventBus;
    }
}
