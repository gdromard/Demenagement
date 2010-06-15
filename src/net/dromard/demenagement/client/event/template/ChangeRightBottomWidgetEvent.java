package net.dromard.demenagement.client.event.template;

import net.dromard.mvp.client.AbstractEvent;

import com.google.gwt.user.client.ui.Widget;

public abstract class ChangeRightBottomWidgetEvent extends AbstractEvent<ChangeRightBottomWidgetEventHandler> {
    private final Widget widget;

    public ChangeRightBottomWidgetEvent(Widget widget) {
        this.widget = widget;
    }

    @Override
    protected void dispatch(ChangeRightBottomWidgetEventHandler handler) {
        handler.onChangeRightBottomWidget(widget);
    }
}
