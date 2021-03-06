package net.dromard.widget.client.impl.css;

import com.google.gwt.resources.client.ClientBundle;

public interface GwtWidgetsBundles extends ClientBundle {
    /*
    @Source("resources/logo.png")
    public ImageResource logo();
     */

    /*
     * Usage
     * <pre>
     * </pre>
    @Source("resources/text.txt")
    public TextResource text();
     */

    /**
     * Usage
     * 
     * <pre>
     * MyTheme theme = MyResources.INSTANCE.theme();
     * // Inject entire file
     * StyleInjector.inject(css.getText());
     * myWidget.setStyleName(theme.maClasse());
     * </pre>
     */
    Style style();
}
