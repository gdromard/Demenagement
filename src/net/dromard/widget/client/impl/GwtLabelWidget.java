package net.dromard.widget.client.impl;

import net.dromard.widget.client.LabelWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class GwtLabelWidget extends Label implements LabelWidget {

    public GwtLabelWidget(String text) {
        super(text);
    }

    public GwtLabelWidget(String text, String title) {
        this(text);
        setTitle(title);
    }

    @Override
    public Widget getWidget() {
        return this;
    }

}
