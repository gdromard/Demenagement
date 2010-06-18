package net.dromard.demenagement.client.view;

import net.dromard.demenagement.client.view.generic.ModelEditView;
import net.dromard.widget.client.IntegerBoxWidget;
import net.dromard.widget.client.LabelWidget;
import net.dromard.widget.client.impl.GwtIntegerBoxWidget;
import net.dromard.widget.client.impl.GwtLabelWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class CartonEditView extends ModelEditView {

    private final IntegerBoxWidget numberBox = new GwtIntegerBoxWidget();

    private final LabelWidget primaryDestinationBox = new GwtLabelWidget("");

    private final LabelWidget secondaryDestinationBox = new GwtLabelWidget("");

    public CartonEditView() {
        super();
        HorizontalPanel form = new HorizontalPanel();
        form.add(new Label(MSG.labelDate()));
        form.add(numberBox.getWidget());
        setForm(form);
    }

    public IntegerBoxWidget getNumberBox() {
        return numberBox;
    }

    public LabelWidget getPrimaryDestinationBox() {
        return primaryDestinationBox;
    }

    public LabelWidget getSecondaryDestinationBox() {
        return secondaryDestinationBox;
    }
}
