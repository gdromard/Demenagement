package net.dromard.demenagement.client;

import net.dromard.demenagement.client.handlers.ModuleHandler;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ClientEntryPoint implements EntryPoint {

    /**
     * Create a remote service proxy to talk to the server-side service.
     */
    public void onModuleLoad() {
        new ModuleHandler().load();
    }
}
