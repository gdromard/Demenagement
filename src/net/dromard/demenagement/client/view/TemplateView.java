package net.dromard.demenagement.client.view;

import net.dromard.mvp.client.View;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class TemplateView extends Composite implements View {

    private final FlexTable table = new FlexTable();

    public TemplateView() {
        table.getFlexCellFormatter().setColSpan(0, 0, 2);
        table.getFlexCellFormatter().setRowSpan(1, 0, 2);
        table.getFlexCellFormatter().setColSpan(2, 0, 2);
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
        table.setWidget(2, 1, widget);
    }

    public void setFooterWidget(Widget widget) {
        table.setWidget(2, 0, widget);
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
