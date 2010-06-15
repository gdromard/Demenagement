package net.dromard.demenagement.client.presenter;

import net.dromard.demenagement.client.event.template.TemplateEventBus;
import net.dromard.demenagement.client.event.template.TemplateEventHandler;
import net.dromard.demenagement.client.view.TemplateView;
import net.dromard.mvp.client.DefaultPresenter;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class TemplatePresenter extends DefaultPresenter<TemplateView, TemplateEventBus> implements TemplateEventHandler {

    @Override
    public void bind() {
        RootPanel.get().add(getView().getWidget());
    }

    @Override
    public void onChangeLeftWidget(Widget widget) {
        getView().setLeftWidget(widget);
    }

    @Override
    public void onChangeRightBottomWidget(Widget widget) {
        getView().setRightBottomWidget(widget);
    }

    @Override
    public void onChangeRightTopWidget(Widget widget) {
        getView().setRightTopWidget(widget);
    }

    @Override
    public void onChangeFooterWidget(Widget widget) {
        getView().setFooterWidget(widget);
    }
}
