package net.dromard.demenagement.client.presenter;

import net.dromard.demenagement.client.event.DemenagementEventBus;
import net.dromard.demenagement.client.event.DemenagementEventHandler;
import net.dromard.demenagement.client.event.template.TemplateEventHandler;
import net.dromard.demenagement.client.view.DebugView;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.model.Model;
import net.dromard.mvp.client.DefaultPresenter;

import com.google.gwt.user.client.ui.Widget;

public class DebugPresenter extends DefaultPresenter<DebugView, DemenagementEventBus> implements TemplateEventHandler, DemenagementEventHandler {

    @Override
    public void bind() {
        getEventBus().getParentBus().changeFooterWidget(getView());
    }

    @Override
    public void onChangeLeftWidget(Widget widget) {
        getView().appendMessage("[DEBUG] onChangeLeftWidget fired");
    }

    @Override
    public void onChangeRightBottomWidget(Widget widget) {
        getView().appendMessage("[DEBUG] onChangeRightBottomWidget fired");
    }

    @Override
    public void onChangeRightTopWidget(Widget widget) {
        getView().appendMessage("[DEBUG] onChangeRightTopWidget fired");
    }

    @Override
    public void onChangeFooterWidget(Widget widget) {
        getView().appendMessage("[DEBUG] onChangeFooterWidget fired");
    }

    @Override
    public void onCreate(Demenagement model) {
        getView().appendMessage("[DEBUG] onCreateDemenagement fired");
    }

    @Override
    public void onDelete(Demenagement model) {
        getView().appendMessage("[DEBUG] onDeleteDemenagement fired");
    }

    @Override
    public void onEdit(Demenagement model) {
        getView().appendMessage("[DEBUG] onEditDemenagement fired");
    }

    @Override
    public void onSelect(int rowIndex, Demenagement model) {
        getView().appendMessage("[DEBUG] onSelectDemenagement fired");
    }

    @Override
    public void onUnselect(int rowIndex, Demenagement model) {
        getView().appendMessage("[DEBUG] onUnselectDemenagement fired");
    }

    @Override
    public void onCreated(Demenagement model) {
        getView().appendMessage("[DEBUG] onDemenagementCreated fired");
    }

    @Override
    public void onUpdated(Demenagement model) {
        getView().appendMessage("[DEBUG] onDemenagementUpdated fired");
    }

    @Override
    public void onDeleted(Demenagement model) {
        getView().appendMessage("[DEBUG] onDemenagementDeleted fired");
    }

    @Override
    public void onListChanged(Class<? extends Model> clazz) {
        getView().appendMessage("[DEBUG] on" + clazz.getName().substring(clazz.getName().lastIndexOf('.') + 1) + "ListChanged fired");
    }
}
