package net.dromard.demenagement.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WidgetLibsExamples implements EntryPoint {

    static int count = 0;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        TabPanel tabs = new TabPanel();
        tabs.add(getButton(), "Button");
        tabs.add(getTextBox(), "Text Box");
        tabs.add(getListBox(), "List Box + Menu Bar");
        tabs.add(getSuggestBox(), "Suggest Box");
        tabs.add(getDatePicker(), "Date Picker");
        tabs.add(getPanel(), "Panel");

        tabs.selectTab(0);
        tabs.setAnimationEnabled(true);
        RootPanel.get().add(tabs);

    }

    private Widget getButton() {
        VerticalPanel layout = new VerticalPanel();
        final Label l = new Label("Mon Label");
        final Button b = new Button("Changer Libellé");
        b.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                l.setText("Libellé modifié " + count + " fois.");
                if (count % 2 == 0) {
                    b.addStyleName("red");
                } else {
                    b.removeStyleName("red");
                }
                ++count;
            }
        });
        layout.add(l);
        layout.add(b);
        return layout;
    }

    private Widget getTextBox() {
        VerticalPanel layout = new VerticalPanel();
        TextBox t = new TextBox();
        t.setAccessKey('o');
        t.setMaxLength(10);
        t.setText("My Text Field");
        layout.add(t);
        layout.add(new TextArea());
        layout.add(new PasswordTextBox());
        return layout;
    }

    private Widget getListBox() {
        Label label = new Label("List");
        ListBox list = new ListBox();
        list.addItem("Item 1");
        list.addItem("Item 2");
        list.addItem("Item 3");

        HorizontalPanel hlayout = new HorizontalPanel();
        VerticalPanel vlayout = new VerticalPanel();

        hlayout.add(label);
        hlayout.add(list);

        vlayout.add(getMenuBar());
        vlayout.add(hlayout);
        return vlayout;
    }

    private Widget getMenuBar() {
        Command cmd = new Command() {
            @Override
            public void execute() {
                Window.alert("You seleted a menu item !");
            }
        };

        MenuBar fooMenu = new MenuBar(true);
        fooMenu.addItem("The", cmd);
        fooMenu.addItem("foo", cmd);
        fooMenu.addItem("menu", cmd);

        MenuBar menu = new MenuBar();
        menu.addItem("File", fooMenu);
        menu.addItem("Edit", fooMenu);
        menu.addItem("Help", fooMenu);

        return menu;
    }

    private Widget getSuggestBox() {
        MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();

        oracle.add("Gabriel");
        oracle.add("Matthieu");
        oracle.add("Rodolphe");
        oracle.add("Guilhem");
        oracle.add("Olivier");
        oracle.add("Jean-François");

        SuggestBox box = new SuggestBox(oracle);

        HorizontalPanel hlayout = new HorizontalPanel();
        hlayout.add(box);
        return hlayout;
    }

    private Widget getDatePicker() {
        DateBox dateBox = new DateBox();

        return dateBox;
    }

    private Widget getPanel() {
        DockPanel panel = new DockPanel();

        final Button okBtn = new Button("Ok");
        okBtn.setEnabled(false);
        Button cancelBtn = new Button("Cancel");
        HorizontalPanel southPanel = new HorizontalPanel();
        southPanel.add(okBtn);
        southPanel.add(cancelBtn);

        VerticalPanel centerPanel = new VerticalPanel();
        final FlexTable form = new FlexTable();
        form.setWidget(0, 0, new Label("Login:"));
        final TextBox login = new TextBox();
        form.setWidget(0, 1, login);
        form.setWidget(1, 0, new Label("Password (same):"));
        final PasswordTextBox password = new PasswordTextBox();
        form.setWidget(1, 1, password);
        centerPanel.add(form);

        login.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                okBtn.setEnabled(login.getValue().equals(password.getText()));
                form.removeRow(2);
            }
        });
        password.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                okBtn.setEnabled(password.getValue().equals(login.getText()));
                if (form.getRowCount() >= 2) {
                    form.removeRow(2);
                }
            }
        });

        panel.add(new Label("North"), DockPanel.NORTH);
        panel.add(centerPanel, DockPanel.CENTER);
        panel.add(southPanel, DockPanel.SOUTH);
        panel.setCellHorizontalAlignment(southPanel, HorizontalPanel.ALIGN_CENTER);
        panel.setCellVerticalAlignment(centerPanel, HasVerticalAlignment.ALIGN_MIDDLE);
        panel.setCellHorizontalAlignment(centerPanel, HorizontalPanel.ALIGN_CENTER);
        panel.setCellHeight(centerPanel, "300px");

        okBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                form.setWidget(2, 0, new Label("Votre login: "));
                form.setWidget(2, 1, new Label(login.getText()));
            }
        });

        cancelBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                login.setText("");
                password.setText("");
                if (form.getRowCount() >= 2) {
                    form.removeRow(2);
                }
            }
        });

        return panel;
    }
}
