package net.dromard.demenagement.client.view;

import net.dromard.demenagement.client.view.generic.ModelEditView;
import net.dromard.widget.client.DateBoxWidget;
import net.dromard.widget.client.impl.GwtDateBoxWidget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class DemenagementEditView extends ModelEditView {
    private final DateBoxWidget dateBox = new GwtDateBoxWidget();

    public DemenagementEditView() {
        super();
        HorizontalPanel form = new HorizontalPanel();
        form.add(new Label(MSG.labelDate()));
        form.add(dateBox.getWidget());
        setForm(form);
    }

    public DateBoxWidget getDateBox() {
        return dateBox;
    }
}
