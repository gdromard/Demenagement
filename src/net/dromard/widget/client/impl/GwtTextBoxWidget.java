package net.dromard.widget.client.impl;

import net.dromard.widget.client.TextBoxWidget;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GwtTextBoxWidget extends TextBox implements TextBoxWidget {

    @Override
    public Widget getWidget() {
        return this;
    }

}
