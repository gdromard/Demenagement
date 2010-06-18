package net.dromard.demenagement.client.presenter;

import net.dromard.demenagement.client.presenter.generic.ModelEditPresenter;
import net.dromard.demenagement.client.view.CartonEditView;
import net.dromard.demenagement.shared.model.Carton;
import net.dromard.demenagement.shared.services.AbstractServiceAsync;
import net.dromard.demenagement.shared.services.CartonService;
import net.dromard.demenagement.shared.services.CartonServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class CartonEditPresenter extends ModelEditPresenter<Carton, CartonEditView> {
    @Override
    public void bind() {
        super.bind();
        // Handle invalid dates
        getView().getNumberBox().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                boolean ok;
                try {
                    getView().getNumberBox().getValue();
                    ok = true;
                } catch (Exception ex) {
                    ok = false;
                }
                getView().getSaveButton().setEnabled(ok);
            }
        });
    }

    @Override
    protected Carton createAndPopulateModel() throws Exception {
        Carton model = new Carton();
        model.setId(getView().getIdBox().getValue());
        model.setNumber(getView().getNumberBox().getValue());
        //model.setPrimaryDestination(Integer.parseInt(getView().getPrimaryDestinationBox().getValue()));
        //model.setSecondaryDestination(Integer.parseInt(getView().getSecondaryDestinationBox().getValue()));
        return model;
    }

    @Override
    protected void populateForm(Carton model) {
        getView().getIdBox().setValue(model.getId());
        getView().getNumberBox().setValue(model.getNumber());
        //getView().getPrimaryDestinationBox().selectItem(""+model.getPrimaryDestination());
        //getView().getSecondaryDestinationBox().selectItem(""+model.getSecondaryDestination());
        getView().getSaveButton().setEnabled(false);
    }

    @Override
    protected AbstractServiceAsync<Carton> setService() {
        return (CartonServiceAsync) GWT.create(CartonService.class);
    }
}
