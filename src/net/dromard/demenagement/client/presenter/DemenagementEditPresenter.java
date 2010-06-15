package net.dromard.demenagement.client.presenter;

import java.util.Date;
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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;

public class DemenagementEditPresenter extends DefaultPresenter<DemenagementEditView, DemenagementEventBus> implements DemenagementEventHandler {
    private final ClientMessages myMessages = GWT.create(ClientMessages.class);

    private final DemenagementServiceAsync demenagementService = (DemenagementServiceAsync) GWT.create(DemenagementService.class);

    private final CartonServiceAsync cartonService = (CartonServiceAsync) GWT.create(CartonService.class);

    private final DestinationServiceAsync destinationService = (DestinationServiceAsync) GWT.create(DestinationService.class);

    private List<Destination> destinations = null;

    private final PopupPanel popup = new PopupPanel(true, true);

    private boolean addingMode = true;

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

        getView().getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (addingMode) {
                    final Demenagement demenagement = new Demenagement();
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
                } else {
                    final Demenagement demenagement = new Demenagement();
                    demenagement.setId(getView().getIdBox().getId());
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
                                popup.hide();
                            } else {
                                getView().getErrorLabel().setText(myMessages.errorServerDidNotSucceed());
                            }
                        }
                    });

                }
            }
        });
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

    public void setAddingMode() {
        getView().getErrorLabel().setText("");
        getView().getSaveButton().setText(myMessages.actionAdd());
        addingMode = true;
    }

    public void setEditMode() {
        getView().getErrorLabel().setText("");
        getView().getSaveButton().setText(myMessages.actionSave());
        addingMode = false;
    }

    @Override
    public void onCreate(final Demenagement demenagement) {
        // Show create popup
        setAddingMode();
        populateForm(demenagement);
    }

    @Override
    public void onEdit(final Demenagement demenagement) {
        // Show create popup
        setEditMode();
        populateForm(demenagement);
    }

    private void populateForm(final Demenagement demenagement) {
        getView().getIdBox().setId(demenagement.getId());
        getView().getDateBox().setValue(demenagement.getDate());
        getView().getSaveButton().setEnabled(false);
        cartonService.getList(new AsyncCallback<List<Carton>>() {
            public void onFailure(Throwable caught) {
                getView().getErrorLabel().setText(myMessages.errorActionFailed(myMessages.actionLoad()));
            }

            public void onSuccess(List<Carton> result) {
                getView().getCartonTable().clear();
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
