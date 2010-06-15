package net.dromard.widget.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;

public interface TableWidget extends HasClickHandlers, HasDoubleClickHandlers, HasWidget {

    public int getRowForEvent(ClickEvent event);

    public int getRowForEvent(DoubleClickEvent event);

    public void selectRow(int row);

    public void unSelectRow(int row);

    public void setText(int row, int column, String text);

    public int getRowCount();

    public void removeRow(int row);

}
