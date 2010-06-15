package net.dromard.widget.client.impl;

import net.dromard.widget.client.HiddenWidget;

import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Widget;

public class GwtHiddenWidget extends Hidden implements HiddenWidget {
    @Override
    public Widget getWidget() {
        return this;
    }
}
