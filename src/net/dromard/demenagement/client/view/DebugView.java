package net.dromard.demenagement.client.view;

import net.dromard.mvp.client.View;
import net.dromard.widget.client.VerticalPanelWidget;
import net.dromard.widget.client.impl.GwtVerticalPanelWidget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DebugView extends Composite implements View {

    private final VerticalPanelWidget debug = new GwtVerticalPanelWidget();

    public DebugView() {
        initWidget(debug.getWidget());
    }

    @Override
    public Widget getWidget() {
        return this;
    }

    public void appendMessage(String message) {
        debug.add(new Label(message));
    }

    public void setMessage(String message) {
        debug.clear();
        appendMessage(message);
    }
}
