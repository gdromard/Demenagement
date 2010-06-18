package net.dromard.mvp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EventBus<H extends EventHandler> extends HandlerManager {
    public EventBus() {
        super(null);
        bind();
    }

    public void bind() {
        // Handle history staff
        //History.addValueChangeHandler(this);
    }

    protected final <EH extends EventHandler> List<EH> getHandlers(Type<EH> type) {
        List<EH> handlers = new ArrayList<EH>();
        for (int i = 0; i < super.getHandlerCount(type); ++i) {
            handlers.add(super.getHandler(type, i));
        }
        return handlers;
    }
}
