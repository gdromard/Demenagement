package net.dromard.demenagement.client.handlers;

import java.util.List;

import net.dromard.demenagement.client.MyMessages;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.services.DemenagementService;
import net.dromard.demenagement.shared.services.DemenagementServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class DemenagementPanelHandler {
    private final DemenagementServiceAsync demenagementService = GWT.create(DemenagementService.class);

    private final MyMessages myMessages = GWT.create(MyMessages.class);;

    public Widget buildList() {
        VerticalPanel widget = new VerticalPanel();

        final DemenagementList demenagementsList = new DemenagementList();
        demenagementsList.reload();

        HorizontalPanel buttons = new HorizontalPanel();
        Button addBtn = new Button(myMessages.actionAdd());
        addBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                showCreateForm(demenagementsList);
            }
        });
        buttons.add(addBtn);

        Button reloadBtn = new Button(myMessages.actionReaload());
        reloadBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                demenagementsList.reload();
            }
        });
        buttons.add(reloadBtn);

        widget.add(demenagementsList);
        widget.add(buttons);
        return widget;
    }

    private class DemenagementList extends FlexTable {
        public void reload() {
            final DemenagementList me = this;
            demenagementService.getDemenagements(new AsyncCallback<List<Demenagement>>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert(myMessages.errorActionFailed(myMessages.actionReaload()));
                }

                @Override
                public void onSuccess(List<Demenagement> result) {
                    removeAllRows();
                    Label header = new Label(myMessages.labelDemenagements());
                    setWidget(0, 0, header);
                    for (final Demenagement demenagement : result) {
                        Label line = new Label(demenagement.getId() + " - " + demenagement.getDate());
                        if (getRowCount() % 2 == 0) {
                            line.addStyleName(myMessages.cssClassOdd());
                        }

                        line.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(ClickEvent event) {
                                showEditForm(me, demenagement);
                            }
                        });
                        setWidget(getRowCount(), 0, line);
                    }
                }
            });
        }
    }

    public void showEditForm(final DemenagementList demenagementsList, final Demenagement demenagement) {
        final PopupPanel popup = new PopupPanel(true, true);
        VerticalPanel vPanel = new VerticalPanel();

        Grid form = new Grid(3, 2);
        form.setWidget(0, 0, new Label(myMessages.labelDate()));
        final DateBox dateBox = new DateBox();
        dateBox.setValue(demenagement.getDate());
        form.setWidget(0, 1, dateBox);
        form.setWidget(1, 0, new Label(myMessages.labelCartons()));
        FlexTable cartonsList = new FlexTable();
        if (demenagement.getCartons() != null) {
            for (int i = 0; i < demenagement.getCartons().size(); ++i) {
                cartonsList.setWidget(i, 0, new Label(myMessages.labelCartonNumber()));
                cartonsList.setWidget(i, 1, new Label(demenagement.getCartons().get(i).getNumero() + ""));
            }
        }
        form.setWidget(1, 1, cartonsList);

        HorizontalPanel btnPanel = new HorizontalPanel();
        Button saveButton = new Button(myMessages.actionSave());
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                demenagement.setDate(dateBox.getValue());
                demenagementService.saveDemenagement(demenagement, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(myMessages.errorActionFailed(myMessages.actionSave()));
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            demenagementsList.reload();
                            popup.removeFromParent();
                        } else {
                            Window.alert(myMessages.errorServerDidNotSucceed());
                        }
                    }
                });
            }
        });

        Button addButton = new Button(myMessages.actionAdd());
        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            }
        });
        Button deleteButton = new Button(myMessages.actionDelete());
        deleteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                demenagementService.removeDemenagement(demenagement, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(myMessages.errorActionFailed(myMessages.actionSave()));
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            demenagementsList.reload();
                            popup.removeFromParent();
                        } else {
                            Window.alert(myMessages.errorServerDidNotSucceed());
                        }
                    }
                });
                popup.removeFromParent();
            }
        });
        Button closeButton = new Button(myMessages.actionCancel());
        closeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popup.removeFromParent();
            }
        });

        btnPanel.add(saveButton);
        btnPanel.add(addButton);
        btnPanel.add(deleteButton);
        btnPanel.add(closeButton);

        vPanel.add(form);
        vPanel.add(btnPanel);
        vPanel.setCellHorizontalAlignment(btnPanel, HorizontalPanel.ALIGN_CENTER);

        popup.add(vPanel);
        popup.show();
    }

    public void showCreateForm(final DemenagementList demenagementsList) {
        final PopupPanel popup = new PopupPanel(true, true);

        final Demenagement demenagement = new Demenagement();
        final Grid form = new Grid(4, 2);
        form.setWidget(0, 0, new Label(myMessages.labelDate()));
        final DateBox dateBox = new DateBox();
        dateBox.setValue(demenagement.getDate());
        form.setWidget(0, 1, dateBox);
        form.setWidget(1, 0, new Label(myMessages.labelCartons()));
        FlexTable cartonsList = new FlexTable();
        if (demenagement.getCartons() != null) {
            for (int i = 0; i < demenagement.getCartons().size(); ++i) {
                cartonsList.setWidget(i, 0, new Label(myMessages.labelCartonNumber()));
                cartonsList.setWidget(i, 1, new Label(demenagement.getCartons().get(i).getNumero() + ""));
            }
        }
        Button addButton = new Button(myMessages.actionAdd());
        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                demenagement.setDate(dateBox.getValue());
                demenagementService.addDemenagement(demenagement, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        form.setWidget(3, 0, new Label(myMessages.errorActionFailed(myMessages.actionSave())));
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            demenagementsList.reload();
                            popup.removeFromParent();
                        } else {
                            form.setWidget(3, 0, new Label(myMessages.errorServerDidNotSucceed()));
                        }
                    }
                });
            }
        });
        Button closeButton = new Button(myMessages.actionCancel());
        closeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popup.removeFromParent();
            }
        });

        form.setWidget(1, 1, cartonsList);
        form.setWidget(3, 0, addButton);
        form.setWidget(3, 1, closeButton);
        popup.add(form);
        popup.show();
    }
}
