package net.dromard.widget.client.impl;

import net.dromard.widget.client.ButtonWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class GwtButtonWidget extends Button implements ButtonWidget {

    public GwtButtonWidget() {
        //nothing to do
    }

    public GwtButtonWidget(String text) {
        super(text);
    }

    @Override
    public Widget getWidget() {
        return this;
    }

}
