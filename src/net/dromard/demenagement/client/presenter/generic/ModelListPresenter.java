package net.dromard.demenagement.client.presenter.generic;

import java.util.List;

import net.dromard.demenagement.client.event.generic.ModelEventBus;
import net.dromard.demenagement.client.event.generic.ModelEventHandler;
import net.dromard.demenagement.client.view.generic.ModelListView;
import net.dromard.demenagement.shared.model.Model;
import net.dromard.demenagement.shared.services.AbstractServiceAsync;
import net.dromard.mvp.client.DefaultPresenter;
import net.dromard.widget.client.ButtonWidget;
import net.dromard.widget.client.TableWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ModelListPresenter<M extends Model> extends DefaultPresenter<ModelListView<M>, ModelEventBus<M>> implements ModelEventHandler<M> {

    private final AbstractServiceAsync<M> service = setService();

    protected int indexSelected = 0;

    protected List<M> list = null;

    protected abstract AbstractServiceAsync<M> setService();

    protected abstract M createModel();

    protected abstract void displayModels(List<M> models);

    @Override
    public void bind() {
        ButtonWidget delete = getView().getDeleteButton();
        delete.setEnabled(false);
        delete.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getView().setConfirmationVisible(true);
            }
        });
        getView().getNewButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getEventBus().create(createModel());
            }
        });
        TableWidget table = getView().getTable();
        table.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                TableWidget table = getView().getTable();
                int rowIndex = table.getRowForEvent(event);
                if (rowIndex > 0) {
                    getEventBus().edit(list.get(rowIndex - 1));
                    onSelect(rowIndex - 1, list.get(rowIndex - 1));
                }
            }
        });
        table.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                TableWidget table = getView().getTable();
                int rowIndex = table.getRowForEvent(event);
                if (rowIndex > 0) {
                    if (indexSelected == rowIndex) {
                        getEventBus().fireUnselect(rowIndex - 1, list.get(rowIndex - 1));
                    } else {
                        getEventBus().fireSelect(rowIndex - 1, list.get(rowIndex - 1));
                    }
                }
            }
        });

        getView().getYesButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                deleteModel();
            }
        });
        getView().getNoButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getView().setConfirmationVisible(false);
            }
        });
        getView().setConfirmationVisible(false);

        reload();
    }

    private void reload() {
        service.getList(new AsyncCallback<List<M>>() {
            public void onFailure(Throwable caught) {
                Window.alert("Ooops !!");
            }

            public void onSuccess(List<M> result) {
                list = result;
                getView().getTable().clear();
                displayModels(list);
                if (indexSelected > 0) {
                    getEventBus().fireUnselect(indexSelected - 1, list.get(indexSelected - 1));
                }
            }
        });
    }

    private void deleteModel() {
        service.remove(list.get(indexSelected - 1), new AsyncCallback<Boolean>() {
            public void onFailure(Throwable caught) {
                Window.alert("deleteModel failed !!");
            }

            public void onSuccess(Boolean result) {
                getView().getDeleteButton().setEnabled(false);
                getView().setConfirmationVisible(false);
                getView().getTable().removeRow(indexSelected);
                getEventBus().fireDeleted(list.get(indexSelected - 1));
            }
        });
    }

    @Override
    public void onCreate(M model) {
    }

    @Override
    public void onDelete(M model) {
    }

    @Override
    public void onEdit(M model) {
    }

    @Override
    public void onSelect(int rowIndex, M model) {
        TableWidget table = getView().getTable();
        if (indexSelected > 0) {
            table.unSelectRow(indexSelected);
        }
        indexSelected = rowIndex + 1;
        table.selectRow(indexSelected);
        getView().getDeleteButton().setEnabled(true);
    }

    @Override
    public void onUnselect(int rowIndex, M model) {
        if (indexSelected == rowIndex + 1) {
            indexSelected = 0;
            getView().getDeleteButton().setEnabled(false);
        }
        getView().getTable().unSelectRow(rowIndex + 1);
    }

    @Override
    public void onUpdated(M model) {
        getEventBus().fireListChanged(model.getClass());
    }

    @Override
    public void onCreated(M model) {
        getEventBus().fireListChanged(model.getClass());
    }

    @Override
    public void onDeleted(M model) {
        getEventBus().fireUnselect(indexSelected - 1, list.get(indexSelected - 1));
        getEventBus().fireListChanged(model.getClass());
    }

    @Override
    public void onListChanged(Class<? extends Model> clazz) {
    }
}
