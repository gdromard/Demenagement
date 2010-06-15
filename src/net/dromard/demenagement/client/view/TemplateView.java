package net.dromard.demenagement.client.view;

import net.dromard.mvp.client.View;
import net.dromard.widget.client.impl.GwtTableWidget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TemplateView extends Composite implements View {

    private final GwtTableWidget table = new GwtTableWidget();

    public TemplateView() {
        table.getFlexCellFormatter().setColSpan(0, 0, 2);
        table.getFlexCellFormatter().setRowSpan(1, 0, 2);
        table.getFlexCellFormatter().setColSpan(3, 0, 2);

        // DEBUG start
        /*
        table.setBorderWidth(1);
        table.setWidget(0, 0, new Label("Header"));
        table.setWidget(1, 0, new Label("Left"));
        table.setWidget(1, 1, new Label("RightTop"));
        table.setWidget(2, 0, new Label("RightBottom"));
        table.setWidget(3, 0, new Label("Footer"));
        */
        // DEBUG stop

        initWidget(table);
    }

    public void setHeaderWidget(Widget widget) {
        table.setWidget(0, 0, widget);
    }

    public void setLeftWidget(Widget widget) {
        table.setWidget(1, 0, widget);
    }

    public void setRightTopWidget(Widget widget) {
        table.setWidget(1, 1, widget);
    }

    public void setRightBottomWidget(Widget widget) {
        table.setWidget(2, 0, widget);
    }

    public void setFooterWidget(Widget widget) {
        table.setWidget(3, 0, widget);
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
