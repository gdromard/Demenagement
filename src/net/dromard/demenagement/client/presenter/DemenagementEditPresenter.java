package net.dromard.demenagement.client.presenter;

import java.util.Date;

import net.dromard.demenagement.client.presenter.generic.ModelEditPresenter;
import net.dromard.demenagement.client.view.DemenagementEditView;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.services.AbstractServiceAsync;
import net.dromard.demenagement.shared.services.DemenagementService;
import net.dromard.demenagement.shared.services.DemenagementServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class DemenagementEditPresenter extends ModelEditPresenter<Demenagement, DemenagementEditView> {
    @Override
    public void bind() {
        super.bind();
        // Handle invalid dates
        getView().getDateBox().addTextBoxChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                getView().getSaveButton().setEnabled(getView().getDateBox().getValue() != null);
            }
        });
        getView().getDateBox().addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                getView().getSaveButton().setEnabled(event.getValue() != null);
            }
        });
    }

    @Override
    protected Demenagement createAndPopulateModel() throws Exception {
        Demenagement model = new Demenagement();
        model.setId(getView().getIdBox().getValue());
        model.setDate(getView().getDateBox().getValue());
        return model;
    }

    @Override
    protected void populateForm(Demenagement model) {
        getView().getIdBox().setValue(model.getId());
        getView().getDateBox().setValue(model.getDate());
        getView().getSaveButton().setEnabled(false);
    }

    @Override
    protected AbstractServiceAsync<Demenagement> setService() {
        return (DemenagementServiceAsync) GWT.create(DemenagementService.class);
    }
}
