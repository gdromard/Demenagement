package net.dromard.widget.client.impl.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;

public class GwtWidgets {
    private static GwtWidgetsBundles INSTANCE = null;

    public static Style getStyle() {
        if (INSTANCE == null) {
            INSTANCE = GWT.create(GwtWidgetsBundles.class);
            StyleInjector.inject(INSTANCE.style().getText());
        }
        return INSTANCE.style();
    }
}
