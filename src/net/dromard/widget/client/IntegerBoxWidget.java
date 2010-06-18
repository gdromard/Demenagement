package net.dromard.widget.client;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public interface IntegerBoxWidget extends IsWidget, HasWidget {

    /**
     * @return The parsed value of the field
     * @throws Exception A NumberFormatException if format typed is invalid.
     */
    int getValue() throws Exception;

    void setValue(int value);

    HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler);
}
