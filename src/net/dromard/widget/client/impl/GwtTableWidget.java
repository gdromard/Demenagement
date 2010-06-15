package net.dromard.widget.client.impl;

import net.dromard.widget.client.TableWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class GwtTableWidget extends FlexTable implements TableWidget {

    public void selectRow(int row) {
        getRowFormatter().addStyleName(row, "selected");

    }

    public void unSelectRow(int row) {
        getRowFormatter().removeStyleName(row, "selected");
    }

    public int getRowForEvent(ClickEvent event) {
        return getCellForEvent(event).getRowIndex();
    }

    public int getRowForEvent(DoubleClickEvent event) {
        Element td = getEventTargetCell(Event.as(event.getNativeEvent()));
        if (td != null) {
            Element tr = DOM.getParent(td);
            Element body = DOM.getParent(tr);
            return DOM.getChildIndex(body, tr);
        }
        return -1;
    }

    @Override
    public Widget getWidget() {
        return this;
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
        return addDomHandler(handler, DoubleClickEvent.getType());
    }
}
