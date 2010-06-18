package net.dromard.demenagement.client.presenter;

import java.util.List;

import net.dromard.demenagement.client.presenter.generic.ModelListPresenter;
import net.dromard.demenagement.client.resources.ClientMessages;
import net.dromard.demenagement.shared.model.Carton;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.model.Destination;
import net.dromard.demenagement.shared.services.AbstractServiceAsync;
import net.dromard.demenagement.shared.services.CartonService;
import net.dromard.demenagement.shared.services.CartonServiceAsync;
import net.dromard.demenagement.shared.services.DestinationService;
import net.dromard.demenagement.shared.services.DestinationServiceAsync;
import net.dromard.widget.client.impl.GwtLabelWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class CartonListPresenter extends ModelListPresenter<Carton> {
    private static final ClientMessages myMessages = GWT.create(ClientMessages.class);

    private static final DestinationServiceAsync destinationService = (DestinationServiceAsync) GWT.create(DestinationService.class);

    private final Demenagement currentDemenagement = null;

    @Override
    public void bind() {
        super.bind();
        getView().getNewButton().setEnabled(false);
        getEventBus().getParentBus().changeRightTopWidget(getView());
    }

    @Override
    protected Carton createModel() {
        return new Carton();
    }

    @Override
    protected void displayModels(final List<Carton> models) {
        destinationService.getList(new AsyncCallback<List<Destination>>() {
            public void onFailure(Throwable caught) {
                getView().getErrorLabel().setText(myMessages.errorActionFailed(myMessages.actionLoad()));
            }

            public void onSuccess(List<Destination> destinations) {
                asyncDisplayModels(models, destinations);
            }
        });

    }

    private void asyncDisplayModels(List<Carton> models, List<Destination> destinations) {
        for (int i = 0; i < models.size(); i++) {
            Carton model = models.get(i);
            getView().getTable().setText(i + 1, 0, "" + model.getId());
            getView().getTable().setText(i + 1, 1, "" + model.getNumber());
            getView().getTable().setWidget(i + 1, 2, createLabel(getDestination(destinations, model.getPrimaryDestination())));
            getView().getTable().setWidget(i + 1, 3, createLabel(getDestination(destinations, model.getSecondaryDestination())));
        }
    }

    private Widget createLabel(Destination destination) {
        if (destination != null) {
            return new GwtLabelWidget(destination.getName(), destination.getDescription());
        }
        return new GwtLabelWidget(myMessages.destinationErrorNotFound());
    }

    private Destination getDestination(List<Destination> destinations, int id) {
        if (destinations != null) {
            for (int i = 0; i < destinations.size(); i++) {
                if (destinations.get(i).getId() == id) {
                    return destinations.get(i);
                }
            }
        }
        return null;
    }

    @Override
    protected AbstractServiceAsync<Carton> setService() {
        return (CartonServiceAsync) GWT.create(CartonService.class);
    }
}
