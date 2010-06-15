package net.dromard.demenagement.client.view;

import net.dromard.demenagement.client.resources.ClientMessages;
import net.dromard.demenagement.shared.model.Carton;
import net.dromard.demenagement.shared.model.Destination;
import net.dromard.mvp.client.View;
import net.dromard.widget.client.ButtonWidget;
import net.dromard.widget.client.TableWidget;
import net.dromard.widget.client.impl.GwtButtonWidget;
import net.dromard.widget.client.impl.GwtDateBoxWidget;
import net.dromard.widget.client.impl.GwtLabelWidget;
import net.dromard.widget.client.impl.GwtTableWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DemenagementEditView extends Composite implements View {
    private final ClientMessages myMessages = GWT.create(ClientMessages.class);

    private final GwtLabelWidget errorLabel = new GwtLabelWidget("");

    private final GwtButtonWidget deleteButton = new GwtButtonWidget(myMessages.actionDelete());

    private final GwtButtonWidget cancelButton = new GwtButtonWidget(myMessages.actionCancel());

    private final GwtButtonWidget saveButton = new GwtButtonWidget(myMessages.actionSave());

    private final GwtDateBoxWidget dateBox = new GwtDateBoxWidget();

    private final GwtTableWidget cartonsList = new GwtTableWidget();

    public DemenagementEditView() {
        VerticalPanel vPanel = new VerticalPanel();

        Grid form = new Grid(3, 2);
        form.setWidget(0, 0, new Label(myMessages.labelDate()));
        form.setWidget(0, 1, dateBox);
        form.setWidget(1, 0, new Label(myMessages.labelCartons()));
        form.setWidget(1, 1, cartonsList);

        HorizontalPanel btnPanel = new HorizontalPanel();

        btnPanel.add(saveButton);
        btnPanel.add(deleteButton);
        btnPanel.add(cancelButton);

        vPanel.add(errorLabel);
        vPanel.add(form);
        vPanel.add(btnPanel);
        vPanel.setCellHorizontalAlignment(btnPanel, HorizontalPanel.ALIGN_CENTER);

        setAddingMode();

        initWidget(vPanel);
    }

    public void addCarton(Carton carton, Destination primary, Destination secondary) {
        int row = cartonsList.getRowCount();
        cartonsList.setWidget(row, 0, new Label(myMessages.labelCartonNumber() + carton.getNumero()));
        if (primary != null) {
            Label primaryLbl = new Label(primary.getName());
            primaryLbl.setTitle(primary.getDescription());
            cartonsList.setWidget(row, 1, primaryLbl);
        }
        if (secondary != null) {
            Label secondaryLbl = new Label(secondary.getName());
            secondaryLbl.setTitle(secondary.getDescription());
            cartonsList.setWidget(row, 2, secondaryLbl);
        }
    }

    public void setAddingMode() {
        errorLabel.setText("");
        deleteButton.setVisible(false);
        saveButton.setText(myMessages.actionAdd());
    }

    public void setEditMode() {
        errorLabel.setText("");
        deleteButton.setVisible(true);
        saveButton.setText(myMessages.actionSave());
    }

    public GwtLabelWidget getErrorLabel() {
        return errorLabel;
    }

    public ButtonWidget getDeleteButton() {
        return deleteButton;
    }

    public TableWidget getCartonTable() {
        return cartonsList;
    }

    public ButtonWidget getCancelButton() {
        return cancelButton;
    }

    public ButtonWidget getSaveButton() {
        return saveButton;
    }

    public GwtDateBoxWidget getDateBox() {
        return dateBox;
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
