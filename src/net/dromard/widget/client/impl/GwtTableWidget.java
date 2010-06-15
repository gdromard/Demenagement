package net.dromard.widget.client.impl;

import net.dromard.widget.client.TableWidget;
import net.dromard.widget.client.impl.css.GwtWidgets;
import net.dromard.widget.client.impl.css.Style;

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
    private static Style style = GwtWidgets.getStyle();

    public GwtTableWidget() {
        setStyleName(style.grid());
    }

    @Override
    public void selectRow(int row) {
        getRowFormatter().addStyleName(row, style.selected());
    }

    @Override
    public void unSelectRow(int row) {
        getRowFormatter().removeStyleName(row, style.selected());
    }

    @Override
    public int getRowForEvent(ClickEvent event) {
        return getCellForEvent(event).getRowIndex();
    }

    @Override
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
    public void setText(int row, int column, String text) {
        super.setText(row, column, text);
        if (row > 0) {
            getRowFormatter().addStyleName(row, (row % 2 == 0 ? style.evenColor() : style.oddColor()));
        } else {
            getRowFormatter().addStyleName(row, style.gridHeader());
        }
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
