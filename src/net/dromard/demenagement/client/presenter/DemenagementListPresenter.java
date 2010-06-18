package net.dromard.demenagement.client.presenter;

import java.util.List;

import net.dromard.demenagement.client.presenter.generic.ModelListPresenter;
import net.dromard.demenagement.shared.model.Demenagement;
import net.dromard.demenagement.shared.services.AbstractServiceAsync;
import net.dromard.demenagement.shared.services.DemenagementService;
import net.dromard.demenagement.shared.services.DemenagementServiceAsync;
import net.dromard.widget.client.TableWidget;

import com.google.gwt.core.client.GWT;

public class DemenagementListPresenter extends ModelListPresenter<Demenagement> {

    @Override
    public void bind() {
        super.bind();
        getEventBus().getParentBus().changeLeftWidget(getView());
    }

    @Override
    protected Demenagement createModel() {
        return new Demenagement();
    }

    @Override
    protected void displayModels(List<Demenagement> models) {
        for (int i = 0; i < models.size(); ++i) {
            TableWidget table = getView().getTable();
            table.setText(i + 1, 0, "" + models.get(i).getId());
            table.setText(i + 1, 1, "" + models.get(i).getDate());
        }
    }

    @Override
    protected AbstractServiceAsync<Demenagement> setService() {
        return (DemenagementServiceAsync) GWT.create(DemenagementService.class);
    }
}
