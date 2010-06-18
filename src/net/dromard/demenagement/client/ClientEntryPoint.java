package net.dromard.demenagement.client;

import net.dromard.demenagement.client.event.CartonEventBus;
import net.dromard.demenagement.client.event.DemenagementEventBus;
import net.dromard.demenagement.client.event.template.TemplateEventBus;
import net.dromard.demenagement.client.presenter.CartonEditPresenter;
import net.dromard.demenagement.client.presenter.CartonListPresenter;
import net.dromard.demenagement.client.presenter.DebugPresenter;
import net.dromard.demenagement.client.presenter.DemenagementEditPresenter;
import net.dromard.demenagement.client.presenter.DemenagementListPresenter;
import net.dromard.demenagement.client.presenter.TemplatePresenter;
import net.dromard.demenagement.client.resources.ClientBundles;
import net.dromard.demenagement.client.view.CartonEditView;
import net.dromard.demenagement.client.view.CartonListView;
import net.dromard.demenagement.client.view.DebugView;
import net.dromard.demenagement.client.view.DemenagementEditView;
import net.dromard.demenagement.client.view.DemenagementListView;
import net.dromard.demenagement.client.view.TemplateView;
import net.dromard.mvp.client.DefaultRunAsyncCallBack;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ClientEntryPoint implements EntryPoint {
    private static boolean debug = true;

    /**
     * Create a remote service proxy to talk to the server-side service.
     */
    public void onModuleLoad() {
        // Old code
        //new ModuleHandler().load();

        // Inject internal CSS
        StyleInjector.inject(ClientBundles.INSTANCE.style().getText());

        // Initiate Template
        final TemplateEventBus templateEventBus = new TemplateEventBus();
        TemplatePresenter templatePresenter = new TemplatePresenter();
        TemplateView templateView = new TemplateView();
        templateEventBus.register(templatePresenter);
        templatePresenter.setView(templateView);
        templatePresenter.setEventBus(templateEventBus);
        templatePresenter.bind();

        // Load Moving Widget
        GWT.runAsync(new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                DemenagementEventBus demenagementEventBus = new DemenagementEventBus(templateEventBus);

                DemenagementListPresenter demenagementListPresenter = new DemenagementListPresenter();
                demenagementListPresenter.setView(new DemenagementListView());
                demenagementListPresenter.setEventBus(demenagementEventBus);

                DemenagementEditPresenter demenagementEditPresenter = new DemenagementEditPresenter();
                demenagementEditPresenter.setView(new DemenagementEditView());
                demenagementEditPresenter.setEventBus(demenagementEventBus);

                if (debug) {
                    DebugPresenter debugPresenter = new DebugPresenter();
                    DebugView debugView = new DebugView();
                    templateEventBus.register(debugPresenter);
                    debugPresenter.setView(debugView);
                    debugPresenter.setEventBus(demenagementEventBus);
                    debugPresenter.bind();

                    demenagementEventBus.register(debugPresenter);
                }

                demenagementEventBus.register(demenagementEditPresenter);
                demenagementEventBus.register(demenagementListPresenter);

                demenagementListPresenter.bind();
                demenagementEditPresenter.bind();
            }
        });

        // Load Demenagement Widget
        GWT.runAsync(new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                CartonEventBus cartonEventBus = new CartonEventBus(templateEventBus);

                CartonListPresenter cartonsPresenter = new CartonListPresenter();
                cartonsPresenter.setEventBus(cartonEventBus);
                cartonsPresenter.setView(new CartonListView());

                CartonEditPresenter cartonPresenter = new CartonEditPresenter();
                cartonPresenter.setEventBus(cartonEventBus);
                cartonPresenter.setView(new CartonEditView());

                if (debug) {
                    //cartonEventBus.register(debugPresenter);
                }
                cartonEventBus.register(cartonsPresenter);
                cartonEventBus.register(cartonPresenter);

                cartonsPresenter.bind();
                cartonPresenter.bind();
            }
        });
    }
}
