package net.dromard.widget.client.impl;

import net.dromard.widget.client.TextBoxWidget;

import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

public class GwtPasswordTextBoxWidget extends PasswordTextBox implements TextBoxWidget {

    @Override
    public Widget getWidget() {
        return this;
    }

}
