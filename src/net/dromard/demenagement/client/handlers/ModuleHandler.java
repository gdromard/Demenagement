package net.dromard.demenagement.client.handlers;

import java.util.List;

import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.services.DemenagementService;
import net.dromard.demenagement.shared.services.DemenagementServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class ModuleHandler {
    private final DemenagementServiceAsync demenagementService = GWT.create(DemenagementService.class);

    public void load() {
        final DemenagementPanelHandler demenagementPanelHandler = new DemenagementPanelHandler();

        final Label message = new Label("Asking server for demenagement list ...");

        demenagementService.getDemenagements(new AsyncCallback<List<Demenagement>>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                Window.alert("Remote Procedure Call - Failure");
                message.setText("Ooops ... it failed !");
            }

            public void onSuccess(List<Demenagement> result) {
                message.setText("Server return " + result.size() + " result(s)");
                RootPanel.get().add(demenagementPanelHandler.buildList(result));
            }
        });
        RootPanel.get().add(message);
    }
}
