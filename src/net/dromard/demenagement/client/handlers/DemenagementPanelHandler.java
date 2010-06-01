package net.dromard.demenagement.client.handlers;

import java.util.List;

import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.services.DemenagementService;
import net.dromard.demenagement.shared.services.DemenagementServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

    public Widget buildList(List<Demenagement> demenagements) {
        VerticalPanel widget = new VerticalPanel();

        final DemenagementList demenagementsList = new DemenagementList();
        int i = 0;
        Label header = new Label("Demenagements");
        demenagementsList.setWidget(i++, 0, header);
        for (Demenagement demenagement : demenagements) {
            demenagementsList.add(demenagement);
        }

        HorizontalPanel buttons = new HorizontalPanel();
        Button addBtn = new Button("+");
        addBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                showCreateForm(demenagementsList);
            }
        });
        buttons.add(addBtn);

        widget.add(demenagementsList);
        widget.add(buttons);
        return widget;
    }

    private class DemenagementList extends FlexTable {
        public void load(List<Demenagement> demenagements) {
            clear(true);
            int i = 0;
            Label header = new Label("Demenagements");
            setWidget(i++, 0, header);
            for (Demenagement demenagement : demenagements) {
                add(demenagement);
            }
        }

        public void reload() {
            /*
            clear(true);
            int i = 0;
            Label header = new Label("Demenagements");
            setWidget(i++, 0, header);
            */
        }

        public void add(final Demenagement demenagement) {
            Label line = new Label(demenagement.getDate().toString());
            if (getRowCount() % 2 == 0) {
                line.addStyleName("odd");
            }
            final DemenagementList me = this;

            line.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    showEditForm(me, demenagement);
                }
            });
            setWidget(getRowCount(), 0, line);
        }
    }

    public void showEditForm(final DemenagementList demenagementsList, final Demenagement demenagement) {
        final PopupPanel popup = new PopupPanel(true, true);
        VerticalPanel vPanel = new VerticalPanel();

        Grid form = new Grid(3, 2);
        form.setWidget(0, 0, new Label("Date"));
        DateBox dateBox = new DateBox();
        dateBox.setValue(demenagement.getDate());
        form.setWidget(0, 1, dateBox);
        form.setWidget(1, 0, new Label("Cartons"));
        FlexTable cartonsList = new FlexTable();
        if (demenagement.getCartons() != null) {
            for (int i = 0; i < demenagement.getCartons().size(); ++i) {
                cartonsList.setWidget(i, 0, new Label("Carton n°"));
                cartonsList.setWidget(i, 1, new Label(demenagement.getCartons().get(i).getNumero() + ""));
            }
        }
        form.setWidget(1, 1, cartonsList);

        HorizontalPanel btnPanel = new HorizontalPanel();
        Button saveButton = new Button("Save");
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                /*
                demenagementService.saveDemenagement(demenagement, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Ooops save action failed !");
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            demenagementsList.reload(demenagements);
                            popup.removeFromParent();
                        } else {
                            Window.alert("Ooops server response says that it did not succeed !");
                        }
                    }
                });
                */
            }
        });

        Button addButton = new Button("Add");
        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            }
        });
        /*
                Button deleteButton = new Button("Delete");
                deleteButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        demenagementService.removeDemenagement(demenagement, new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                Window.alert("Ooops delete action failed !");
                            }

                            @Override
                            public void onSuccess(Boolean result) {
                                if (result) {
                                    demenagementsList.reload();
                                    popup.removeFromParent();
                                } else {
                                    Window.alert("Ooops server response says that it did not succeed !");
                                }
                            }
                        });
                        popup.removeFromParent();
                    }
                });
        */
        Button closeButton = new Button("Cancel");
        closeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popup.removeFromParent();
            }
        });

        btnPanel.add(saveButton);
        btnPanel.add(addButton);
        //btnPanel.add(deleteButton);
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
        form.setWidget(0, 0, new Label("Date"));
        final DateBox dateBox = new DateBox();
        dateBox.setValue(demenagement.getDate());
        form.setWidget(0, 1, dateBox);
        form.setWidget(1, 0, new Label("Cartons"));
        FlexTable cartonsList = new FlexTable();
        if (demenagement.getCartons() != null) {
            for (int i = 0; i < demenagement.getCartons().size(); ++i) {
                cartonsList.setWidget(i, 0, new Label("Carton n°"));
                cartonsList.setWidget(i, 1, new Label(demenagement.getCartons().get(i).getNumero() + ""));
            }
        }
        Button addButton = new Button("Add");
        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                demenagement.setDate(dateBox.getValue());
                demenagementService.addDemenagement(demenagement, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        form.setWidget(3, 0, new Label("Ooops save action failed !"));
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            demenagementsList.add(demenagement);
                            popup.removeFromParent();
                        } else {
                            form.setWidget(3, 0, new Label("Ooops server response says that it did not succeed !"));
                        }
                    }
                });
            }
        });
        Button closeButton = new Button("Cancel");
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
