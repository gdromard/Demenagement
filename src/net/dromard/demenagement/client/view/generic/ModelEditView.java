package net.dromard.demenagement.client.view.generic;

import net.dromard.demenagement.client.resources.ClientMessages;
import net.dromard.mvp.client.View;
import net.dromard.widget.client.ButtonWidget;
import net.dromard.widget.client.IntegerBoxWidget;
import net.dromard.widget.client.LabelWidget;
import net.dromard.widget.client.TableWidget;
import net.dromard.widget.client.impl.GwtButtonWidget;
import net.dromard.widget.client.impl.GwtIntegerBoxWidget;
import net.dromard.widget.client.impl.GwtLabelWidget;
import net.dromard.widget.client.impl.GwtTableWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ModelEditView extends Composite implements View {
    protected static final ClientMessages MSG = GWT.create(ClientMessages.class);

    private final LabelWidget errorLabel = new GwtLabelWidget("");

    private final ButtonWidget cancelButton = new GwtButtonWidget(MSG.actionCancel());

    private final ButtonWidget saveButton = new GwtButtonWidget(MSG.actionSave());

    private final IntegerBoxWidget idBox = new GwtIntegerBoxWidget();

    private final TableWidget layout = new GwtTableWidget();

    public ModelEditView() {
        VerticalPanel vPanel = new VerticalPanel();
        HorizontalPanel btnPanel = new HorizontalPanel();

        btnPanel.add(saveButton.getWidget());
        btnPanel.add(cancelButton.getWidget());

        idBox.setVisible(false);
        vPanel.add(idBox.getWidget());
        vPanel.add(errorLabel.getWidget());
        vPanel.add(layout.getWidget());
        vPanel.add(btnPanel);
        vPanel.setCellHorizontalAlignment(btnPanel, HorizontalPanel.ALIGN_CENTER);

        initWidget(vPanel);
    }

    protected void setForm(Widget widget) {
        layout.setWidget(0, 1, widget);
    }

    public LabelWidget getErrorLabel() {
        return errorLabel;
    }

    public ButtonWidget getCancelButton() {
        return cancelButton;
    }

    public ButtonWidget getSaveButton() {
        return saveButton;
    }

    public IntegerBoxWidget getIdBox() {
        return idBox;
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
