package net.dromard.demenagement.client.event.template;

import net.dromard.mvp.client.AbstractEvent;

import com.google.gwt.user.client.ui.Widget;

public abstract class ChangeLeftWidgetEvent extends AbstractEvent<ChangeLeftWidgetEventHandler> {
    private final Widget widget;

    public ChangeLeftWidgetEvent(Widget widget) {
        this.widget = widget;
    }

    @Override
    protected void dispatch(ChangeLeftWidgetEventHandler handler) {
        handler.onChangeLeftWidget(widget);
    }
}
