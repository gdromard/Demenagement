package net.dromard.demenagement.client;

import net.dromard.demenagement.client.event.demenagement.DemenagementEventBus;
import net.dromard.demenagement.client.event.template.TemplateEventBus;
import net.dromard.demenagement.client.presenter.DebugPresenter;
import net.dromard.demenagement.client.presenter.DemenagementEditPresenter;
import net.dromard.demenagement.client.presenter.DemenagementListPresenter;
import net.dromard.demenagement.client.presenter.TemplatePresenter;
import net.dromard.demenagement.client.view.DebugView;
import net.dromard.demenagement.client.view.DemenagementEditView;
import net.dromard.demenagement.client.view.DemenagementListView;
import net.dromard.demenagement.client.view.TemplateView;
import net.dromard.mvp.client.DefaultRunAsyncCallBack;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

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

        // New Code
        GWT.runAsync(new DefaultRunAsyncCallBack() {
            @Override
            public void onSuccess() {
                TemplateEventBus templateEventBus = new TemplateEventBus();
                DemenagementEventBus demenagementEventBus = new DemenagementEventBus(templateEventBus);

                TemplatePresenter templatePresenter = new TemplatePresenter();
                TemplateView templateView = new TemplateView();
                templateEventBus.register(templatePresenter);
                templatePresenter.setView(templateView);
                templatePresenter.setEventBus(templateEventBus);
                templatePresenter.bind();

                DemenagementListPresenter demenagementListPresenter = new DemenagementListPresenter();
                DemenagementListView demenagementListView = new DemenagementListView();
                demenagementEventBus.register(demenagementListPresenter);
                demenagementListPresenter.setView(demenagementListView);
                demenagementListPresenter.setEventBus(demenagementEventBus);

                DemenagementEditPresenter demenagementEditPresenter = new DemenagementEditPresenter();
                DemenagementEditView demenagementEditView = new DemenagementEditView();
                demenagementEventBus.register(demenagementEditPresenter);
                demenagementEditPresenter.setView(demenagementEditView);
                demenagementEditPresenter.setEventBus(demenagementEventBus);

                if (debug) {
                    DebugPresenter debugPresenter = new DebugPresenter();
                    DebugView debugView = new DebugView();
                    templateEventBus.register(debugPresenter);
                    demenagementEventBus.register(debugPresenter);
                    debugPresenter.setView(debugView);
                    debugPresenter.setEventBus(demenagementEventBus);
                    debugPresenter.bind();
                }

                demenagementListPresenter.bind();
                demenagementEditPresenter.bind();
            }
        });
    }
}
