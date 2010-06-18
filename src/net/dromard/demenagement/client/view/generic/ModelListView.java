package net.dromard.demenagement.client.view.generic;

import net.dromard.demenagement.client.resources.ClientBundles;
import net.dromard.demenagement.client.resources.ClientMessages;
import net.dromard.demenagement.client.resources.Style;
import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.View;
import net.dromard.widget.client.ButtonWidget;
import net.dromard.widget.client.LabelWidget;
import net.dromard.widget.client.TableWidget;
import net.dromard.widget.client.impl.GwtButtonWidget;
import net.dromard.widget.client.impl.GwtLabelWidget;
import net.dromard.widget.client.impl.GwtTableWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class ModelListView<M extends Model> extends Composite implements View {
    protected static final ClientMessages MSG = GWT.create(ClientMessages.class);

    protected static final Style STYLES = ClientBundles.INSTANCE.style();

    private final LabelWidget errorLabel = new GwtLabelWidget("");

    private final ButtonWidget delete = new GwtButtonWidget(MSG.actionDelete());

    private final ButtonWidget newButton = new GwtButtonWidget(MSG.actionAdd());

    private final ButtonWidget yes = new GwtButtonWidget(MSG.buttonYes());

    private final ButtonWidget no = new GwtButtonWidget(MSG.buttonNo());

    private final PopupPanel popup = new PopupPanel(true, true);

    private final GwtTableWidget list = new GwtTableWidget();

    public ModelListView() {
        HorizontalPanel confirmButtons = new HorizontalPanel();
        confirmButtons.add(yes.getWidget());
        confirmButtons.add(no.getWidget());
        VerticalPanel confirmPanel = new VerticalPanel();
        GwtLabelWidget confirmMessage = new GwtLabelWidget(MSG.questionAreYouSure());
        confirmMessage.addStyleName(STYLES.confirmPopupMessage());
        confirmPanel.add(confirmMessage);
        confirmPanel.add(confirmButtons);
        confirmPanel.setCellHorizontalAlignment(confirmButtons, HorizontalPanel.ALIGN_RIGHT);
        popup.add(confirmPanel);
        popup.center();

        HorizontalPanel buttons = new HorizontalPanel();
        buttons.setSpacing(4);
        buttons.add(delete.getWidget());
        buttons.add(newButton.getWidget());

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(list.getWidget());
        mainPanel.add(errorLabel.getWidget());
        mainPanel.add(buttons);

        errorLabel.setStyleName(STYLES.labelColorError());

        initWidget(mainPanel);
    }

    public ButtonWidget getDeleteButton() {
        return delete;
    }

    public TableWidget getTable() {
        return list;
    }

    public ButtonWidget getNewButton() {
        return newButton;
    }

    public void setConfirmationVisible(boolean visible) {
        if (visible) {
            popup.show();
        } else {
            popup.hide();
        }
    }

    public ButtonWidget getNoButton() {
        return no;
    }

    public ButtonWidget getYesButton() {
        return yes;
    }

    public LabelWidget getErrorLabel() {
        return errorLabel;
    }

    @Override
    public Widget getWidget() {
        return this;
    }
}
