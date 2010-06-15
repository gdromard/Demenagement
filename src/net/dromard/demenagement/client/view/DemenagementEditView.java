package net.dromard.demenagement.client.view;

import net.dromard.demenagement.client.resources.ClientMessages;
import net.dromard.demenagement.shared.model.Carton;
import net.dromard.demenagement.shared.model.Destination;
import net.dromard.mvp.client.View;
import net.dromard.widget.client.ButtonWidget;
import net.dromard.widget.client.DateBoxWidget;
import net.dromard.widget.client.IdWidget;
import net.dromard.widget.client.LabelWidget;
import net.dromard.widget.client.TableWidget;
import net.dromard.widget.client.impl.GwtButtonWidget;
import net.dromard.widget.client.impl.GwtDateBoxWidget;
import net.dromard.widget.client.impl.GwtIdWidget;
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

    private final LabelWidget errorLabel = new GwtLabelWidget("");

    private final ButtonWidget cancelButton = new GwtButtonWidget(myMessages.actionCancel());

    private final ButtonWidget saveButton = new GwtButtonWidget(myMessages.actionSave());

    private final IdWidget idBox = new GwtIdWidget();

    private final DateBoxWidget dateBox = new GwtDateBoxWidget();

    private final TableWidget cartonsList = new GwtTableWidget();

    public DemenagementEditView() {
        VerticalPanel vPanel = new VerticalPanel();

        Grid form = new Grid(3, 2);
        form.setWidget(0, 0, new Label(myMessages.labelDate()));
        form.setWidget(0, 1, dateBox.getWidget());
        form.setWidget(1, 0, new Label(myMessages.labelCartons()));
        form.setWidget(1, 1, cartonsList.getWidget());

        HorizontalPanel btnPanel = new HorizontalPanel();

        btnPanel.add(saveButton.getWidget());
        btnPanel.add(cancelButton.getWidget());

        vPanel.add(idBox.getWidget());
        vPanel.add(errorLabel.getWidget());
        vPanel.add(form);
        vPanel.add(btnPanel);
        vPanel.setCellHorizontalAlignment(btnPanel, HorizontalPanel.ALIGN_CENTER);

        initWidget(vPanel);
    }

    public void addCarton(Carton carton, Destination primary, Destination secondary) {
        int row = cartonsList.getRowCount();
        cartonsList.setText(row, 0, myMessages.labelCartonNumber() + carton.getNumero());
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

    public LabelWidget getErrorLabel() {
        return errorLabel;
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

    public DateBoxWidget getDateBox() {
        return dateBox;
    }

    public IdWidget getIdBox() {
        return idBox;
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
