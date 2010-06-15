package net.dromard.demenagement.client.view;

import net.dromard.mvp.client.View;
import net.dromard.widget.client.VerticalPanelWidget;
import net.dromard.widget.client.impl.GwtButtonWidget;
import net.dromard.widget.client.impl.GwtVerticalPanelWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DebugView extends Composite implements View {

    private final GwtButtonWidget clearBtn = new GwtButtonWidget("Clear");

    private final VerticalPanelWidget debug = new GwtVerticalPanelWidget();

    public DebugView() {
        setMessage("");
        clearBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setMessage("");
            }
        });
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
        debug.add(clearBtn);
        appendMessage(message);
    }
}
