package net.dromard.demenagement.client.view;

import net.dromard.mvp.client.View;
import net.dromard.widget.client.ButtonWidget;
import net.dromard.widget.client.LabelWidget;
import net.dromard.widget.client.TableWidget;
import net.dromard.widget.client.impl.GwtButtonWidget;
import net.dromard.widget.client.impl.GwtLabelWidget;
import net.dromard.widget.client.impl.GwtTableWidget;

import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DemenagementListView extends Composite implements View {

    private final GwtButtonWidget delete = new GwtButtonWidget("Delete");

    private final GwtButtonWidget newButton = new GwtButtonWidget("New");

    private final GwtButtonWidget yes = new GwtButtonWidget("Yes");

    private final GwtButtonWidget no = new GwtButtonWidget("No");

    private final GwtLabelWidget confirmText = new GwtLabelWidget("Are you Sure?");

    private final GwtTableWidget demenagementList = new GwtTableWidget();

    public DemenagementListView() {

        CaptionPanel cpPanel = new CaptionPanel("Demenagement");

        HorizontalPanel buttons = new HorizontalPanel();
        buttons.setSpacing(4);
        buttons.add(delete);
        buttons.add(newButton);
        buttons.add(confirmText);
        buttons.add(yes);
        buttons.add(no);

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(demenagementList);
        mainPanel.add(buttons);
        mainPanel.setStyleName("grid-container");

        demenagementList.setStyleName("grid");
        demenagementList.setCellPadding(0);
        demenagementList.setCellSpacing(0);
        /**/
        demenagementList.getRowFormatter().setStyleName(0, "header");
        demenagementList.setText(0, 0, "ID");
        demenagementList.setText(0, 1, "Date");
        /**/

        cpPanel.add(mainPanel);

        initWidget(cpPanel);
    }

    public ButtonWidget getDeleteButton() {
        return delete;
    }

    public TableWidget getTable() {
        return demenagementList;
    }

    public ButtonWidget getNewButton() {
        return newButton;
    }

    public LabelWidget getConfirmText() {
        return confirmText;
    }

    public ButtonWidget getNoButton() {
        return no;
    }

    public ButtonWidget getYesButton() {
        return yes;
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
