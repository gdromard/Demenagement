package net.dromard.demenagement.client.event.template;

import net.dromard.mvp.client.AbstractEvent;

import com.google.gwt.user.client.ui.Widget;

public abstract class ChangeFooterWidgetEvent extends AbstractEvent<ChangeFooterWidgetEventHandler> {
    private final Widget widget;

    public ChangeFooterWidgetEvent(Widget widget) {
        this.widget = widget;
    }

    @Override
    protected void dispatch(ChangeFooterWidgetEventHandler handler) {
        handler.onChangeFooterWidget(widget);
    }
}
