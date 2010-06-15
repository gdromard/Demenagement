package net.dromard.widget.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

public interface DateBoxWidget extends HasValue<Date>, HasWidget {
    public HandlerRegistration addTextBoxChangeHandler(ChangeHandler handler);
}
