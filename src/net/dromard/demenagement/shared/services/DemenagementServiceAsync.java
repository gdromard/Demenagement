package net.dromard.demenagement.shared.services;

import java.util.List;

import net.dromard.demenagement.shared.model.Demenagement;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>DemenagementService</code>.
 */
public interface DemenagementServiceAsync {
    void getDemenagements(AsyncCallback<List<Demenagement>> callback) throws IllegalArgumentException;

    void addDemenagement(Demenagement demenagement, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    void removeDemenagement(Demenagement demenagement, AsyncCallback<Boolean> callback) throws IllegalArgumentException;
}
