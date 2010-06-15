package net.dromard.mvp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerManager;

public class EventBus<H extends EventHandler> extends HandlerManager {
    private final List<H> handlers = new ArrayList<H>();

    public List<H> getHandlers() {
        return handlers;
    }

    public void addHandler(H handler) {
        handlers.add(handler);
    }

    public EventBus() {
        super(null);
        bind();
    }

    public void bind() {
        // Handle history staff
        //History.addValueChangeHandler(this);
    }
}
