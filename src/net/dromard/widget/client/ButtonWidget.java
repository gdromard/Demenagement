package net.dromard.widget.client;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface ButtonWidget extends HasClickHandlers, HasWidget {

    public void setEnabled(boolean enabled);

    public void setVisible(boolean isVisible);

    public void setText(String text);
}
