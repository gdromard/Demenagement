package net.dromard.demenagement.client.presenter.generic;

import net.dromard.demenagement.client.event.generic.ModelEventBus;
import net.dromard.demenagement.client.event.generic.ModelEventHandler;
import net.dromard.demenagement.client.resources.ClientMessages;
import net.dromard.demenagement.client.view.generic.ModelEditView;
import net.dromard.demenagement.shared.model.Model;
import net.dromard.demenagement.shared.services.AbstractServiceAsync;
import net.dromard.mvp.client.DefaultPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;

public abstract class ModelEditPresenter<M extends Model, V extends ModelEditView> extends DefaultPresenter<V, ModelEventBus<M>> implements ModelEventHandler<M> {
    protected static final ClientMessages MSG = GWT.create(ClientMessages.class);

    private final AbstractServiceAsync<M> service = setService();

    private final PopupPanel popup = new PopupPanel(true, true);

    private boolean addingMode = true;

    protected abstract AbstractServiceAsync<M> setService();

    protected abstract M createAndPopulateModel() throws Exception;

    protected abstract void populateForm(final M model);

    @Override
    public void bind() {
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
                    try {
                        final M model = createAndPopulateModel();
                        service.add(model, new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                getView().getErrorLabel().setText(MSG.errorActionFailed(MSG.actionSave()));
                            }

                            @Override
                            public void onSuccess(Boolean result) {
                                if (result) {
                                    getEventBus().fireCreated(model);
                                    popup.hide();
                                } else {
                                    getView().getErrorLabel().setText(MSG.errorServerDidNotSucceed());
                                }
                            }
                        });
                    } catch (Exception e) {
                        getView().getErrorLabel().setText(MSG.errorActionFailed(MSG.actionSave()));
                    }
                } else {
                    try {
                        final M model = createAndPopulateModel();
                        service.save(model, new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                getView().getErrorLabel().setText(MSG.errorActionFailed(MSG.actionSave()));
                            }

                            @Override
                            public void onSuccess(Boolean result) {
                                if (result) {
                                    getEventBus().fireUpdated(model);
                                    popup.hide();
                                } else {
                                    getView().getErrorLabel().setText(MSG.errorServerDidNotSucceed());
                                }
                            }
                        });
                    } catch (Exception e) {
                        getView().getErrorLabel().setText(MSG.errorActionFailed(MSG.actionSave()));
                    }
                }
            }
        });
    }

    public void setAddingMode() {
        getView().getErrorLabel().setText("");
        getView().getSaveButton().setText(MSG.actionAdd());
        getView().getSaveButton().setEnabled(true);
        addingMode = true;
    }

    public void setEditMode() {
        getView().getErrorLabel().setText("");
        getView().getSaveButton().setText(MSG.actionSave());
        getView().getSaveButton().setEnabled(false);
        addingMode = false;
    }

    @Override
    public void onCreate(final M model) {
        // Show create popup
        setAddingMode();
        populateForm(model);
        popup.center();
        popup.show();
    }

    @Override
    public void onEdit(final M model) {
        // Show create popup
        setEditMode();
        populateForm(model);
        popup.center();
        popup.show();
    }

    @Override
    public void onDelete(M model) {
    }

    @Override
    public void onSelect(int rowIndex, M model) {
    }

    @Override
    public void onUnselect(int rowIndex, M model) {
    }

    @Override
    public void onUpdated(M model) {
    }

    @Override
    public void onDeleted(M model) {
    }

    @Override
    public void onCreated(M model) {
    }

    @Override
    public void onListChanged(Class<? extends Model> clazz) {
    }
}
