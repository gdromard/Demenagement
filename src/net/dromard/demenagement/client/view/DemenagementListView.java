package net.dromard.demenagement.client.view;

import net.dromard.demenagement.client.resources.ClientMessages;
import net.dromard.demenagement.client.view.generic.ModelListView;
import net.dromard.demenagement.shared.model.Demenagement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Widget;

public class DemenagementListView extends ModelListView<Demenagement> {
    private static final ClientMessages myMessages = GWT.create(ClientMessages.class);

    public DemenagementListView() {
        super();
        getTable().setText(0, 0, myMessages.movingIdLabel());
        getTable().setText(0, 1, myMessages.movingDateLabel());
    }

    @Override
    protected void initWidget(Widget widget) {
        CaptionPanel cpPanel = new CaptionPanel(myMessages.movingsLabel());
        cpPanel.add(widget);
        super.initWidget(cpPanel);
    }
}
