package net.dromard.demenagement.client.resources;

import com.google.gwt.resources.client.CssResource;

public interface Theme extends CssResource {

    String dialogVPanel();

    @ClassName("gwt-DialogBox")
    String gwtDialogBox();

    String sendButton();

    String serverResponseLabelError();

}
