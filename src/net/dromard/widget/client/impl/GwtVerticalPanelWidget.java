package net.dromard.widget.client.impl;

import net.dromard.widget.client.VerticalPanelWidget;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GwtVerticalPanelWidget extends VerticalPanel implements VerticalPanelWidget {

    public GwtVerticalPanelWidget() {
        super();
    }

    @Override
    public Widget getWidget() {
        return this;
    }

}
