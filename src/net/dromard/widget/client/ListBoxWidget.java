package net.dromard.widget.client;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;

public interface ListBoxWidget extends HasClickHandlers, HasKeyUpHandlers, HasWidget {

    public void setSelectedIndex(int index);

    public int getSelectedIndex();

    public String getSelectedValue();

    public void setSelectedValue(String value);

    public void addItem(String item);

    public void removeItem(String item);

    public void clear();

    public void setEnabled(boolean enabled);

}
