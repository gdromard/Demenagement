package net.dromard.widget.client.impl;

import net.dromard.widget.client.IntegerBoxWidget;
import net.dromard.widget.client.impl.css.GwtWidgets;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GwtIntegerBoxWidget extends Composite implements IntegerBoxWidget {
    private final TextBox integerField = new TextBox();

    public GwtIntegerBoxWidget() {
        initWidget(integerField);
        integerField.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                try {
                    Integer.parseInt(integerField.getValue());
                } catch (Exception ex) {
                    integerField.addStyleName(GwtWidgets.getStyle().boxFormatError());
                }
            }
        });
    }

    @Override
    public Widget getWidget() {
        return this;
    }

    @Override
    public int getValue() throws Exception {
        return Integer.parseInt(integerField.getValue());
    }

    @Override
    public void setValue(int value) {
        integerField.setValue(value + "");
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return integerField.addValueChangeHandler(handler);
    }
}
