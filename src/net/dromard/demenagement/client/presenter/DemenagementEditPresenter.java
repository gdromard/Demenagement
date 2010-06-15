package net.dromard.demenagement.client.presenter;

import java.util.List;

import net.dromard.demenagement.client.event.demenagement.DemenagementEventBus;
import net.dromard.demenagement.client.event.demenagement.DemenagementEventHandler;
import net.dromard.demenagement.client.resources.ClientMessages;
import net.dromard.demenagement.client.view.DemenagementEditView;
import net.dromard.demenagement.shared.model.Carton;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.model.Destination;
import net.dromard.demenagement.shared.services.CartonService;
import net.dromard.demenagement.shared.services.CartonServiceAsync;
import net.dromard.demenagement.shared.services.DemenagementService;
import net.dromard.demenagement.shared.services.DemenagementServiceAsync;
import net.dromard.demenagement.shared.services.DestinationService;
import net.dromard.demenagement.shared.services.DestinationServiceAsync;
import net.dromard.mvp.client.DefaultPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;

public class DemenagementEditPresenter extends DefaultPresenter<DemenagementEditView, DemenagementEventBus> implements DemenagementEventHandler {
    private final ClientMessages myMessages = GWT.create(ClientMessages.class);

    private final DemenagementServiceAsync demenagementService = (DemenagementServiceAsync) GWT.create(DemenagementService.class);

    private final CartonServiceAsync cartonService = (CartonServiceAsync) GWT.create(CartonService.class);

    private final DestinationServiceAsync destinationService = (DestinationServiceAsync) GWT.create(DestinationService.class);

    private List<Destination> destinations = null;

    private final PopupPanel popup = new PopupPanel(true, true);

    @Override
    public void bind() {
        destinationService.getList(new AsyncCallback<List<Destination>>() {
            public void onFailure(Throwable caught) {
                getView().getErrorLabel().setText(myMessages.errorActionFailed(myMessages.actionLoad()));
            }

            public void onSuccess(List<Destination> result) {
                destinations = result;
            }
        });
        getView().getCancelButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popup.hide();
            }
        });
        popup.setWidget(getView().getWidget());
    }

    @Override
    public void onCreate(final Demenagement demenagement) {
        // Show create popup
        getView().setAddingMode();
        populateForm(demenagement);
        getView().getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                demenagement.setDate(getView().getDateBox().getValue());
                demenagementService.add(demenagement, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        getView().getErrorLabel().setText(myMessages.errorActionFailed(myMessages.actionSave()));
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            getEventBus().fireCreatedDemenagement(demenagement);
                            popup.hide();
                        } else {
                            getView().getErrorLabel().setText(myMessages.errorServerDidNotSucceed());
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onEdit(final Demenagement demenagement) {
        // Show create popup
        getView().setEditMode();
        populateForm(demenagement);
        getView().getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                demenagement.setDate(getView().getDateBox().getValue());
                demenagementService.save(demenagement, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        getView().getErrorLabel().setText(myMessages.errorActionFailed(myMessages.actionSave()));
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            getEventBus().fireUpdatedDemenagement(demenagement);
                        } else {
                            getView().getErrorLabel().setText(myMessages.errorServerDidNotSucceed());
                        }
                    }
                });
            }
        });
        getView().getDeleteButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                demenagementService.remove(demenagement, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        getView().getErrorLabel().setText(myMessages.errorActionFailed(myMessages.actionDelete()));
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            getEventBus().fireDeletedDemenagement(demenagement);
                            popup.hide();
                        } else {
                            getView().getErrorLabel().setText(myMessages.errorServerDidNotSucceed());
                        }
                    }
                });
            }
        });
    }

    private void populateForm(final Demenagement demenagement) {
        getView().getDateBox().setValue(demenagement.getDate());
        cartonService.getList(new AsyncCallback<List<Carton>>() {
            public void onFailure(Throwable caught) {
                getView().getErrorLabel().setText(myMessages.errorActionFailed(myMessages.actionLoad()));
            }

            public void onSuccess(List<Carton> result) {
                for (int i = 0; i < result.size(); i++) {
                    getView().addCarton(result.get(i), getDestination(result.get(i).getPrimaryDestination()), getDestination(result.get(i).getSecondaryDestination()));
                }
            }
        });
        popup.center();
        popup.show();
    }

    private Destination getDestination(int id) {
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
    public void onDelete(Demenagement model) {
        // Nothing to do !
    }

    @Override
    public void onSelect(int rowIndex, Demenagement model) {
        // Nothing to do !
    }

    @Override
    public void onUnselect(int rowIndex, Demenagement model) {
        // Nothing to do !
    }

    @Override
    public void onUpdated(Demenagement model) {
        // Nothing to do !
    }

    @Override
    public void onDeleted(Demenagement model) {
        // Nothing to do !
    }

    @Override
    public void onCreated(Demenagement model) {
        // Nothing to do !
    }
}
