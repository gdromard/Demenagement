package net.dromard.demenagement.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface ClientBundles extends ClientBundle {
    public static final ClientBundles INSTANCE = GWT.create(ClientBundles.class);

    /*
     * 
    @Source("resources/logo.png")
    public ImageResource logo();
     */

    /*
     * Usage
     * 
     * <pre>
     * 
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
    @Source("net/dromard/demenagement/client/resources/style.css")
    Style style();
}
