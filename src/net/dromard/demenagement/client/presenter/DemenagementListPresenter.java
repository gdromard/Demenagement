package net.dromard.demenagement.client.presenter;

import java.util.List;

import net.dromard.demenagement.client.event.demenagement.DemenagementEventBus;
import net.dromard.demenagement.client.event.demenagement.DemenagementEventHandler;
import net.dromard.demenagement.client.view.DemenagementListView;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.services.DemenagementService;
import net.dromard.demenagement.shared.services.DemenagementServiceAsync;
import net.dromard.mvp.client.DefaultPresenter;
import net.dromard.widget.client.ButtonWidget;
import net.dromard.widget.client.TableWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DemenagementListPresenter extends DefaultPresenter<DemenagementListView, DemenagementEventBus> implements DemenagementEventHandler {

    protected int indexSelected = 0;

    protected List<Demenagement> list = null;

    private final DemenagementServiceAsync service = (DemenagementServiceAsync) GWT.create(DemenagementService.class);

    @Override
    public void bind() {
        ButtonWidget delete = getView().getDeleteButton();
        delete.setEnabled(false);
        delete.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                setVisibleConfirmDeletion(true);
            }
        });
        getView().getNewButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getEventBus().createDemenagement(new Demenagement());
            }
        });
        TableWidget table = getView().getTable();
        table.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                TableWidget table = getView().getTable();
                int rowIndex = table.getRowForEvent(event);
                if (rowIndex > 0) {
                    getEventBus().selectDemenagement(rowIndex - 1, list.get(rowIndex - 1));
                    getEventBus().editDemenagement(list.get(rowIndex - 1));
                }
            }
        });
        table.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                TableWidget table = getView().getTable();
                int rowIndex = table.getRowForEvent(event);
                if (rowIndex > 0) {
                    getEventBus().selectDemenagement(rowIndex - 1, list.get(rowIndex - 1));
                }
            }
        });

        getView().getYesButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                deleteDemenagement();
            }
        });
        getView().getNoButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                setVisibleConfirmDeletion(false);
            }
        });
        setVisibleConfirmDeletion(false);

        reload();
        getEventBus().getParentBus().changeLeftWidget(getView());
    }

    private void reload() {
        service.getList(new AsyncCallback<List<Demenagement>>() {
            public void onFailure(Throwable caught) {
                Window.alert("Ooops !!");
            }

            public void onSuccess(List<Demenagement> result) {
                list = result;
                int nbUsers = result.size();
                for (int i = 0; i < nbUsers; i++) {
                    displayDemenagement(list.get(i), i + 1);
                }
            }
        });
    }

    private void deleteDemenagement() {
        service.remove(list.get(indexSelected - 1), new AsyncCallback<Boolean>() {
            public void onFailure(Throwable caught) {
                Window.alert("deleteDemenagement failed !!");
            }

            public void onSuccess(Boolean result) {
                list.remove(indexSelected - 1);
                getView().getTable().removeRow(indexSelected);
                getView().getDeleteButton().setEnabled(false);
                setVisibleConfirmDeletion(false);
            }
        });
    }

    private void displayDemenagement(Demenagement demenagement, int row) {
        TableWidget table = getView().getTable();
        table.setText(row, 0, "" + demenagement.getId());
        table.setText(row, 1, "" + demenagement.getDate());
    }

    private void setVisibleConfirmDeletion(boolean visible) {
        getView().getConfirmText().setVisible(visible);
        getView().getYesButton().setVisible(visible);
        getView().getNoButton().setVisible(visible);
    }

    @Override
    public void onCreate(Demenagement model) {

    }

    @Override
    public void onDelete(Demenagement model) {

    }

    @Override
    public void onEdit(Demenagement model) {

    }

    @Override
    public void onSelect(int rowIndex, Demenagement model) {
        TableWidget table = getView().getTable();
        if (indexSelected > -1) {
            table.unSelectRow(indexSelected);
        }
        indexSelected = rowIndex;
        table.selectRow(indexSelected);
        getView().getDeleteButton().setEnabled(true);
    }

    @Override
    public void onUnselect(int rowIndex, Demenagement model) {
        getView().getTable().unSelectRow(rowIndex);
        indexSelected = 0;
    }

    @Override
    public void onUpdated(Demenagement model) {
        displayDemenagement(model, list.indexOf(model) + 1);
    }

    @Override
    public void onCreated(Demenagement model) {
        reload();
    }

    @Override
    public void onDeleted(Demenagement model) {
        reload();
    }
}
