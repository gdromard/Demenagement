package net.dromard.demenagement.client.resources;

import com.google.gwt.resources.client.ClientBundle;

public interface ClientBundles extends ClientBundle {

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
    //@Source("net/dromard/demenagement/client/bundles/theme.css")
    Theme theme();

}
