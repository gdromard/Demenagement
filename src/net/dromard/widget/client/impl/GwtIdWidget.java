package net.dromard.widget.client.impl;

import net.dromard.widget.client.IdWidget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Widget;

public class GwtIdWidget extends Composite implements IdWidget {
    private final Hidden idField = new Hidden();

    public GwtIdWidget() {
        initWidget(idField);
    }

    @Override
    public Widget getWidget() {
        return this;
    }

    @Override
    public int getId() {
        return Integer.parseInt(idField.getValue());
    }

    @Override
    public void setId(int value) {
        idField.setValue(value + "");
    }
}
