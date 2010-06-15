package net.dromard.demenagement.client.event.template;

import net.dromard.mvp.client.AbstractEvent;

import com.google.gwt.user.client.ui.Widget;

public abstract class ChangeRightTopWidgetEvent extends AbstractEvent<ChangeRightTopWidgetEventHandler> {
    private final Widget widget;

    public ChangeRightTopWidgetEvent(Widget widget) {
        this.widget = widget;
    }

    @Override
    protected void dispatch(ChangeRightTopWidgetEventHandler handler) {
        handler.onChangeRightTopWidget(widget);
    }
}
