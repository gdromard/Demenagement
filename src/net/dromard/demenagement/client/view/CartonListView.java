package net.dromard.demenagement.client.view;

import net.dromard.demenagement.client.resources.ClientMessages;
import net.dromard.demenagement.client.view.generic.ModelListView;
import net.dromard.demenagement.shared.model.Carton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Widget;

public class CartonListView extends ModelListView<Carton> {
    private static final ClientMessages myMessages = GWT.create(ClientMessages.class);

    public CartonListView() {
        super();
        getTable().setText(0, 0, myMessages.cartonIdLabel());
        getTable().setText(0, 1, myMessages.cartonNumberLabel());
        getTable().setText(0, 2, myMessages.cartonPrimaryDestinationLabel());
        getTable().setText(0, 3, myMessages.cartonSecondaryDestinationLabel());
    }

    @Override
    protected void initWidget(Widget widget) {
        CaptionPanel cpPanel = new CaptionPanel(myMessages.cartonsLabel());
        cpPanel.add(widget);
        super.initWidget(cpPanel);
    }
}
