package net.dromard.widget.client.impl;

import net.dromard.widget.client.ListBoxWidget;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class GwtListBoxWidget extends ListBox implements ListBoxWidget {

    public String getSelectedValue() {
        int selectedIndex = getSelectedIndex();

        return (selectedIndex == -1) ? null : getValue(getSelectedIndex());
    }

    public void setSelectedValue(String value) {
        if (value != null) {
            int itemCount = getItemCount();
            for (int i = 0; i < itemCount; i++) {
                if (value.equals(getValue(i))) {
                    setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void removeItem(String item) {
        if (item != null) {
            int itemCount = getItemCount();
            for (int i = 0; i < itemCount; i++) {
                if (item.equals(getValue(i))) {
                    removeItem(i);
                    break;
                }
            }
        }
    }

    @Override
    public Widget getWidget() {
        return this;
    }

}
