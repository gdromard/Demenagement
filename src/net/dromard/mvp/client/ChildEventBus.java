package net.dromard.mvp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;

public class ChildEventBus<H extends EventHandler, ParentH extends EventHandler, ParentEventBus extends EventBus<ParentH>> extends EventBus<H> {
    private final ParentEventBus parentEventBus;

    private final List<H> handlers = new ArrayList<H>();

    public ChildEventBus(ParentEventBus parentBus) {
        this.parentEventBus = parentBus;
    }

    @Override
    public List<H> getHandlers() {
        return handlers;
    }

    @Override
    public void addHandler(H handler) {
        handlers.add(handler);
    }

    public ParentEventBus getParentBus() {
        return parentEventBus;
    }
}
