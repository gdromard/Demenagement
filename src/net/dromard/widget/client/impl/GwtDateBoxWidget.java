package net.dromard.widget.client.impl;

import net.dromard.widget.client.DateBoxWidget;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class GwtDateBoxWidget extends DateBox implements DateBoxWidget {

    @Override
    public Widget getWidget() {
        return this;
    }

    public HandlerRegistration addTextBoxChangeHandler(ChangeHandler handler) {
        return getTextBox().addChangeHandler(handler);
    }
}
